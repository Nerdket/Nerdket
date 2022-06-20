package com.nerdket.market.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@DiscriminatorValue("QnA")
public class Qna extends Board {

    @OneToOne(mappedBy = "qna")
    private Comment comment;
}
