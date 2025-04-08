package com.sseob.chaptor01.reservation.domain;


import com.sseob.chaptor01.generic.Money;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class DiscountPolicy {

  private List<DiscountCondition> conditions;

  public DiscountPolicy(DiscountCondition... conditions) {
    this.conditions = List.of(conditions);
  }

  public Money calculateDiscount(Screening screening) {

    for (DiscountCondition condition : conditions) {
      if (condition.isSatisfiedBy(screening)) {
        return getDisCountAmount(screening);
      }
    }

    return Money.ZERO;
  }

  protected abstract Money getDisCountAmount(Screening screening);
  public abstract Long getPolicyId();

}