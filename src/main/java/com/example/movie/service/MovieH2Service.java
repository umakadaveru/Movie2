package com.example.movie.service;



import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.*;



import com.example.movie.model.Movie;

import com.example.movie.model.MovieRowMapper;

import com.example.movie.repository.MovieRepository;



import org.springframework.http.HttpStatus;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import java.util.*;



@Service

public class MovieH2Service implements MovieRepository {

    @Autowired

    public JdbcTemplate db;



    @Override

    public ArrayList<Movie> getMovies() {

        Collection<Movie> movieList = db.query("select * from MOVIELIST", new MovieRowMapper());

        ArrayList<Movie> movies = new ArrayList<>(movieList);

        return movies;



    }



    @Override

    public Movie getMovieById(int movieId) {

        try {

            Movie movie = db.queryForObject("select * from MOVIELIST where movieId=?", new MovieRowMapper(), movieId);

            return movie;



        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }



    }



    @Override

    public Movie addMovie(Movie movie) {

        db.update("insert into MOVIELIST(movieName,leadActor) values(?,?)", movie.getMovieName(), movie.getLeadActor());

        Movie savedMovie = db.queryForObject("select * from MOVIELIST where movieName=? and leadActor=?",

                new MovieRowMapper(), movie.getMovieName(), movie.getLeadActor());

        return savedMovie;

    }



    @Override

    public Movie updateMovie(int movieId, Movie movie) {

        if (movie.getMovieName() != null) {

            db.update("update MOVIELIST set movieName=? where movieId=?", movie.getMovieName(), movieId);

        }

        if (movie.getLeadActor() != null) {

            db.update("update MOVIELIST set leadActor=? where movieId=?", movie.getLeadActor(), movieId);

        }

        return getMovieById(movieId);

    }



    @Override

    public void deleteMovie(int movieId) {

        db.update("delete from MOVIELIST where movieId=?", movieId);



    }



}