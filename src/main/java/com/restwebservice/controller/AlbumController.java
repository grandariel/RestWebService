package com.restwebservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restwebservice.dao.AlbumDao;
import com.restwebservice.dao.ArtistDao;
import com.restwebservice.dao.TrackDao;
import com.restwebservice.model.Album;
import com.restwebservice.model.Artist;
import com.restwebservice.model.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Daniel Jastrzębski on 07.10.2016.
 */
@RestController
@RequestMapping("/artists/{artistId}/albums")
public class AlbumController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private ArtistDao artistDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private TrackDao trackDao;

    @RequestMapping(method = GET)
    public List<Album> getAlbums(@PathVariable long artistId) {
        return artistDao.findOne(artistId).getAlbumList();
    }

    @RequestMapping(value = "/{albumId}", method = GET)
    public Album getAlbumById(@PathVariable long artistId, @PathVariable long albumId) {
        Artist artist = artistDao.findOne(artistId);
        for (Album album : artist.getAlbumList()) {
            if (album.getId() == albumId)
                return album;
        }
        return null;
    }

    @RequestMapping(method = POST)
    public ResponseEntity postAlbum(@PathVariable long artistId, @RequestBody String albumJson) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Album album = mapper.readValue(albumJson, Album.class);
            Artist artist = artistDao.findOne(artistId);
            artist.addAlbum(album);
            artistDao.save(artist);
            albumDao.save(album);
            return ResponseEntity.ok("Created album for artist");
        } catch (Exception e) {
            LOGGER.error("Couldn't add album");
            return ResponseEntity.badRequest().body("Invalid artist id or album json");
        }
    }

    @RequestMapping(value = "/{albumId}", method = DELETE)
    public ResponseEntity deleteAlbum(@PathVariable long artistId, @PathVariable long albumId) {
        Artist artist = artistDao.findOne(artistId);
        Album album = null;
        for (Album alb : artist.getAlbumList()) {
            if (alb.getId() == albumId)
                album = alb;
        }
        if (album == null) {
            return ResponseEntity.badRequest().body("Artist " + artistId + " doesn't have album " + albumId);
        }
        for (Track track : album.getTrackList()) {
            trackDao.delete(track);
        }
        artist.removeAlbum(album);
        albumDao.delete(album);
        return ResponseEntity.ok("Removed album " + albumId);
    }
}
