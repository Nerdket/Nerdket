package com.nerdket.market.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nerdket.market.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrincipalDetails implements UserDetails {

	private final User user;

	public PrincipalDetails(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(() -> user.getRole().toString());
		List<String> roles = authorities.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.toList());
		log.info("로그인된 사용자 아이디={} 권한={}", user.getUsername(), roles);
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
