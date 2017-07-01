package com.restwebservice.controller;

import com.restwebservice.dao.*;
import com.restwebservice.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
@RestController
@RequestMapping("/")
public class MainController {

    private static Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserDao userDao;
    @Autowired
    UserListDao userListDao;
    @Autowired
    MovieDao movieDao;
    @Autowired
    PersonDao personDao;
    @Autowired
    MovieStatsDao movieStatsDao;

    @RequestMapping()
    public Message getMain(){
        return new Message("Hello World!");
    }

    @RequestMapping("/testdata")
    public Message getTestData(){
        Person person1 = new Person("Daniel", "Hawk", "Poland");
        Person person2 = new Person("Ola", "Bawk", "USA");

        Set<Person> cast1 = new HashSet<Person>();
        cast1.add(person1);
        cast1.add(person2);

        Set<Person> cast2 = new HashSet<Person>();
        cast2.add(person2);

        Movie movie1 = new Movie("Shawshank Redemption", 142, "Example of description", person1, cast2);
        Movie movie2 = new Movie("Godfather", 190, "Example of description", person1, cast1);
        Movie movie3 = new Movie("Forrest Gump", 130, "Example of description", person1, cast1);
        Movie movie4 = new Movie("Pulp Fiction", 100, "Example of description", person1, cast2);
        Movie movie5 = new Movie("Lord of the Rings", 200, "Example of description", person1, cast1);

        UserList userList = new UserList();
        userList.addMovie(movie3);
        userList.addMovie(movie5);
        User user1 = new User("daniel", userList);

        personDao.save(person1);
        personDao.save(person2);

        movieDao.save(movie1);
        movieDao.save(movie2);
        movieDao.save(movie3);
        movieDao.save(movie4);
        movieDao.save(movie5);

        for(MovieStats m : userList.getMovieMap().values())
            movieStatsDao.save(m);

        userListDao.save(userList);
        userDao.save(user1);

        return new Message("Test");
    }
}
