package com.restwebservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=Movie.class)
public class Movie {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "movie_id")
   private long id;
   private String title;
   private int runtime;
   private String description;
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "director_id")
   @JsonManagedReference
   private Person director;
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(
           name="STARRED_IN",
           joinColumns=@JoinColumn(name="movie_id", referencedColumnName="movie_id"),
           inverseJoinColumns=@JoinColumn(name="person_id", referencedColumnName="person_id"))
   private Set<Person> cast = new HashSet<Person>();
   private String language;

   public Movie() {}

   public Movie(String title, int runtime, String description, Person director, Set<Person> cast) {
      this.title = title;
      this.runtime = runtime;
      this.description = description;
      setDirector(director);
      setCast(cast);
   }

   public void copy(Movie movie) {
      this.title = !StringUtils.isEmpty(movie.getTitle()) ? movie.getTitle() : this.title;
      this.runtime = movie.getRuntime() != 0 ? movie.getRuntime() : this.runtime;
      this.description = !StringUtils.isEmpty(movie.getDescription()) ? movie.getDescription() : this.description;
      this.director = movie.getDirector() != null ? movie.getDirector() : this.getDirector();
      this.cast.addAll(movie.getCast());
      this.language = !StringUtils.isEmpty(movie.getLanguage()) ? movie.getLanguage() : this.language;
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public int getRuntime() {
      return runtime;
   }

   public void setRuntime(int runtime) {
      this.runtime = runtime;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Person getDirector() {
      return director;
   }

   public void setDirector(Person director) {
      director.addMovie(this);
      this.director = director;
   }

   public Set<Person> getCast() {
      return cast;
   }

   public void setCast(Set<Person> cast) {
      for(Person p : cast)
         p.addMovie(this);
      this.cast = cast;
   }

   public String getLanguage() {
      return language;
   }

   public void setLanguage(String language) {
      this.language = language;
   }

   @Override
   public boolean equals(Object movie) {
      return this.getId() == ((Movie)movie).getId();
   }

   @Override
   public String toString(){
      ObjectMapper mapper = new ObjectMapper();
      try {
         return mapper.writeValueAsString(this);
      } catch (JsonProcessingException e) {
         return "";
      }
   }
}
