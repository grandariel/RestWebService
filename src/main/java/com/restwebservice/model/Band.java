package com.restwebservice.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
@Entity
public class Band extends Performer {

    private String bandName;
    @OneToMany(mappedBy = "band")
    private List<Artist> artists;

    public Band() {}

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
