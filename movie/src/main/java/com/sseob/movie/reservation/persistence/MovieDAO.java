package com.sseob.movie.reservation.persistence;


import com.sseob.movie.reservation.domain.Movie;

public interface MovieDAO {
    Movie selectMovie(Long movieId);

    void insert(Movie movie);
}
