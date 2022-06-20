package com.nerdket.market.config.jwt;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.nerdket.market.domain.User;

public interface JwtTokenService {

	/**
	 * 토큰 생성
	 */
	String getJwtToken(String username);

	/**
	 * 토큰으로 유저 찾기
	 */
	Optional<User> getUser(HttpServletRequest request);
}
