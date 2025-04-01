package com.sseob.chaptor01.reservation.persistence;


import com.sseob.chaptor01.reservation.domain.Reservation;

public interface ReservationDAO {
    void insert(Reservation reservation);
}
