package com.aurorastudio.demo.repositories;

import com.aurorastudio.demo.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
