package com.xdsoft.toplistmovies.aggregators;

import com.xdsoft.toplistmovies.model.Movie;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Evjen on 20.10.2017.
 */
public interface MovieAggregator {

    @PostConstruct
    @Scheduled(cron = "${cron.expression}")
    @CacheEvict(value = "byYearBetween", allEntries = true)
    @Transactional
    default void retrieveAndSaveData() {
        List<Movie> movies = retrieveData();
        saveData(movies);
    }

    List<Movie> retrieveData();

    void saveData(List<Movie> movies);
}
