package com.nerdket.market.domain.vo;

public interface PatternRegex {

	/**
	 * 비밀번호는 8자리부터 16자리까지, 숫자, 문자, 특수문자가 포함되어야 한다.
	 */
	String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";
}
