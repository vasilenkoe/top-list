package com.xdsoft.toplistmovies.controllers;

import com.xdsoft.toplistmovies.dao.MovieRepository;
import com.xdsoft.toplistmovies.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Evjen on 20.10.2017.
 */
@Controller
public class TopListController {

    private static final Logger logger = LoggerFactory.getLogger(TopListController.class);

    private MovieRepository movieRepository;

    @Autowired
    public TopListController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping("/")
    public String main() {
        logger.debug("main");

        return "main";
    }

    @RequestMapping("/getTopMovies")
    @ResponseBody
    public List<Movie> getTopMovies(@RequestParam(name = "from") int from, @RequestParam(name = "to") int to) {
        logger.debug("getTopMovies {}, {}", from, to);

        return movieRepository.findTop10ByYearBetween(from, to);
    }
}
