package com.restwebservice.dao;

import com.restwebservice.model.MovieStats;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Grand on 10/31/2016.
 */
public interface MovieStatsDao extends CrudRepository<MovieStats, Long> {}
