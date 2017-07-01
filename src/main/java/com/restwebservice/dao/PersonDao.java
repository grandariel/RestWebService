package com.restwebservice.dao;

import com.restwebservice.model.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Grand on 10/31/2016.
 */
public interface PersonDao extends CrudRepository<Person, Long> {}
