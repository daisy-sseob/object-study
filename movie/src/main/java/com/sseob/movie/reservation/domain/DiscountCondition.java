package com.sseob.movie.reservation.domain;

public interface DiscountCondition {

  Long getPolicyId();
  boolean isSatisfiedBy(Screening screening);
  
}
