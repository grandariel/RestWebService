package com.restwebservice.controller;

import com.restwebservice.dao.AlbumDao;
import com.restwebservice.dao.ArtistDao;
import com.restwebservice.dao.BandDao;
import com.restwebservice.dao.TrackDao;
import com.restwebservice.model.Album;
import com.restwebservice.model.Artist;
import com.restwebservice.model.Message;
import com.restwebservice.model.Track;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
@RestController
@RequestMapping("/")
public class MainController {

    private static Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    ArtistDao artistDao;
    @Autowired
    AlbumDao albumDao;
    @Autowired
    BandDao bandDao;
    @Autowired
    TrackDao trackDao;

    @RequestMapping(method = GET)
    public Message getMain(){
        LOGGER.info("Hello");
        return new Message("Hello World!");
    }

    @RequestMapping("/test")
    public Message getTestData(){
        Album album = new Album();
        album.setName("Album1");
        album.setReleaseDate(new Date());

        Track track1 = new Track("Track1");
        Track track2 = new Track("Track2");
        List<Track> trackList1 = new ArrayList<Track>();
        trackList1.add(track1);
        trackList1.add(track2);
        album.setTrackList(trackList1);
        track1.setAlbum(album);
        track2.setAlbum(album);

        Artist artist = new Artist();
        album.setPerformer(artist);
        artist.setName("Daniel");
        artist.setSurname("Hawk");
        artist.setBirthday(new Date());
        artist.setNationality("Polish");
        artistDao.save(artist);
        albumDao.save(album);
        trackDao.save(track1);
        trackDao.save(track2);

        return new Message("Test");
    }

    @RequestMapping("/test2")
    public Album getTest(){
        return albumDao.findOne(1L);
    }
}
