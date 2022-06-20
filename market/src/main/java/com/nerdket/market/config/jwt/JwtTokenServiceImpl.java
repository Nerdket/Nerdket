package com.nerdket.market.config.jwt;

import static com.nerdket.market.config.jwt.JwtProperties.*;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nerdket.market.config.auth.PrincipalDetails;
import com.nerdket.market.domain.User;
import com.nerdket.market.exception.badrequest.NoSuchUserException;
import com.nerdket.market.repository.UserRepository;
import com.nerdket.market.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

	@Override
	public String createJwtToken(String username) {
		return TOKEN_PREFIX + JWT.create()
			.withSubject(username)
			.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
			.sign(Algorithm.HMAC512(JwtProperties.SECRET_KEY));
	}

	@Override
	public Optional<String> parseTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX, "");
		return parseToken(token);
	}


	private Optional<String> parseToken(String token) {
		return Optional.ofNullable(JWT.require(Algorithm.HMAC512(JwtProperties.SECRET_KEY))
			.build()
			.verify(token)
			.getClaim("username")
			.asString());
	}


}
