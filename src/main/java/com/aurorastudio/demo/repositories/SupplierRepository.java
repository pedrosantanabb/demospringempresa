package com.aurorastudio.demo.repositories;

import com.aurorastudio.demo.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
