package com.inventory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{

}
