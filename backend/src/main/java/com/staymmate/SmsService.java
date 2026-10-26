package com.staymmate;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service class SmsService {
  private static final String OWNER_NUMBER="+919529441384";
  private static final String GIRLS_NUMBER="+919987984983";
  private static final String BOYS_NUMBER="+919834759661";
  private final HttpClient client=HttpClient.newHttpClient();

  SmsResult sendOwnerEnquiry(Lead lead){
    return send(OWNER_NUMBER,"Staymmate PG enquiry for "+lead.pg.name+" from "+clean(lead.name)+" ("+clean(lead.phone)+"). Message: "+clean(lead.message));
  }
  boolean enabled(){return "true".equalsIgnoreCase(env("STAYMMATE_SMS_ENABLED"));}
  SmsResult sendFriendRequest(ConnectionRequest request){
    String to="Female".equalsIgnoreCase(request.roommate.gender)?GIRLS_NUMBER:BOYS_NUMBER;
    return send(to,"Staymmate roommate request for "+clean(request.roommate.name)+" from "+clean(request.requesterName)+". Message: "+clean(request.message));
  }
  private SmsResult send(String to,String body){
    String sid=env("TWILIO_ACCOUNT_SID"),token=env("TWILIO_AUTH_TOKEN"),service=env("TWILIO_MESSAGING_SERVICE_SID"),from=env("TWILIO_FROM_NUMBER");
    if(sid==null||token==null)return new SmsResult("NOT_CONFIGURED","Twilio Account SID or Auth Token is missing.");
    if(service==null&&from==null)return new SmsResult("NOT_CONFIGURED","Set TWILIO_FROM_NUMBER to your Twilio trial phone number, or set TWILIO_MESSAGING_SERVICE_SID.");
    try{
      // Trial accounts commonly restrict Messaging Services. Prefer the trial number when it is configured.
      String form="To="+enc(to)+"&Body="+enc(body)+(from!=null?"&From="+enc(from):"&MessagingServiceSid="+enc(service));
      String credentials=Base64.getEncoder().encodeToString((sid+":"+token).getBytes(StandardCharsets.UTF_8));
      HttpRequest request=HttpRequest.newBuilder(URI.create("https://api.twilio.com/2010-04-01/Accounts/"+sid+"/Messages.json"))
        .header("Authorization","Basic "+credentials).header("Content-Type","application/x-www-form-urlencoded")
        .POST(HttpRequest.BodyPublishers.ofString(form)).build();
      HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
      if(response.statusCode()>=200&&response.statusCode()<300)return new SmsResult("QUEUED",null);
      return new SmsResult("FAILED",twilioError(response.body(),response.statusCode()));
    }catch(Exception ex){return new SmsResult("FAILED",ex.getClass().getSimpleName()+": "+ex.getMessage());}
  }
  private static String env(String name){String value=System.getenv(name);return value==null||value.isBlank()?null:value.trim();}
  private static String enc(String value){return URLEncoder.encode(value,StandardCharsets.UTF_8);}
  private static String clean(String value){return value==null?"not provided":value.replaceAll("[\\r\\n]+"," ").trim();}
  private static String twilioError(String body,int code){int p=body.indexOf("\"message\":");if(p>=0){int start=body.indexOf('"',p+10)+1,end=body.indexOf('"',start);if(start>0&&end>start)return "Twilio "+code+": "+body.substring(start,end);}return "Twilio HTTP "+code;}
  record SmsResult(String status,String error){}
}
