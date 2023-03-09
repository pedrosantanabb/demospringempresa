package com.aurorastudio.demo.repositories;

import com.aurorastudio.demo.entities.PriceReduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceReductionRepository extends JpaRepository<PriceReduction, Long> {
}
