package com.sseob.chaptor01.reservation.domain.discount;

import com.sseob.chaptor01.generic.Money;
import com.sseob.chaptor01.reservation.domain.DiscountPolicy;
import com.sseob.chaptor01.reservation.domain.Screening;

public class PercentDiscountPolicy extends DiscountPolicy {
  
  private double percent;

  @Override
  protected Money getDisCountAmount(Screening screening) {
    return screening.getFixedFee().times(percent);
  }
  
}
