package com.staymmate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Configuration class OwnerSeed {
  @Bean CommandLineRunner seedOwner(Owners owners){return args->{
    if(owners.findByPhoneAndActiveTrue("9529441384").isEmpty()){
      OwnerAccount owner=new OwnerAccount(); owner.name="Staymmate PG Owner"; owner.phone="9529441384"; owner.email="owner@staymmate.local"; owners.save(owner);
    }
  };}
}

@RestController @RequestMapping("/api/owner") class OwnerPortal {
  private final Owners owners;
  OwnerPortal(Owners owners){this.owners=owners;}
  @GetMapping("/account") ResponseEntity<OwnerAccount> account(@RequestParam String phone){return owners.findByPhoneAndActiveTrue(phone).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());}
}
