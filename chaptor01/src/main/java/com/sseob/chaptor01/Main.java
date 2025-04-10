package com.sseob.chaptor01;


import com.sseob.chaptor01.generic.Money;
import com.sseob.chaptor01.reservation.domain.*;
import com.sseob.chaptor01.reservation.domain.policy.AmountDiscountPolicy;
import com.sseob.chaptor01.reservation.domain.condition.CombinedDiscountCondition;
import com.sseob.chaptor01.reservation.domain.condition.PeriodDiscountCondition;
import com.sseob.chaptor01.reservation.domain.condition.SequenceDiscountCondition;
import com.sseob.chaptor01.reservation.persistence.*;
import com.sseob.chaptor01.reservation.persistence.memory.*;
import com.sseob.chaptor01.reservation.service.ReservationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.PrimitiveIterator;

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

    Long policyId = 1L;
    Movie movie = new Movie("한산", 150, Money.wons(10000));
    movieDAO.insert(movie);

    discountConditionDAO.insert(new SequenceDiscountCondition(policyId, 1));
    discountConditionDAO.insert(new PeriodDiscountCondition(policyId, MONDAY, of(10, 0), of(12, 0)));
    discountConditionDAO.insert(new CombinedDiscountCondition(policyId, 1, MONDAY, of(10, 0), of(12, 0)));

    List<DiscountCondition> discountConditions = discountConditionDAO.selectDiscountConditions(policyId);
    DiscountPolicy discountPolicy = new AmountDiscountPolicy(1L, Money.wons(1000), discountConditions);
    discountPolicyDAO.insert(discountPolicy);

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
