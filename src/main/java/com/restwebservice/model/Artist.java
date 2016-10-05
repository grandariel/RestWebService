package com.restwebservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Daniel Jastrzębski on 21.09.2016.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=Artist.class)
public class Artist extends Performer {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "artist_id")
    private long artistId;
    private String name;
    private String surname;
    private Date birthday;
    private String nationality;
    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    public Artist(){}

    public Artist(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
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

    @Override
    public String toString(){
        return "id: " + getId() + ", name: " + name + ", surname: " + surname;
    }
}
