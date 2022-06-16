package com.nerdket.market.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class QnA extends Board {

	@Id
	@GeneratedValue
	@Column(name = "qna_id")
	private Long id;
}
