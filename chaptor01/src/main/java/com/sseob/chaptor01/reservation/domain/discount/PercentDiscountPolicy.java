package com.sseob.chaptor01.reservation.domain.discount;

import com.sseob.chaptor01.generic.Money;
import com.sseob.chaptor01.reservation.domain.DiscountCondition;
import com.sseob.chaptor01.reservation.domain.DiscountPolicy;
import com.sseob.chaptor01.reservation.domain.Screening;

import java.util.List;

public class PercentDiscountPolicy extends DiscountPolicy {
  
  private Long policyId;
  private double percent;

  public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
    super(conditions);
    this.percent = percent;
  }

  public PercentDiscountPolicy(double percent, List<DiscountCondition> conditions) {
    super(conditions);
    this.percent = percent;
  }

  @Override
  protected Money getDisCountAmount(Screening screening) {
    return screening.getFixedFee().times(percent);
  }

  @Override
  public Long getPolicyId() {
    return this.policyId;
  }
  
}
