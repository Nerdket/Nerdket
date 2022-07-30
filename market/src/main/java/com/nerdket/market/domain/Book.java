package com.nerdket.market.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Book extends BaseEntity{

    @Id
    @Column(name = "book_isbn")
    private String isbn;

    @Column(name = "book_title")
    private String title;

    @Column(name="book_desc")
    private String desc;

    @Column(name = "book_thumbnail")
    private String thumbnail;

    @OneToMany(mappedBy = "book")
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;


}
