package com.restwebservice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "album_id")
    private long id;
    private String name;
    private Date releaseDate;
    @OneToMany(mappedBy = "album")
    private List<Track> trackList;
    @ManyToOne
    @JoinColumn(name = "performer_id")
    private Performer performer;

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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }
}