package com.nerdket.market.config.jwt;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nerdket.market.config.auth.PrincipalDetails;
import com.nerdket.market.domain.User;
import com.nerdket.market.exception.NoSuchUserException;
import com.nerdket.market.repository.UserRepository;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private final UserRepository userRepository;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		String header = request.getHeader(JwtProperties.HEADER_STRING);
		if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		String token = getToken(request);
		getUsernameByToken(token).ifPresent(this::saveAuthentication);

		chain.doFilter(request, response);
	}

	private void saveAuthentication(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(NoSuchUserException::new);

		PrincipalDetails principalDetails = new PrincipalDetails(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(
			principalDetails,
			null,
			principalDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		return request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
	}

	private Optional<String> getUsernameByToken(String token) {
		return Optional.ofNullable(JWT.require(Algorithm.HMAC512(JwtProperties.SECRET_KEY))
			.build()
			.verify(token)
			.getClaim("username")
			.asString());
	}

}
