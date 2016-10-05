package com.restwebservice.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
@Entity
public class Band extends Performer {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "band_id")
    private long bandId;
    private String bandName;
    @OneToMany(mappedBy = "band")
    private List<Artist> artists;

    public Band() {}

    public long getBandId() {
        return bandId;
    }

    public void setBandId(long bandId) {
        this.bandId = bandId;
    }

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
