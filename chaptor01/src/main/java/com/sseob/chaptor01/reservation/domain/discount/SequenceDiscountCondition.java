package com.sseob.chaptor01.reservation.domain.discount;

import com.sseob.chaptor01.reservation.domain.DiscountCondition;
import com.sseob.chaptor01.reservation.domain.Screening;

public class SequenceDiscountCondition implements DiscountCondition {
  
  private final Integer sequence;
  private final Long policyId;

  public SequenceDiscountCondition(Long policyId, Integer sequence) {
    this.sequence = sequence;
    this.policyId = policyId;
  }

  @Override
  public boolean isSatisfiedBy(Screening screening) {
    return screening.isSequence(this.sequence);
  }

  @Override
  public Long getPolicyId() {
    return this.policyId;
  }
}
