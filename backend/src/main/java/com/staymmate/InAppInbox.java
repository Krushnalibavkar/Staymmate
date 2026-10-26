package com.staymmate;

import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/inbox") class InAppInbox {
  private final Owners owners; private final Leads leads; private final Mates mates; private final Connections connections;
  InAppInbox(Owners owners,Leads leads,Mates mates,Connections connections){this.owners=owners;this.leads=leads;this.mates=mates;this.connections=connections;}
  @GetMapping("/owner") ResponseEntity<List<Lead>> owner(@RequestParam String phone){
    if(owners.findByPhoneAndActiveTrue(phone).isEmpty()) return ResponseEntity.status(403).build();
    return ResponseEntity.ok(leads.findAll());
  }
  @GetMapping("/roommate") ResponseEntity<List<ConnectionRequest>> roommate(@RequestParam String email){
    var recipient=mates.findByActiveTrue().stream().filter(x->email.equalsIgnoreCase(x.email)).findFirst();
    if(recipient.isEmpty())return ResponseEntity.notFound().build();
    return ResponseEntity.ok(connections.findAll().stream().filter(x->x.roommate.id.equals(recipient.get().id)).toList());
  }
  @GetMapping("/friend") ResponseEntity<List<ConnectionRequest>> friend(@RequestParam String phone){
    if(!phone.equals("9987984983")&&!phone.equals("9834759661"))return ResponseEntity.status(403).build();
    return ResponseEntity.ok(connections.findAll().stream().filter(x->phone.equals(x.recipientPhone)).toList());
  }
}
