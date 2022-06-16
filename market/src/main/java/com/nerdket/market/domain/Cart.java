package com.nerdket.market.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart extends BaseEntity{

	@Id
	@GeneratedValue
	@Column(name = "cart_id")
	private Long id;

	@OneToMany
	private List<Item> items = new ArrayList<>();
}
