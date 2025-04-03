package com.sseob.chaptor01.reservation.domain;


import com.sseob.chaptor01.generic.Money;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DiscountPolicy {
  public enum PolicyType {PERCENT_POLICY, AMOUNT_POLICY}

  private Long id;
  private Long movieId;
  private PolicyType policyType;
  private Money amount;
  private Double percent;
  private List<DiscountCondition> conditions;

  public DiscountPolicy() {
  }

  public DiscountPolicy(Long movieId, PolicyType policyType, Money amount, Double percent) {
    this(null, movieId, policyType, amount, percent, new ArrayList<>());
  }

  public DiscountPolicy(Long id, Long movieId, PolicyType policyType, Money amount, Double percent, List<DiscountCondition> conditions) {
    this.id = id;
    this.movieId = movieId;
    this.policyType = policyType;
    this.amount = amount;
    this.percent = percent;
    this.conditions = conditions;
  }


  public Money calculateDiscount(Movie movie) {

    if (isAmountPolicy()) {
      return this.amount;
    } else if (isPercentPolicy()) {
      return movie.getFee().times(this.percent);
    }
    
    return Money.ZERO;
  }
  
  public boolean findDiscountCondition(Screening screening) {
    for (DiscountCondition condition : conditions) {
      if (condition.isSatisfiedBy(screening)) {
        return true;
      }
    }

    return false;
  }

  private boolean isAmountPolicy() {
    return PolicyType.AMOUNT_POLICY.equals(policyType);
  }

  private boolean isPercentPolicy() {
    return PolicyType.PERCENT_POLICY.equals(policyType);
  }

}