package com.sseob.movie.service;


import com.sseob.movie.generic.Money;
import com.sseob.movie.reservation.domain.*;
import com.sseob.movie.reservation.domain.discount.*;
import com.sseob.movie.reservation.persistence.*;
import com.sseob.movie.reservation.service.ReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
  @InjectMocks
  private ReservationService reservationService;

  @Mock
  private ScreeningDAO screeningDAO;
  @Mock
  private MovieDAO movieDAO;
  @Mock
  private DiscountPolicyDAO discountPolicyDAO;
  @Mock
  private DiscountConditionDAO discountConditionDAO;
  @Mock
  private ReservationDAO reservationDAO;

  @Test
  public void 금액할인정책_계산() {
    // given
    Long customerId = 1L;
    Long screeningId = 1L;
    Long movieId = 1L;
    Long policyId = 1L;
    Integer audienceCount = 2;

    List<DiscountCondition> conditions = List.of(
            new SequenceDiscountCondition(policyId, 2),
            new SequenceDiscountCondition(policyId, 1),
            new PeriodDiscountCondition(policyId, MONDAY, LocalTime.of(10, 12), LocalTime.of(12, 0)),
            new CombinedDiscountCondition(policyId, 1, MONDAY, LocalTime.of(10, 12), LocalTime.of(12, 0))
    );

    DiscountPolicy amountDiscountPolicy = new AmountDiscountPolicy(Money.wons(1000), conditions);
    Movie movie = new Movie(movieId, "한신", 120, Money.wons(10000), amountDiscountPolicy);
    
    Mockito.when(screeningDAO.selectScreening(screeningId))
            .thenReturn(new Screening(screeningId, movie, 1, LocalDateTime.of(2024, 12, 11, 18, 0)));

    // when
    Reservation reservation = reservationService.reserveScreening(customerId, screeningId, audienceCount);

    // then
    Assertions.assertEquals(reservation.getFee(), Money.wons(18000));
  }

  @Test
  public void 비율할인정책_계산() {
    // given
    Long customerId = 1L;
    Long screeningId = 1L;
    Long movieId = 1L;
    Long policyId = 1L;
    double percent = 0.2;
    Integer audienceCount = 2;
    
    List<DiscountCondition> conditions = List.of(
            new SequenceDiscountCondition(policyId, 2),
            new SequenceDiscountCondition(policyId, 1),
            new PeriodDiscountCondition(policyId, MONDAY, LocalTime.of(10, 12), LocalTime.of(12, 0)),
            new CombinedDiscountCondition(policyId, 1, MONDAY, LocalTime.of(10, 12), LocalTime.of(12, 0))
    );

    DiscountPolicy percentDiscountPolicy = new PercentDiscountPolicy(percent, conditions);
    Movie movie = new Movie(movieId, "한신", 120, Money.wons(10000), percentDiscountPolicy);
    
    Mockito.when(screeningDAO.selectScreening(screeningId))
            .thenReturn(new Screening(screeningId, movie, 1, LocalDateTime.of(2024, 12, 11, 18, 0)));

    // when
    Reservation reservation = reservationService.reserveScreening(customerId, screeningId, audienceCount);

    // then
    Assertions.assertEquals(reservation.getFee(), Money.wons(16000));
    
  }
}
