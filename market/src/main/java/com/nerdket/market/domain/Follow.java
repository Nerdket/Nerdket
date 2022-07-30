package com.nerdket.market.domain;

import javax.persistence.*;

@Entity
public class Follow extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name="following_id")
    private User following;
}
