package com.restwebservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Grand on 10/31/2016.
 */
@Entity
public class UserList {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "userlist_id")
   private long id;
   @OneToOne(mappedBy = "userList", fetch = FetchType.EAGER)
   @JsonBackReference
   private User user;
   @ElementCollection
   @LazyCollection(LazyCollectionOption.FALSE)
   private Map<Movie, MovieStats> movieMap = new HashMap<Movie, MovieStats>();

   public UserList() {}

   public List<Movie> getMovies(){
      return new ArrayList<Movie>(movieMap.keySet());
   }

   public MovieStats getMovieStats(Movie movie) {
      for(Movie m : movieMap.keySet()){
         if(m.getId() == movie.getId())
            return movieMap.get(m);
      }
      return null;
   }

   public void addMovie(Movie movie){
      if(!movieMap.containsKey(movie))
         movieMap.put(movie, new MovieStats());
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public Map<Movie, MovieStats> getMovieMap() {
      return movieMap;
   }

   public void setMovieMap(Map<Movie, MovieStats> movieMap) {
      this.movieMap = movieMap;
   }
}