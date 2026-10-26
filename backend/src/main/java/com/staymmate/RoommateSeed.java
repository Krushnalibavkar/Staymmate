package com.staymmate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;

@Configuration
class RoommateSeed {
  @Bean @Order(300) CommandLineRunner ensureRoommateMatches(Mates mates) {
    return args -> {
      addIfMissing(mates,"Aarav Shah","aarav@example.com","Andheri","Male",24,16000,"9am-6pm work","11pm-7am","Vegetarian","Keeps shared spaces tidy","Product designer looking for a calm and friendly roommate.");
      addIfMissing(mates,"Isha Kulkarni","isha@example.com","Bandra","Female",22,19000,"College 10am-4pm","12am-8am","Eggetarian","Occasional music","Architecture student and weekend explorer.");
      addIfMissing(mates,"Rohan Patil","rohan@example.com","Wadala","Male",26,13000,"Rotating hospital shifts","1am-9am","Non-vegetarian","Smokes only outdoors","Healthcare professional, friendly and punctual.");
      addIfMissing(mates,"Neha Deshmukh","neha@example.com","Kandivali","Female",23,15000,"9am-5pm office","11pm-7am","Vegetarian","Early riser, no smoking","Marketing associate seeking a clean shared room.");
      addIfMissing(mates,"Kunal Mehta","kunal@example.com","Borivali","Male",25,14000,"10am-7pm office","12am-8am","Non-vegetarian","Plays music with headphones","Software engineer who enjoys a quiet weekday routine.");
      addIfMissing(mates,"Sana Khan","sana@example.com","Andheri","Female",24,17500,"10am-6pm office","11pm-7am","Non-vegetarian","Very organised","Media professional looking for a respectful female roommate.");
    };
  }
  private void addIfMissing(Mates repo,String n,String e,String l,String g,int age,int budget,String schedule,String sleep,String food,String habits,String bio){
    if(repo.findByActiveTrue().stream().anyMatch(x->e.equalsIgnoreCase(x.email)))return;
    Roommate x=new Roommate();x.name=n;x.email=e;x.location=l;x.gender=g;x.age=age;x.budget=budget;x.schedule=schedule;x.sleep=sleep;x.food=food;x.habits=habits;x.bio=bio;x.avatar="https://randomuser.me/api/portraits/"+(g.equalsIgnoreCase("Female")?"women/":"men/")+(20+(Math.abs(e.hashCode())%50))+".jpg";repo.save(x);
  }
}
