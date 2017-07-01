package com.restwebservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restwebservice.dao.MovieDao;
import com.restwebservice.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Daniel Jastrzębski on 07.10.2016.
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieDao movieDao;

    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/{movieId}", method = GET)
    public Movie getMovieById(@PathVariable long movieId) {
        return movieDao.findOne(movieId);
    }

    @RequestMapping(value = "/{movieId}", method = DELETE)
    public ResponseEntity deleteMovieById(@PathVariable long movieId) {
        movieDao.delete(movieId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping
    public Iterable<Movie> getMovies() {
        return movieDao.findAll();
    }

    @RequestMapping(method = POST)
    public ResponseEntity postTrack(@RequestBody String movieJson) {

        try {
            Movie movie = mapper.readValue(movieJson, Movie.class);
            movieDao.save(movie);
            return ResponseEntity.ok("Movie has been saved.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Wrong movie json");
        }
    }

    @RequestMapping(value = "/{movieId}", method = PUT)
    public ResponseEntity putMovieById(@PathVariable long movieId, @RequestBody String movieJson) {
        Movie movie = movieDao.findOne(movieId);
        try {
            Movie newMovie = mapper.readValue(movieJson, Movie.class);
            movie.copy(newMovie);
            movieDao.save(movie);
            return ResponseEntity.ok("Movie has been updated.");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Wrong movie json");
        }
    }
}
