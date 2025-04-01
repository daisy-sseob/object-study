package com.sseob.chaptor01.reservation.persistence;


import com.sseob.chaptor01.reservation.domain.Movie;

public interface MovieDAO {
    Movie selectMovie(Long movieId);

    void insert(Movie movie);
}
