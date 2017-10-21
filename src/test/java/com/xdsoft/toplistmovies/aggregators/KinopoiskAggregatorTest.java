package com.xdsoft.toplistmovies.aggregators;

import com.xdsoft.toplistmovies.dao.MovieRepository;
import com.xdsoft.toplistmovies.model.Movie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class KinopoiskAggregatorTest {

    private KinopoiskAggregator kinopoiskAggregator;
    private MovieRepository movieRepository;

    @Before
    public void init() {
        kinopoiskAggregator = new KinopoiskAggregator();
        movieRepository = mock(MovieRepository.class);
        kinopoiskAggregator.setMovieRepository(movieRepository);
    }

    @Test
    public void saveDataTest() {
        List<Movie> movies = new ArrayList<>();
        kinopoiskAggregator.saveData(movies);

        verify(movieRepository).save(anyCollection());
    }

    @Test
    public void retrieveDataTest() {
        List<Movie> movies = kinopoiskAggregator.retrieveData();

        assertEquals(250, movies.size());
    }

}
