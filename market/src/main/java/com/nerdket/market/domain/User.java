package com.nerdket.market.domain;

import com.nerdket.market.domain.vo.Password;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.nerdket.market.domain.Role.NORMAL;

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
	@Column(name = "password")
	private Password password;

	@OneToMany(mappedBy = "user")
	private List<Review> reviews;

	@OneToMany(mappedBy = "user")
	private List<Bookmark> bookmarks;

	@OneToMany(mappedBy = "follower")
	private List<Follow> followers;

	@OneToMany(mappedBy = "following")
	private List<Follow> followings;



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
