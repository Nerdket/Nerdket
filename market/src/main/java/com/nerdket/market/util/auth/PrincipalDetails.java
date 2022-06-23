package com.nerdket.market.util.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nerdket.market.domain.Role;
import com.nerdket.market.app.service.user.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrincipalDetails implements UserDetails {

	private final String username;
	private final String password;
	private final Role role;

	public PrincipalDetails(UserDto user) {
		username = user.getUsername();
		password = user.getPassword();
		role = Role.valueOf(user.getRole());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(role::toString);
		List<String> roles = authorities.stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.toList());
		log.info("로그인된 사용자 아이디={} 권한={}", username, roles);
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
