package com.bullrunpro.repository;

import com.bullrunpro.entity.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RacerRepository extends JpaRepository<Racer, Long> {

    // ✅ Only active (not withdrawn) racers
    List<Racer> findByWithdrawnFalse();

    // ✅ Only grouped racers
    List<Racer> findByGroupNumberIsNotNull();

    // ✅ Only published groups (visible to users)
    List<Racer> findByGroupsPublishedTrue();

}
