package com.restwebservice.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Performer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "performer_id")
    private long id;
    @OneToMany(mappedBy = "performer")
    private List<Album> albumList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }
}