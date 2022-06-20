package com.nerdket.market.config.jwt;

import static com.nerdket.market.config.jwt.JwtProperties.*;

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
import com.nerdket.market.exception.badrequest.NoSuchUserException;
import com.nerdket.market.repository.UserRepository;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private final JwtTokenService jwtTokenService;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
		super(authenticationManager);
		this.jwtTokenService = jwtTokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		String header = request.getHeader(JwtProperties.HEADER_STRING);
		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		jwtTokenService.getUser(request).ifPresent(this::saveAuthentication);

		chain.doFilter(request, response);
	}

	private void saveAuthentication(User user) {

		PrincipalDetails principalDetails = new PrincipalDetails(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(
			principalDetails,
			null,
			principalDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
