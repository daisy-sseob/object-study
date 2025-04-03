package com.sseob.chaptor01.reservation.persistence.memory;

import com.sseob.chaptor01.reservation.domain.DiscountPolicy;
import com.sseob.chaptor01.reservation.persistence.DiscountPolicyDAO;

public class DiscountPolicyMemoryDAO extends InMemoryDAO<DiscountPolicy> implements DiscountPolicyDAO {
    @Override
    public DiscountPolicy selectDiscountPolicy(Long movieId) {
        return findOne(policy -> policy.getMovieId().equals(movieId));
    }
}
