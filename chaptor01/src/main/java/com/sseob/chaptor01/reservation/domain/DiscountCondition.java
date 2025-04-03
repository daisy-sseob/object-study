package com.sseob.chaptor01.reservation.domain;

import com.sseob.chaptor01.generic.TimeInterval;
import lombok.Getter;

import java.time.DayOfWeek;

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

  public DiscountCondition(Long policyId, ConditionType conditionType, DayOfWeek dayOfWeek, TimeInterval interval, Integer sequence) {
    this(null, policyId, conditionType, dayOfWeek, interval, sequence);
  }

  public DiscountCondition(Long id, Long policyId, ConditionType conditionType, DayOfWeek dayOfWeek, TimeInterval interval, Integer sequence) {
    this.id = id;
    this.policyId = policyId;
    this.conditionType = conditionType;
    this.dayOfWeek = dayOfWeek;
    this.interval = interval;
    this.sequence = sequence;
  }

  public boolean isPeriodCondition() {
    return ConditionType.PERIOD_CONDITION.equals(conditionType);
  }

  public boolean isSequenceCondition() {
    return ConditionType.SEQUENCE_CONDITION.equals(conditionType);
  }

  public boolean isCombinationCondition() {
    return ConditionType.COMBINED_CONDITION.equals(conditionType);
  }

}
