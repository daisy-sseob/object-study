package com.sseob.chaptor01.reservation.domain;


import com.sseob.chaptor01.generic.Money;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class DiscountPolicy {

  @Getter
  private Long policyId;
  private List<DiscountCondition> conditions;

  public DiscountPolicy(Long policyId, DiscountCondition... conditions) {
    this.policyId = policyId;
    this.conditions = List.of(conditions);
  }
  
  public DiscountPolicy(Long policyId, List<DiscountCondition> conditions) {
    this.policyId = policyId;
    this.conditions = conditions;
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

}