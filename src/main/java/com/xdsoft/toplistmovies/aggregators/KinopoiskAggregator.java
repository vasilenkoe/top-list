package com.xdsoft.toplistmovies.aggregators;

import com.xdsoft.toplistmovies.dao.MovieRepository;
import com.xdsoft.toplistmovies.model.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evjen on 19.10.2017.
 */
@Component
public class KinopoiskAggregator implements MovieAggregator {

    private static final Logger logger = LoggerFactory.getLogger(KinopoiskAggregator.class);

    private static final String URL = "http://kinopoisk.ru/top/";
    private static final int MAX_COUNT = 250;

    private MovieRepository movieRepository;
    private boolean isFirstUpdate = true;

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> retrieveData() {
        logger.debug("retrieveData");

        List<Movie> movies = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(URL).get();

            String prefix = "top250_place_";
            String splitRegex = "[\\(\\)]";

            for (int i = 1; i <= MAX_COUNT; i++) {
                Elements elements = doc.getElementById(prefix + i).select("td");
                String[] tittle = elements.select("td").get(1).text().split(splitRegex);
                String[] rating = elements.select("td").get(2).text().split(splitRegex);
                String name = tittle[2].length() == 1 ? tittle[0] : tittle[2];

                Movie movie = new Movie();
                movie.setName(removeNonBreakableWhiteSpaces(name).trim());
                movie.setYear(Integer.parseInt(tittle[1]));
                movie.setRating(Double.parseDouble(rating[0]));
                movie.setPosition(i);
                movie.setVotes(Integer.parseInt(removeNonBreakableWhiteSpaces(rating[1]).replaceAll(" ", "")));

                movies.add(movie);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return movies;
    }

    @Override
    @Transactional
    public void saveData(List<Movie> movies) {
        logger.debug("saveData");

        if (!isFirstUpdate) {
            movieRepository.deleteAll();
        } else {
            isFirstUpdate = false;
        }
        movieRepository.save(movies);
    }

    private String removeNonBreakableWhiteSpaces(String s) {
        return s.replace('\u00A0', ' ');
    }
}
