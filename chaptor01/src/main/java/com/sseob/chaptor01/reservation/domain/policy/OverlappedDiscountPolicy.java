package com.sseob.chaptor01.reservation.domain.policy;

import com.sseob.chaptor01.generic.Money;
import com.sseob.chaptor01.reservation.domain.DiscountPolicy;
import com.sseob.chaptor01.reservation.domain.Screening;

import java.util.ArrayList;
import java.util.List;

public class OverlappedDiscountPolicy extends DiscountPolicy {

  private List<DiscountPolicy> policies = new ArrayList<>(); 
  
  public OverlappedDiscountPolicy(Long policyId, List<DiscountPolicy> policies) {
    super(policyId, (screening -> true));
    this.policies = policies;
  }

  @Override
  protected Money getDisCountAmount(Screening screening) {
    Money result = Money.ZERO;
    
    for (DiscountPolicy policy : policies) {
      result = result.plus(policy.calculateDiscount(screening));
    }
    
    return result;
  }
  
}
