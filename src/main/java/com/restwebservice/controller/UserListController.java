package com.restwebservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restwebservice.dao.MovieDao;
import com.restwebservice.dao.MovieStatsDao;
import com.restwebservice.dao.UserDao;
import com.restwebservice.dao.UserListDao;
import com.restwebservice.model.Movie;
import com.restwebservice.model.MovieStats;
import com.restwebservice.model.State;
import com.restwebservice.model.UserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Daniel Jastrzębski on 03.09.2016.
 */
@RestController
@RequestMapping("/list/{userId}")
public class UserListController {

   private static final Logger LOGGER = LoggerFactory.getLogger(UserListController.class);

   @Autowired private UserListDao userListDao;
   @Autowired private UserDao userDao;
   @Autowired private MovieDao movieDao;
   @Autowired private MovieStatsDao movieStatsDao;

   private ObjectMapper mapper = new ObjectMapper();

   @RequestMapping(method = GET)
   public UserList getUserListByUserId(@PathVariable long userId) {
      return userDao.findOne(userId).getUserList();
   }

   @RequestMapping(value = "/movies", method = GET)
   public Map<Movie, MovieStats> getAllMovieStats(@PathVariable long userId) {
      return userDao.findOne(userId).getUserList().getMovieMap();
   }

   @RequestMapping(value = "/movies/{movieId}/stats", method = GET)
   public MovieStats getMovieStatsByMovieId(@PathVariable long userId, @PathVariable long movieId) {
      Movie movie = movieDao.findOne(movieId);
      UserList userList = userDao.findOne(userId).getUserList();
      return userList.getMovieStats(movie);
   }

   @RequestMapping(method = POST, value = "/movies/{movieId}")
   public ResponseEntity addMovieToList(@PathVariable long userId, @PathVariable long movieId) {
      try {
         UserList list = getUserListByUserId(userId);
         Movie movie = movieDao.findOne(movieId);
         list.addMovie(movie);
         movieStatsDao.save(list.getMovieStats(movie));
         userListDao.save(list);
         movieDao.save(movie);
         LOGGER.info("Added movie " + movieId);
         return ResponseEntity.accepted().body("Added movie " + movieId);
      } catch (Exception e) {
         LOGGER.error("Error in addMovieToList", e);
         return ResponseEntity.badRequest().build();
      }
   }

   @RequestMapping(value = "/movies/{movieId}/stats", method = POST)
   public ResponseEntity postMovieStats(@PathVariable long userId, @PathVariable long movieId,
                                        @RequestBody String movieStatsJson) {
      try {
         UserList list = getUserListByUserId(userId);
         Movie movie = movieDao.findOne(movieId);
         MovieStats oldStats = list.getMovieStats(movie);
         String formattedJson = movieStatsJson.substring(1, movieStatsJson.length()-1).replace("\\", "");
         MovieStats newStats = mapper.readValue(formattedJson, MovieStats.class);
         oldStats.setState(newStats.getState());
         oldStats.setRating(newStats.getRating());
         movieStatsDao.save(oldStats);
         LOGGER.info("Changed stats for " + movieId);
         return ResponseEntity.accepted().body("true");
      } catch (Exception e) {
         LOGGER.error("Error in postMovieStats", e);
         return ResponseEntity.badRequest().build();
      }
   }

   @RequestMapping(value = "/movies/{movieId}/stats", method = PUT)
   public ResponseEntity putMovieStats(@PathVariable long userId, @PathVariable long movieId,
                                        @RequestBody String movieStatsJson) {
      try {
         UserList list = getUserListByUserId(userId);
         Movie movie = movieDao.findOne(movieId);
         MovieStats oldStats = list.getMovieStats(movie);
         String formattedJson = movieStatsJson.substring(1, movieStatsJson.length()-1).replace("\\", "");
         MovieStats newStats = mapper.readValue(formattedJson, MovieStats.class);
         if (newStats.getState() != State.NONE) {
            oldStats.setState(newStats.getState());
         }
         if (newStats.getRating() != 0) {
            oldStats.setRating(newStats.getRating());
         }
         movieStatsDao.save(oldStats);
         LOGGER.info("Changed stats for " + movieId);
         return ResponseEntity.accepted().body("true");
      } catch (Exception e) {
         LOGGER.error("Error in postMovieStats", e);
         return ResponseEntity.badRequest().build();
      }
   }

   @RequestMapping(value = "/movies/{movieId}/stats", method = DELETE)
   public ResponseEntity clearMovieStats(@PathVariable long userId, @PathVariable long movieId) {
      try {
         UserList list = getUserListByUserId(userId);
         Movie movie = movieDao.findOne(movieId);
         MovieStats movieStats = list.getMovieStats(movie);
         movieStats.setRating(0);
         movieStats.setState(State.NONE);
         LOGGER.info("Changed stats for " + movieId);
         return ResponseEntity.accepted().body("true");
      } catch (Exception e) {
         LOGGER.error("Error in postMovieStats", e);
         return ResponseEntity.badRequest().build();
      }
   }

   @RequestMapping(method = DELETE, value = "/movies/{movieId}")
   public ResponseEntity removeMovieFromList(@PathVariable long userId, @PathVariable long movieId) {
      UserList list = getUserListByUserId(userId);
      Iterator<Movie> iterator = list.getMovieMap().keySet().iterator();
      while (iterator.hasNext()) {
         Movie movie = iterator.next();
         if (movie.getId() == movieId) {
            iterator.remove();
         }
      }
      return ResponseEntity.ok().build();
   }
/*
   @RequestMapping(value = "/{id}", method = PUT)
   public ResponseEntity updateBand(@PathVariable long id, @RequestBody String bandJson) {
      ObjectMapper mapper = new ObjectMapper();
      try {
         Band band = mapper.readValue(bandJson, Band.class);
         band.setId(id);
         bandDao.save(band);
         return ResponseEntity.accepted().body("Updated band " + band);
      } catch (Exception e) {
         LOGGER.error("Couldn't update band with id " + id);
         return ResponseEntity.badRequest().body("Invalid id or band json");
      }
   }*/
}
