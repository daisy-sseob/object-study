package com.sseob.chaptor01.reservation.persistence;


import com.sseob.chaptor01.reservation.domain.DiscountPolicy;

public interface DiscountPolicyDAO {
  DiscountPolicy selectDiscountPolicy(Long policyId);

  void insert(DiscountPolicy discountPolicy);
}
