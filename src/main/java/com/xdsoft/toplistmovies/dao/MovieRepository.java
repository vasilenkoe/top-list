package com.xdsoft.toplistmovies.dao;

import com.xdsoft.toplistmovies.model.Movie;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Evjen on 19.10.2017.
 */
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Cacheable("byYearBetween")
    List<Movie> findTop10ByYearBetween(int from, int to);

}
