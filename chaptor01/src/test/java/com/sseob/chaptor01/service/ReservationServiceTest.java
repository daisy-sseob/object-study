package com.sseob.chaptor01.service;


import com.sseob.chaptor01.generic.Money;
import com.sseob.chaptor01.reservation.domain.*;
import com.sseob.chaptor01.reservation.domain.condition.*;
import com.sseob.chaptor01.reservation.domain.policy.AmountDiscountPolicy;
import com.sseob.chaptor01.reservation.domain.policy.OverlappedDiscountPolicy;
import com.sseob.chaptor01.reservation.domain.policy.PercentDiscountPolicy;
import com.sseob.chaptor01.reservation.persistence.*;
import com.sseob.chaptor01.reservation.service.ReservationService;
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

    DiscountPolicy amountDiscountPolicy = new AmountDiscountPolicy(policyId, Money.wons(1000), conditions);
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

    DiscountPolicy percentDiscountPolicy = new PercentDiscountPolicy(policyId, percent, conditions);
    Movie movie = new Movie(movieId, "한신", 120, Money.wons(10000), percentDiscountPolicy);
    
    Mockito.when(screeningDAO.selectScreening(screeningId))
            .thenReturn(new Screening(screeningId, movie, 1, LocalDateTime.of(2024, 12, 11, 18, 0)));

    // when
    Reservation reservation = reservationService.reserveScreening(customerId, screeningId, audienceCount);

    // then
    Assertions.assertEquals(reservation.getFee(), Money.wons(16000));
    
  }
  
  @Test
  public void 중복할인정책_계산() {
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

    DiscountPolicy amountDiscountPolicy = new AmountDiscountPolicy(policyId, Money.wons(1000), conditions);
    DiscountPolicy percentDiscountPolicy = new PercentDiscountPolicy(policyId, percent, conditions);
    
    OverlappedDiscountPolicy overlappedDiscountPolicy = new OverlappedDiscountPolicy(policyId, List.of(percentDiscountPolicy, amountDiscountPolicy));
    
    Movie movie = new Movie(movieId, "한신", 120, Money.wons(10000), overlappedDiscountPolicy);
    
    Mockito.when(screeningDAO.selectScreening(screeningId))
            .thenReturn(new Screening(screeningId, movie, 1, LocalDateTime.of(2024, 12, 11, 18, 0)));

    // when
    Reservation reservation = reservationService.reserveScreening(customerId, screeningId, audienceCount);

    // then
    
    // 20퍼센트 할인, 1000원 2번 할인
    // 20000 * 0.2 = 4000 + 2000 = 6000
    Assertions.assertEquals(reservation.getFee(), Money.wons(14000));
    
  }
  
  
}
