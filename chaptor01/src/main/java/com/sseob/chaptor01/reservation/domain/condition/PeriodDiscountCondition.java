package com.sseob.chaptor01.reservation.domain.condition;

import com.sseob.chaptor01.reservation.domain.DiscountCondition;
import com.sseob.chaptor01.reservation.domain.Screening;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodDiscountCondition implements DiscountCondition {
  
  @Getter
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

}
