package com.sseob.movie.reservation.persistence.memory;


import com.sseob.movie.reservation.domain.Movie;
import com.sseob.movie.reservation.persistence.MovieDAO;

public class MovieMemoryDAO extends InMemoryDAO<Movie> implements MovieDAO {
    @Override
    public Movie selectMovie(Long movieId) {
        return findOne(movie -> movie.getId().equals(movieId));
    }
}
