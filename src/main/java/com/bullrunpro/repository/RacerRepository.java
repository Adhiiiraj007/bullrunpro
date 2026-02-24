package com.bullrunpro.repository;

import com.bullrunpro.entity.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RacerRepository extends JpaRepository<Racer, Long> {

    List<Racer> findByWithdrawnFalse();

    List<Racer> findByGroupNumberIsNotNull();

    List<Racer> findByGroupsPublishedTrue();

    // 🔥 Registration order (first registered first)
    List<Racer> findAllByOrderByIdAsc();
}
