package com.nerdket.market.app.repository;

import com.nerdket.market.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @EntityGraph(attributePaths = {"user"})
    Page<Notice> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    Optional<Notice> findById(long boardId);
}
