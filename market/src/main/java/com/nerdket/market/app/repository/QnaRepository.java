package com.nerdket.market.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nerdket.market.domain.Qna;

public interface QnaRepository extends JpaRepository<Qna, Long> {
}
