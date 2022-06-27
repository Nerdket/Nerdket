package com.nerdket.market.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("QnA")
public class Qna extends Board {

    @OneToOne(mappedBy = "qna")
    private Comment comment;
}
