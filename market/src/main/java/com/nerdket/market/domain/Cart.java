package com.nerdket.market.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cart extends BaseEntity{

	@Id
	@GeneratedValue
	@Column(name = "cart_id")
	private Long id;
}
