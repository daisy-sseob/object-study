package com.sseob.movie.reservation.domain.discount;

import com.sseob.movie.reservation.domain.DiscountCondition;
import com.sseob.movie.reservation.domain.Screening;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodDiscountCondition implements DiscountCondition {
  
  private final Long policyId;
  private final DayOfWeek dayOfWeek;
  private final LocalTime startTime;
  private final LocalTime endTime;
  

  public PeriodDiscountCondition(Long policyId, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
    this.policyId = policyId;
    this.dayOfWeek = dayOfWeek;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public boolean isSatisfiedBy(Screening screening) {
    return screening.isPlayedIn(
        this.dayOfWeek,
        this.startTime,
        this.endTime);
  }

  @Override
  public Long getPolicyId() {
    return this.policyId;
  }
}
