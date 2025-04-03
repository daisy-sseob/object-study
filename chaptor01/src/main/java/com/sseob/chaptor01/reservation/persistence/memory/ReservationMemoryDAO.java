package com.sseob.chaptor01.reservation.persistence.memory;

import com.sseob.chaptor01.reservation.domain.Reservation;
import com.sseob.chaptor01.reservation.persistence.ReservationDAO;

public class ReservationMemoryDAO extends InMemoryDAO<Reservation> implements ReservationDAO {
    @Override
    public void insert(Reservation reservation) {
        super.insert(reservation);
    }
}
