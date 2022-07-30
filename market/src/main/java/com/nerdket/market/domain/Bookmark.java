package com.nerdket.market.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Bookmark  extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_isbn")
    private Book book;
}
