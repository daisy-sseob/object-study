package com.sseob.chaptor01.reservation.domain.discount;

import com.sseob.chaptor01.generic.Money;
import com.sseob.chaptor01.reservation.domain.DiscountCondition;
import com.sseob.chaptor01.reservation.domain.DiscountPolicy;
import com.sseob.chaptor01.reservation.domain.Screening;

public class AmountDiscountPolicy extends DiscountPolicy {
  
  private Long policyId; 
  private Money discountAmount;

  public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
    super(conditions);
    this.discountAmount = discountAmount;
  }

  @Override
  protected Money getDisCountAmount(Screening screening) {
    return discountAmount;
  }

  @Override
  public Long getPolicyId() {
    return this.policyId;
  }
  
}
