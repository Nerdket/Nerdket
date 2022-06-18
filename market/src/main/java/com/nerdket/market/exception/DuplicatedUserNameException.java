package com.nerdket.market.exception;

public class DuplicatedUserNameException extends BadRequestException {
	public DuplicatedUserNameException() {
		super("[ERROR] 중복된 회원이름이 존재합니다.");
	}

	public DuplicatedUserNameException(String message) {
		super(message);
	}

	public DuplicatedUserNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedUserNameException(Throwable cause) {
		super(cause);
	}
}
