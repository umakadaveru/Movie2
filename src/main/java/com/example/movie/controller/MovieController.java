package com.example.movie.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.*;



import com.example.movie.model.Movie;

import com.example.movie.service.MovieH2Service;



@RestController

public class MovieController {

    @Autowired

    public MovieH2Service mhs;



    @GetMapping("/movies")

    public ArrayList<Movie> getMovies() {

        return mhs.getMovies();

    }



    @GetMapping("/movies/{movieId}")

    public Movie getMovieById(@PathVariable("movieId") int movieId) {

        return mhs.getMovieById(movieId);

    }



    @PostMapping("/movies")

    public Movie addMovie(@RequestBody Movie movie) {

        return mhs.addMovie(movie);

    }



    @PutMapping("/movies/{movieId}")

    public Movie updateMovie(@PathVariable("movieId") int movieId, @RequestBody Movie movie) {

        return mhs.updateMovie(movieId, movie);

    }



    @DeleteMapping("/movies/{movieId}")

    public void deleteMovie(@PathVariable("movieId") int movieId) {

        mhs.deleteMovie(movieId);

    }

}