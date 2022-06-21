package com.nerdket.market.domain;

import static javax.persistence.FetchType.*;
import static javax.persistence.InheritanceType.*;

import javax.persistence.*;

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
	protected User user;

	@Column(name = "board_hit")
	private Long hit;

	@Column(name = "board_title")
	protected String title;

	@Column(name = "board_content")
	protected String content;

}
