package com.restwebservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restwebservice.dao.AlbumDao;
import com.restwebservice.dao.ArtistDao;
import com.restwebservice.dao.TrackDao;
import com.restwebservice.model.Album;
import com.restwebservice.model.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Daniel Jastrzębski on 07.10.2016.
 */
@RestController
@RequestMapping("/artists/{artistId}/albums/{albumId}/tracks")
public class TrackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private ArtistDao artistDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private TrackDao trackDao;

    @RequestMapping(method = GET)
    public List<Track> getTracks(@PathVariable long artistId, @PathVariable long albumId) {
        List<Album> albumList = artistDao.findOne(artistId).getAlbumList();
        for (Album album : albumList) {
            if(album.getId() == albumId)
                return album.getTrackList();
        }
        return null;
    }

    @RequestMapping(value = "/{trackId}", method = GET)
    public Track getTrackById(@PathVariable long artistId, @PathVariable long albumId, @PathVariable long trackId) {
        List<Album> albumList = artistDao.findOne(artistId).getAlbumList();
        for (Album album : albumList)
            if(album.getId() == albumId)
                for (Track track : album.getTrackList())
                    if(track.getId() == trackId)
                        return track;

        return null;
    }

    @RequestMapping(method = POST)
    public ResponseEntity postTrack(@PathVariable long artistId, @PathVariable long albumId, @RequestBody String trackJson) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Track track = mapper.readValue(trackJson, Track.class);
            List<Album> albumList = artistDao.findOne(artistId).getAlbumList();
            for (Album album : albumList) {
                if(album.getId() == albumId) {
                    album.addTrack(track);
                    albumDao.save(album);
                    trackDao.save(track);
                    return ResponseEntity.ok("Track added");
                }
            }
            return ResponseEntity.badRequest().body("Wrong artist or album id");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Wrong track json");
        }
    }
}
