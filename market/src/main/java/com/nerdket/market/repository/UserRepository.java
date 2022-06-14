package com.nerdket.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nerdket.market.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
