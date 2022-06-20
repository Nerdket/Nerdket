package com.nerdket.market.domain;

import static com.nerdket.market.domain.Role.*;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.nerdket.market.domain.vo.Password;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	@Column(name = "user_email")
	private String email;

	@Column(name = "user_name")
	private String username;

	@Embedded
	@Column(name = "user_password")
	private Password password;

	@OneToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@OneToMany(mappedBy = "user")
	private List<Board> boards;

	@OneToMany(mappedBy = "user")
	private List<Review> reviews;

	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	@Column(name = "user_role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Builder
	public User(String email, String username, Password password, Role role){
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = NORMAL;
	}

	public String getPassword(){
		return password.toString();
	}
}
