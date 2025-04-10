package com.sseob.movie.reservation.service;


import com.sseob.movie.reservation.domain.*;
import com.sseob.movie.reservation.persistence.*;

public class ReservationService {
  private ScreeningDAO screeningDAO;
  private ReservationDAO reservationDAO;

  public ReservationService(ScreeningDAO screeningDAO,
                            ReservationDAO reservationDAO) {
    this.screeningDAO = screeningDAO;
    this.reservationDAO = reservationDAO;
  }

  public Reservation reserveScreening(Long customerId, Long screeningId, Integer audienceCount) {
    Screening screening = screeningDAO.selectScreening(screeningId);
    Reservation reservation = screening.reserve(customerId, audienceCount);
    reservationDAO.insert(reservation);
    return reservation;
  }

}
