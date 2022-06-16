package com.nerdket.market.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;

@Entity
@Getter
public class User extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	@Column(name = "user_email")
	private String email;

	@Column(name = "user_password")
	private String password;

	@OneToOne
	private Cart cart;

	@OneToMany(mappedBy = "user")
	private List<Board> boards;

	@OneToMany(mappedBy = "user")
	private List<Review> reviews;
}
