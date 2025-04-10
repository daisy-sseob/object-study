package com.sseob.movie.reservation.persistence;


import com.sseob.movie.reservation.domain.DiscountPolicy;

public interface DiscountPolicyDAO {
  DiscountPolicy selectDiscountPolicy(Long policyId);

  void insert(DiscountPolicy discountPolicy);
}
