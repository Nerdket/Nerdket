package com.nerdket.market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nerdket.market.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String name);

	Optional<User> findByEmail(String email);
}