package com.staymmate;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/roommate-requests") class FriendRequestRouter {
  private final Mates mates; private final Connections connections;
  FriendRequestRouter(Mates mates,Connections connections){this.mates=mates;this.connections=connections;}
  @PostMapping("/{id}") ResponseEntity<Map<String,String>> send(@PathVariable Long id,@RequestBody ConnectionRequest request){
    request.id=null;
    request.roommate=mates.findById(id).orElseThrow();
    request.requesterName=request.requesterName==null||request.requesterName.isBlank()?"Staymmate member":request.requesterName;
    request.message=request.message==null||request.message.isBlank()?"I would like to connect and discuss sharing a room.":request.message;
    request.recipientPhone="Female".equalsIgnoreCase(request.roommate.gender)?"9987984983":"9834759661";
    request.smsStatus="SAVED_IN_APP";
    connections.save(request);
    return ResponseEntity.status(201).body(Map.of("message","Request sent. The member will review it and Staymmate will notify you when they accept.","recipientType",request.roommate.gender));
  }
}
