package com.restwebservice.dao;

import com.restwebservice.model.Greeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Jastrzębski on 04.09.2016.
 */
public class GreetingDao {

    private List<Greeting> greetingList = new ArrayList<Greeting>();
    public int count = 0;

    public GreetingDao(){
        greetingList.add(count++, new Greeting(0, "START"));
        greetingList.add(count++, new Greeting(1, "START"));
    }

    public List<Greeting> getGreetingList() {
        return greetingList;
    }

    public void setGreetingList(List<Greeting> greetingList) {
        this.greetingList = greetingList;
    }

    public void addGreeting(Greeting greeting) {
        greetingList.add(greeting);
    }

    public Greeting getGreeting(int index) {
        return greetingList.get(index);
    }
}
