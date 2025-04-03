package com.sseob.chaptor01.reservation.persistence.memory;


import com.sseob.chaptor01.reservation.domain.Screening;
import com.sseob.chaptor01.reservation.persistence.ScreeningDAO;

public class ScreeningMemoryDAO extends InMemoryDAO<Screening> implements ScreeningDAO {
    @Override
    public Screening selectScreening(Long id) {
        return findOne(screening -> screening.getId().equals(id));
    }

}
