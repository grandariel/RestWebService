package com.restwebservice.controller;

import com.restwebservice.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Daniel Jastrzębski on 03.09.2016.
 */
@RestController
public class TestController {

    private Greeting greeting;

    @RequestMapping(value = "/", method = GET)
    public Greeting get() {
        greeting = new Greeting(0, "GET");
        return greeting;
    }

    @RequestMapping(value = "/", method = POST)
    public Greeting post(){
        greeting = new Greeting(0, "POST");
        return greeting;
    }
}
