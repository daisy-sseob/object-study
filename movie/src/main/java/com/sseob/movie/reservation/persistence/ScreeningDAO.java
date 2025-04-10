package com.sseob.movie.reservation.persistence;


import com.sseob.movie.reservation.domain.Screening;

public interface ScreeningDAO {
    Screening selectScreening(Long screeningId);

    void insert(Screening screening);
}
