package com.nerdket.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nerdket.market.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
