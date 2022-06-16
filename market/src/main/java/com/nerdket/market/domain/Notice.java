package com.nerdket.market.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Notice extends Board {

	@Id
	@GeneratedValue
	@Column(name = "notice_id")
	private Long id;
}
