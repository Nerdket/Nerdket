package com.nerdket.market.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("QnA")
public class Qna extends Board {

}
