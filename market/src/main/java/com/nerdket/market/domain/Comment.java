package com.nerdket.market.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Qna qna;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment_content")
    private String content;
}
