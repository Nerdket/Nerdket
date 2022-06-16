package com.nerdket.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nerdket.market.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
