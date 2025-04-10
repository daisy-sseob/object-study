package com.sseob.movie.reservation.domain.discount;

import com.sseob.movie.reservation.domain.DiscountCondition;
import com.sseob.movie.reservation.domain.Screening;

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
