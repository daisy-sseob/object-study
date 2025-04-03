package com.sseob.chaptor01.reservation.domain;

import com.sseob.chaptor01.generic.TimeInterval;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
public class DiscountCondition {
  public enum ConditionType {PERIOD_CONDITION, SEQUENCE_CONDITION, COMBINED_CONDITION}

  private Long id;
  private Long policyId;
  private ConditionType conditionType;
  private DayOfWeek dayOfWeek;
  private TimeInterval interval;
  private Integer sequence;

  public DiscountCondition() {
  }

  public DiscountCondition(Long policyId, ConditionType conditionType, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, Integer sequence) {
    this(null, policyId, conditionType, dayOfWeek, startTime, endTime, sequence);
  }

  public DiscountCondition(Long id, Long policyId, ConditionType conditionType, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, Integer sequence) {
    this.id = id;
    this.policyId = policyId;
    this.conditionType = conditionType;
    this.dayOfWeek = dayOfWeek;
    this.interval = TimeInterval.of(startTime, endTime);
    this.sequence = sequence;
  }
  
  public boolean isSatisfiedBy(Screening screening) {
    if (isPeriodCondition()) {
      if (screening.isPlayedIn(this.dayOfWeek,
              interval.getStartTime(),
              interval.getEndTime())) {
        return true;
      }
    } else if (isSequenceCondition()){
      if (this.sequence.equals(screening.getSequence())) {
        return true;
      }
    } else if (isCombinationCondition()) {
      if (screening.isPlayedIn(
              this.dayOfWeek,
              this.interval.getStartTime(),
              this.interval.getEndTime()) &&
              this.sequence.equals(screening.getSequence())) {
        return true;
      }
    }
    return false;
  }

  private boolean isPeriodCondition() {
    return ConditionType.PERIOD_CONDITION.equals(conditionType);
  }

  private boolean isSequenceCondition() {
    return ConditionType.SEQUENCE_CONDITION.equals(conditionType);
  }

  private boolean isCombinationCondition() {
    return ConditionType.COMBINED_CONDITION.equals(conditionType);
  }

}
