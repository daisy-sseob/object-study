package com.sseob.chaptor01.reservation.persistence;


import com.sseob.chaptor01.reservation.domain.Screening;

public interface ScreeningDAO {
    Screening selectScreening(Long screeningId);

    void insert(Screening screening);
}
