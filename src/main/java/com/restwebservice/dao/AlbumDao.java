package com.restwebservice.dao;

import com.restwebservice.model.Album;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Daniel Jastrzębski on 05.10.2016.
 */
public interface AlbumDao extends CrudRepository<Album, Long> {}