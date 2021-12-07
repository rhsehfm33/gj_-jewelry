package com.lms.gj_jewelry.domain;

import com.lms.gj_jewelry.enumclass.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Long> {

    List<Item> findAll();

    List<Item> findByName(String name);

    List<Item> findByCategory(ItemCategory itemCategory);

    List<Item> findByManufacturer_Name(String name);

    Optional<Item> findById(Long id);

}
