package com.restwebservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restwebservice.dao.ArtistDao;
import com.restwebservice.model.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Daniel Jastrzębski on 03.09.2016.
 */
@RestController
@RequestMapping("/artists")
public class ArtistController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private ArtistDao artistDao;

    @RequestMapping(method = GET)
    public Iterable<Artist> getArtists() {
        return artistDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Artist getArtistById(@PathVariable long id) {
        return artistDao.findOne(id);
    }

    @RequestMapping(method = POST)
    public ResponseEntity postArtist(@RequestBody String artistJson) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Artist artist = mapper.readValue(artistJson, Artist.class);
            artistDao.save(artist);
            LOGGER.info("Created artist " + artist);
            return ResponseEntity.accepted().body("Created artist " + artist);
        } catch (IOException e) {
            LOGGER.error("Error in postArtist", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity deleteArtist(@PathVariable long id) {
        try {
            artistDao.delete(id);
            LOGGER.info("Deleted artist with id " + id);
            return ResponseEntity.accepted().body("Deleted artist with id " + id);
        } catch (Exception e) {
            LOGGER.error("Couldn't delete artist with id " + id);
            return ResponseEntity.badRequest().body("Invalid id");
        }
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity updateArtist(@PathVariable long id, @RequestBody String artistJson) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Artist artist = mapper.readValue(artistJson, Artist.class);
            artist.setId(id);
            artistDao.save(artist);
            return ResponseEntity.accepted().body("Updated artist " + artist);
        } catch (Exception e) {
            LOGGER.error("Couldn't update artist with id " + id);
            return ResponseEntity.badRequest().body("Invalid id or artist json");
        }
    }
}
