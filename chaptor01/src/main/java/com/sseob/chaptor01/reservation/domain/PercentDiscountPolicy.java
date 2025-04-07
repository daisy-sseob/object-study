package com.sseob.chaptor01.reservation.domain;

import com.sseob.chaptor01.generic.Money;

public class PercentDiscountPolicy extends DiscountPolicy {
  
  private double percent;

  @Override
  protected Money getDisCountAmount(Screening screening) {
    return screening.getFixedFee().times(percent);
  }
  
}
