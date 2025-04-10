package com.sseob.chaptor01.reservation.domain.policy;

import com.sseob.chaptor01.generic.Money;
import com.sseob.chaptor01.reservation.domain.DiscountCondition;
import com.sseob.chaptor01.reservation.domain.DiscountPolicy;
import com.sseob.chaptor01.reservation.domain.Screening;

import java.util.List;

public class PercentDiscountPolicy extends DiscountPolicy {
  
  private final double percent;

  public PercentDiscountPolicy(Long policyId, double percent, List<DiscountCondition> conditions) {
    super(policyId, conditions);
    this.percent = percent;
  }

  @Override
  protected Money getDisCountAmount(Screening screening) {
    return screening.getFixedFee().times(percent);
  }

}
