package com.nerdket.market.config.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerdket.market.config.auth.PrincipalDetails;
import com.nerdket.market.service.user.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
		AuthenticationException {
		UserDto userDto = getUserDto(request).orElseThrow(IllegalStateException::new);
		return makeAuthentication(userDto);
	}

	private Optional<UserDto> getUserDto(HttpServletRequest request) {
		ObjectMapper objectMapper = new ObjectMapper();
		UserDto userDto = null;
		try {
			userDto = objectMapper.readValue(request.getInputStream(), UserDto.class);
		} catch (IOException e) {
			log.error("[ERROR] I/O 오류");
		}
		return Optional.ofNullable(userDto);
	}

	private Authentication makeAuthentication(UserDto userDto) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
			userDto.getEmail(), userDto.getPassword());
		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication authResult) {
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		String JwtToken = makeJwtToken(principalDetails);
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + JwtToken);
	}

	private String makeJwtToken(PrincipalDetails principalDetails) {
		return JWT.create()
			.withSubject(principalDetails.getUsername())
			.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
			.withClaim("id", principalDetails.getUser().getId())
			.withClaim("username", principalDetails.getUser().getUsername())
			.sign(Algorithm.HMAC512(JwtProperties.SECRET_KEY));
	}
}
