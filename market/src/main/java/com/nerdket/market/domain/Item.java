package com.nerdket.market.domain;

import static javax.persistence.FetchType.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item extends BaseEntity{

	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "cart_id")
	private Cart cart;

}

