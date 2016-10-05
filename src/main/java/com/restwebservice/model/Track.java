package com.restwebservice.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlIDREF;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "track_id")
    private long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    public Track() {
    }

    public Track(String title) {
        this.title = title;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
