package com.sseob.chaptor01.reservation.domain;

public interface DiscountCondition {

  boolean isSatisfiedBy(Screening screening);
  
}
