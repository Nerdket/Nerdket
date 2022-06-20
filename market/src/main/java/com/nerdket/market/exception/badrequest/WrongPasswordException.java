package com.nerdket.market.exception.badrequest;

public class WrongPasswordException extends BadRequestException {
	public WrongPasswordException() {
		super("[ERROR] 비밀번호가 틀렸습니다.");
	}

	public WrongPasswordException(String message) {
		super(message);
	}

	public WrongPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongPasswordException(Throwable cause) {
		super(cause);
	}
}
