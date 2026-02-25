package com.bullrunpro.repository;

import com.bullrunpro.entity.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrizeRepository extends JpaRepository<Prize, Long> {

    List<Prize> findByPublishedTrue();

}
