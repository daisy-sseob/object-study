package com.sseob.chaptor01.reservation.persistence;


import com.sseob.chaptor01.reservation.domain.DiscountCondition;

import java.util.List;

public interface DiscountConditionDAO {
  List<DiscountCondition> selectDiscountConditions(Long policyId);

  void insert(DiscountCondition discountCondition);

  void insertAll(List<DiscountCondition> conditions);
  
}
