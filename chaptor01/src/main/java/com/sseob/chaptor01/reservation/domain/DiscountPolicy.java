package com.sseob.chaptor01.reservation.domain;


import com.sseob.chaptor01.generic.Money;
import lombok.Getter;

import java.util.List;

@Getter
public class DiscountPolicy {
  public enum PolicyType {PERCENT_POLICY, AMOUNT_POLICY}

  private Long id;
  private Long movieId;
  private PolicyType policyType;
  private Money amount;
  private Double percent;

  public DiscountPolicy() {
  }

  public DiscountPolicy(Long movieId, PolicyType policyType, Money amount, Double percent) {
    this(null, movieId, policyType, amount, percent);
  }

  public DiscountPolicy(Long id, Long movieId, PolicyType policyType, Money amount, Double percent) {
    this.id = id;
    this.movieId = movieId;
    this.policyType = policyType;
    this.amount = amount;
    this.percent = percent;
  }


  public DiscountCondition findDiscountCondition(Screening screening, List<DiscountCondition> conditions) {
    for (DiscountCondition condition : conditions) {
      if (condition.isPeriodCondition()) {
        if (screening.isPlayedIn(condition.getDayOfWeek(),
                condition.getInterval().getStartTime(),
                condition.getInterval().getEndTime())) {
          return condition;
        }
      } else if (condition.isSequenceCondition()){
        if (condition.getSequence().equals(screening.getSequence())) {
          return condition;
        }
      } else if (condition.isCombinationCondition()) {
        if (screening.isPlayedIn(
                condition.getDayOfWeek(),
                condition.getInterval().getStartTime(),
                condition.getInterval().getEndTime()) &&
                condition.getSequence().equals(screening.getSequence())) {
          return condition;
        }
      }
    }

    return null;
  }


  public Money calculateDiscount(DiscountPolicy policy, Movie movie) {

    if (policy.isAmountPolicy()) {
      return policy.getAmount();
    } else if (policy.isPercentPolicy()) {
      return movie.getFee().times(policy.getPercent());
    }
    
    return Money.ZERO;
  }

  private boolean isAmountPolicy() {
    return PolicyType.AMOUNT_POLICY.equals(policyType);
  }

  private boolean isPercentPolicy() {
    return PolicyType.PERCENT_POLICY.equals(policyType);
  }

}