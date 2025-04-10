package com.sseob.chaptor01.reservation.domain.policy;

import com.sseob.chaptor01.generic.Money;
import com.sseob.chaptor01.reservation.domain.DiscountCondition;
import com.sseob.chaptor01.reservation.domain.DiscountPolicy;
import com.sseob.chaptor01.reservation.domain.Screening;

import java.util.List;

public class AmountDiscountPolicy extends DiscountPolicy {
  
  private final Money discountAmount;

  public AmountDiscountPolicy(Long policyId, Money discountAmount, List<DiscountCondition> conditions) {
    super(policyId, conditions);
    this.discountAmount = discountAmount;
  }

  @Override
  protected Money getDisCountAmount(Screening screening) {
    return discountAmount;
  }

}
