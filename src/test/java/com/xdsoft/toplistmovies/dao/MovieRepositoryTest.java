package com.xdsoft.toplistmovies.dao;

import com.xdsoft.toplistmovies.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Evjen on 20.10.2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findTop10ByYearBetweenTest() {
        int startYear = 1990;

        for (int i = 0; i < 20; i++) {
            Movie movie = new Movie();
            movie.setYear(startYear + i);
            entityManager.persist(movie);
        }

        List<Movie> topMovies = movieRepository.findTop10ByYearBetween(1995, 2005);

        assertEquals(10, topMovies.size());

        for (Movie movie : topMovies) {
            int year = movie.getYear();
            assertTrue(year >= 1995 && year <= 2005);
        }
    }

}
