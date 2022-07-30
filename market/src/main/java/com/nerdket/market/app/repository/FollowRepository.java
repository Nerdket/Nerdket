package com.nerdket.market.app.repository;

import com.nerdket.market.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository  extends JpaRepository<Follow, Long> {
}
