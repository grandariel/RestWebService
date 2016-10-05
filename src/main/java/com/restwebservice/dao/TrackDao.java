package com.restwebservice.dao;

import com.restwebservice.model.Track;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
public interface TrackDao extends CrudRepository<Track, Long> {}