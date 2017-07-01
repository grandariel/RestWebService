package com.restwebservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

/**
 * Created by Grand on 10/31/2016.
 */
@Entity
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "user_id")
   private long id;
   private String name;
   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "userlist_id")
   @JsonManagedReference

   @JsonSerialize(using = UserSerializer.class)
   private UserList userList;

   public User() {}

   public User(String name, UserList userList) {
      setName(name);
      setUserList(userList);
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public UserList getUserList() {
      return userList;
   }

   public void setUserList(UserList userList) {
      this.userList = userList;
   }
}
