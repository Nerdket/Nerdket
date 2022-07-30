package com.nerdket.market.app.repository;

import com.nerdket.market.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book, Long> {
}
