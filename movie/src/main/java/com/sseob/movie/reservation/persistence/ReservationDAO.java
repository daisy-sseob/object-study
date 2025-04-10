package com.sseob.movie.reservation.persistence;


import com.sseob.movie.reservation.domain.Reservation;

public interface ReservationDAO {
    void insert(Reservation reservation);
}
