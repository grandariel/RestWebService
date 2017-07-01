package com.restwebservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Grand on 10/31/2016.
 */
@Entity
public class Person {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "person_id")
   private long id;
   private String name;
   private String surname;
   private String country;
   private Date birthday;
   @ManyToMany(mappedBy = "cast", fetch = FetchType.EAGER)
   @JsonIgnore
   private List<Movie> movies = new ArrayList<Movie>();
   @OneToMany(mappedBy = "director", fetch = FetchType.EAGER)
   @JsonBackReference
   private List<Movie> directorOf = new ArrayList<Movie>();

   public Person() {}

   public Person(String name, String surname, String country) {
      this.name = name;
      this.surname = surname;
      this.country = country;
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

   public String getSurname() {
      return surname;
   }

   public void setSurname(String surname) {
      this.surname = surname;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public Date getBirthday() {
      return birthday;
   }

   public void setBirthday(Date birthday) {
      this.birthday = birthday;
   }

   public List<Movie> getMovies() {
      return movies;
   }

   public void setMovies(List<Movie> movies) {
      this.movies = movies;
   }

   public List<Movie> getDirectorOf() {
      return directorOf;
   }

   public void setDirectorOf(List<Movie> directorOf) {
      this.directorOf = directorOf;
   }

   public void addDirectorOf(Movie movie){
      if(!directorOf.contains(movie))
         directorOf.add(movie);
   }

   public void addMovie(Movie movie){
      if(!movies.contains(movie))
         movies.add(movie);
   }
}
