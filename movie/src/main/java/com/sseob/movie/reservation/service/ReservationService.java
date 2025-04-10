package com.sseob.movie.reservation.service;


import com.sseob.movie.reservation.domain.Reservation;
import com.sseob.movie.reservation.domain.Screening;
import com.sseob.movie.reservation.domain.repository.ReservationRepository;
import com.sseob.movie.reservation.domain.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
  private ScreeningRepository screeningRepository;
  private ReservationRepository reservationRepository;

  public Reservation reserveScreening(Long customerId, Long screeningId, Integer audienceCount) {
    Screening screening = screeningRepository.findById(screeningId);
    Reservation reservation = screening.reserve(customerId, audienceCount);
    reservationRepository.save(reservation);
    return reservation;
  }

}
