package com.sseob.chaptor01.reservation.domain;

public interface DiscountCondition {

  Long getPolicyId();
  boolean isSatisfiedBy(Screening screening);
  
}
