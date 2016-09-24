package com.restwebservice.controller;

import com.restwebservice.dao.ArtistDao;
import com.restwebservice.dao.GreetingDao;
import com.restwebservice.model.Artist;
import com.restwebservice.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Daniel Jastrzębski on 03.09.2016.
 */
@RestController
public class TestController {

    private GreetingDao greetingDao = new GreetingDao();

    @Autowired
    private ArtistDao artistDao;

    @RequestMapping(value = "/greetings/{id}", method = GET)
    public Greeting getGreeting(@PathVariable int id) {
        return greetingDao.getGreeting(id);
    }

    @RequestMapping(value = "/greetings", method = GET)
    public List<Greeting> getGreetingList() {
        return greetingDao.getGreetingList();
    }

    @RequestMapping(value = "/greetings", method = POST)
    public void post() {
        greetingDao.addGreeting(new Greeting(greetingDao.count++, "POST"));
    }

    @RequestMapping(value = "/artists", method = GET)
    public Iterable<Artist> getArtists() {
        return artistDao.findAll();
    }

    @RequestMapping(value = "/artists/{id}", method = GET)
    public Artist getArtistById(@PathVariable long id) {
        return artistDao.findOne(id);
    }
}
