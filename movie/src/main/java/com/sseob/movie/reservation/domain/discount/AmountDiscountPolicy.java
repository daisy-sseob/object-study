package com.sseob.movie.reservation.domain.discount;

import com.sseob.movie.generic.Money;
import com.sseob.movie.reservation.domain.DiscountCondition;
import com.sseob.movie.reservation.domain.DiscountPolicy;
import com.sseob.movie.reservation.domain.Screening;

import java.util.List;

public class AmountDiscountPolicy extends DiscountPolicy {
  
  private Long policyId; 
  private Money discountAmount;

  public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
    super(conditions);
    this.discountAmount = discountAmount;
  }
  
  public AmountDiscountPolicy(Money discountAmount, List<DiscountCondition> conditions) {
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
