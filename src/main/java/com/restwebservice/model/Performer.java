package com.restwebservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=Performer.class)
public abstract class Performer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "performer_id")
    private long id;
    @OneToMany(mappedBy = "performer", fetch = FetchType.EAGER)
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