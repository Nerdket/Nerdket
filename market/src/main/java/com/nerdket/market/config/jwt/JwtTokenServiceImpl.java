package com.nerdket.market.config.jwt;

import static com.nerdket.market.config.jwt.JwtProperties.*;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

	@Override
	public String createJwtToken(String username) {
		return TOKEN_PREFIX + JWT.create()
			.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
			.withClaim(TOKEN_CLAIM, username)
			.sign(Algorithm.HMAC512(SECRET_KEY));
	}

	@Override
	public Optional<String> parseTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX, "");
		return parseToken(token);
	}


	private Optional<String> parseToken(String token) {
		return Optional.ofNullable(JWT.require(Algorithm.HMAC512(SECRET_KEY))
			.build()
			.verify(token)
			.getClaim(TOKEN_CLAIM)
			.asString());
	}


}
