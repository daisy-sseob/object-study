package com.sseob.chaptor01.reservation.service;


import com.sseob.chaptor01.reservation.domain.*;
import com.sseob.chaptor01.reservation.persistence.*;

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
