package com.nerdket.market.config.jwt;

public interface JwtProperties {
	String SECRET_KEY = "Nz,jw@2kY{v`SNfT(j9F6ap-";
	int EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
	String TOKEN_CLAIM = "username";
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
