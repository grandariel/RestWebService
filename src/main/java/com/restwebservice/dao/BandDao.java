package com.restwebservice.dao;

import com.restwebservice.model.Band;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
public interface BandDao extends CrudRepository<Band, Long> {}