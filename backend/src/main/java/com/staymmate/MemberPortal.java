package com.staymmate;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api") class MemberPortal {
  private final Leads leads; private final Mates mates; private final Connections connections;
  MemberPortal(Leads leads,Mates mates,Connections connections){this.leads=leads;this.mates=mates;this.connections=connections;}
  @GetMapping("/member/enquiries") List<Lead> enquiries(@RequestParam String email){return leads.findAll().stream().filter(lead->email.equalsIgnoreCase(lead.email)).toList();}
  @PutMapping("/owner/enquiries/{id}/reply") ResponseEntity<Lead> reply(@PathVariable Long id,@RequestBody Map<String,String> body){
    var lead=leads.findById(id).orElseThrow();String text=body.getOrDefault("message","").trim();
    if(text.isBlank())return ResponseEntity.badRequest().build();
    lead.ownerReply=text;lead.status="OWNER_REPLIED";lead.repliedAt=LocalDateTime.now();return ResponseEntity.ok(leads.save(lead));
  }
  @PutMapping("/member/enquiries/{id}/reply") ResponseEntity<Lead> memberReply(@PathVariable Long id,@RequestBody Map<String,String> body){
    var lead=leads.findById(id).orElseThrow();String email=body.getOrDefault("email","");String text=body.getOrDefault("message","").trim();
    if(!lead.email.equalsIgnoreCase(email)||text.isBlank())return ResponseEntity.status(403).build();
    lead.userReply=text;lead.status="MEMBER_REPLIED";lead.userRepliedAt=LocalDateTime.now();return ResponseEntity.ok(leads.save(lead));
  }
  @GetMapping("/auth/profile") ResponseEntity<Roommate> login(@RequestParam String email){return mates.findByActiveTrue().stream().filter(profile->email.equalsIgnoreCase(profile.email)).findFirst().map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
  @GetMapping("/member/friend-requests") List<ConnectionRequest> friendRequests(@RequestParam String email){return connections.findAll().stream().filter(request->email.equalsIgnoreCase(request.requesterEmail)||email.equalsIgnoreCase(request.roommate.email)).toList();}
}
