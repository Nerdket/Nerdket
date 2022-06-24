package com.nerdket.market.util.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nerdket.market.domain.User;
import com.nerdket.market.app.service.user.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

	@Override
	public String createJwtToken(User user) {
		return JwtProperties.TOKEN_PREFIX + JWT.create()
			.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
			.withClaim("username", user.getUsername())
			.withClaim("password", user.getPassword())
			.withClaim("role", user.getRole().toString())
			.sign(Algorithm.HMAC512(JwtProperties.SECRET_KEY));
	}

	@Override
	public UserDto parseTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
		return getUsernameFromToken(token);
	}


	private UserDto getUsernameFromToken(String token) {
		DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET_KEY)).build().verify(token);
		return UserDto.builder()
			.username(decodedJWT.getClaim("username").asString())
			.password(decodedJWT.getClaim("password").asString())
			.role(decodedJWT.getClaim("role").asString())
			.build();

	}

}
