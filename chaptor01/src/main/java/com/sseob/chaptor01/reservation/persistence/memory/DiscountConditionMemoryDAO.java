package com.sseob.chaptor01.reservation.persistence.memory;


import com.sseob.chaptor01.reservation.domain.DiscountCondition;
import com.sseob.chaptor01.reservation.persistence.DiscountConditionDAO;

import java.util.List;

public class DiscountConditionMemoryDAO extends InMemoryDAO<DiscountCondition> implements DiscountConditionDAO {
  @Override
  public List<DiscountCondition> selectDiscountConditions(Long policyId) {
    return findMany((id) -> true);
  }

  @Override
  public void insertAll(List<DiscountCondition> conditions) {
    insertAll(conditions);
  }

}
