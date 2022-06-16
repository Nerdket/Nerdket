package com.nerdket.market.config.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.nerdket.market.domain.User;
import com.nerdket.market.repository.UserRepository;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private final UserRepository userRepository;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository  userRepository){
		super(authenticationManager);
		this.userRepository = userRepository;
	}

}
