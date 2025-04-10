package com.sseob.movie.reservation.persistence.memory;

import com.sseob.movie.reservation.domain.Reservation;
import com.sseob.movie.reservation.persistence.ReservationDAO;

public class ReservationMemoryDAO extends InMemoryDAO<Reservation> implements ReservationDAO {
    @Override
    public void insert(Reservation reservation) {
        super.insert(reservation);
    }
}
