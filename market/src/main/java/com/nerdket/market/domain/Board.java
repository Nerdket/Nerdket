package com.nerdket.market.domain;

import static javax.persistence.FetchType.*;
import static javax.persistence.InheritanceType.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Getter;

@Entity
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "board_type")
public class Board extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "board_id")
	private Long id;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
