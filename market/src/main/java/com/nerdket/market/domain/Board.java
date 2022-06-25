package com.nerdket.market.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy = JOINED)
@Getter
@DiscriminatorColumn(name = "board_type")
public class Board extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "board_id")
	protected Long id;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	protected User user;

	@Column(name = "board_hit")
	protected Long hit;

	@Column(name = "board_title")
	protected String title;

	@Column(name = "board_content")
	protected String content;

}
