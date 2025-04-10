package com.sseob.movie.reservation.persistence.memory;

import com.sseob.movie.reservation.domain.DiscountPolicy;
import com.sseob.movie.reservation.persistence.DiscountPolicyDAO;

public class DiscountPolicyMemoryDAO extends InMemoryDAO<DiscountPolicy> implements DiscountPolicyDAO {
    @Override
    public DiscountPolicy selectDiscountPolicy(Long policyId) {
        return findOne(policy -> policy.getPolicyId().equals(policyId));
    }
}
