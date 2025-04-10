package com.sseob.movie.reservation.domain.discount;

import com.sseob.movie.reservation.domain.DiscountCondition;
import com.sseob.movie.reservation.domain.Screening;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class CombinedDiscountCondition implements DiscountCondition {

  private final Long policyId;
  private final Integer sequence;
  private final DayOfWeek dayOfWeek;
  private final LocalTime startTime;
  private final LocalTime endTime;

  public CombinedDiscountCondition(Long policyId, Integer sequence, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
    this.policyId = policyId;
    this.sequence = sequence;
    this.dayOfWeek = dayOfWeek;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public boolean isSatisfiedBy(Screening screening) {
    return screening.isSequence(this.sequence) &&
           screening.isPlayedIn(this.dayOfWeek, this.startTime, this.endTime);
  }

  @Override
  public Long getPolicyId() {
    return this.policyId;
  }
  
}
