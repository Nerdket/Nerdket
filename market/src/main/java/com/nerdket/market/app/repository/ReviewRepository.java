package com.nerdket.market.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nerdket.market.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
