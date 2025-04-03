package com.sseob.chaptor01.reservation.persistence.memory;


import com.sseob.chaptor01.reservation.domain.Movie;
import com.sseob.chaptor01.reservation.persistence.MovieDAO;

public class MovieMemoryDAO extends InMemoryDAO<Movie> implements MovieDAO {
    @Override
    public Movie selectMovie(Long movieId) {
        return findOne(movie -> movie.getId().equals(movieId));
    }
}
