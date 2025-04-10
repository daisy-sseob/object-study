package com.sseob.movie;


import com.sseob.movie.generic.Money;
import com.sseob.movie.reservation.domain.DiscountPolicy;
import com.sseob.movie.reservation.domain.Movie;
import com.sseob.movie.reservation.domain.Reservation;
import com.sseob.movie.reservation.domain.Screening;
import com.sseob.movie.reservation.domain.discount.AmountDiscountPolicy;
import com.sseob.movie.reservation.domain.discount.CombinedDiscountCondition;
import com.sseob.movie.reservation.domain.discount.PeriodDiscountCondition;
import com.sseob.movie.reservation.domain.discount.SequenceDiscountCondition;
import com.sseob.movie.reservation.persistence.*;
import com.sseob.movie.reservation.persistence.memory.*;
import com.sseob.movie.reservation.service.ReservationService;

import java.time.LocalDateTime;

import static java.time.DayOfWeek.MONDAY;
import static java.time.LocalTime.of;

public class Main {
  private ScreeningDAO screeningDAO = new ScreeningMemoryDAO();
  private MovieDAO movieDAO = new MovieMemoryDAO();
  private DiscountPolicyDAO discountPolicyDAO = new DiscountPolicyMemoryDAO();
  private DiscountConditionDAO discountConditionDAO = new DiscountConditionMemoryDAO();
  private ReservationDAO reservationDAO = new ReservationMemoryDAO();

  ReservationService reservationService = new ReservationService(screeningDAO, reservationDAO);

  private Screening initializeData() {
    Movie movie = new Movie("한산", 150, Money.wons(10000));
    movieDAO.insert(movie);

    DiscountPolicy discountPolicy = new AmountDiscountPolicy(Money.wons(1000));
    discountPolicyDAO.insert(discountPolicy);

    discountConditionDAO.insert(new SequenceDiscountCondition(discountPolicy.getPolicyId(), 1));
    discountConditionDAO.insert(new PeriodDiscountCondition(discountPolicy.getPolicyId(), MONDAY, of(10, 0), of(12, 0)));
    discountConditionDAO.insert(new CombinedDiscountCondition(discountPolicy.getPolicyId(), 1, MONDAY, of(10, 0), of(12, 0)));
    
    Screening screening = new Screening(movie, 7, LocalDateTime.of(2024, 12, 11, 18, 0));
    screeningDAO.insert(screening);

    return screening;
  }

  public void run() {
    Screening screening = initializeData();

    Reservation reservation = reservationService.reserveScreening(1L, screening.getId(), 2);

    System.out.printf("관객수 : %d, 요금: %s%n", reservation.getAudienceCount(), reservation.getFee());
  }

  public static void main(String[] args) {
    new Main().run();
  }
}
