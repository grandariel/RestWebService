package com.restwebservice.model;

import javax.persistence.*;

/**
 * Created by Grand on 10/31/2016.
 */
@Entity
public class MovieStats {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "moviestats_id")
   private long id;
   private int rating;
   private State state;

   public MovieStats(){
      setState(State.NONE);
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public int getRating() {
      return rating;
   }

   public void setRating(int rating) {
      this.rating = rating;
   }

   public State getState() {
      return state;
   }

   public void setState(State state) {
      this.state = state;
   }
}
