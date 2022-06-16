package com.nerdket.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nerdket.market.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
