package com.restwebservice.dao;

import com.restwebservice.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Daniel Jastrzębski on 21.09.2016.
 */
@Repository
public interface ArtistDao extends CrudRepository<Artist, Long> {}