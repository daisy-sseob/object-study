package com.sseob.chaptor01.reservation.domain;

import com.sseob.chaptor01.generic.Money;

public class AmountDiscountPolicy extends DiscountPolicy {
  
  private Money discountAmount;

  @Override
  protected Money getDisCountAmount(Screening screening) {
    return discountAmount;
  }
  
}
