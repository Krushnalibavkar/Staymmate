package com.staymmate;
import org.springframework.data.jpa.repository.JpaRepository;import java.util.*;
interface Pgs extends JpaRepository<Pg,Long>{} interface Mates extends JpaRepository<Roommate,Long>{List<Roommate> findByActiveTrue();}interface Reviews extends JpaRepository<Review,Long>{}interface Leads extends JpaRepository<Lead,Long>{}interface Connections extends JpaRepository<ConnectionRequest,Long>{}interface Messages extends JpaRepository<DirectMessage,Long>{}interface Owners extends JpaRepository<OwnerAccount,Long>{Optional<OwnerAccount> findByPhoneAndActiveTrue(String phone);}
