package com.nerdket.market.util.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.nerdket.market.util.auth.PrincipalDetails;
import com.nerdket.market.app.service.user.UserDto;
import com.nerdket.market.app.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private final JwtTokenService jwtTokenService;
	private final UserService userService;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
								  JwtTokenService jwtTokenService,
								  UserService userService) {
		super(authenticationManager);
		this.jwtTokenService = jwtTokenService;
		this.userService = userService;
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

		UserDto userDto = jwtTokenService.parseTokenFromRequest(request);
		userDto.ifPresent(this::saveAuthentication);
		chain.doFilter(request, response);
	}

	private void saveAuthentication(UserDto userDto) {
		PrincipalDetails principalDetails = new PrincipalDetails(userDto);
		Authentication authentication = new UsernamePasswordAuthenticationToken(
			principalDetails,
			null,
			principalDetails.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
