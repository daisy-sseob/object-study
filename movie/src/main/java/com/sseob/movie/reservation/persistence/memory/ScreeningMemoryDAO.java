package com.sseob.movie.reservation.persistence.memory;


import com.sseob.movie.reservation.domain.Screening;
import com.sseob.movie.reservation.persistence.ScreeningDAO;

public class ScreeningMemoryDAO extends InMemoryDAO<Screening> implements ScreeningDAO {
    @Override
    public Screening selectScreening(Long id) {
        return findOne(screening -> screening.getId().equals(id));
    }

}
