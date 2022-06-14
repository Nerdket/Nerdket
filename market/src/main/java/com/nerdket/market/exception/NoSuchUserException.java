package com.nerdket.market.exception;

public class NoSuchUserException extends RuntimeException {
	public NoSuchUserException() {
		super("[ERROR] 해당 유저가 존재하지 않습니다.");
	}

	public NoSuchUserException(String message) {
		super(message);
	}

	public NoSuchUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchUserException(Throwable cause) {
		super(cause);
	}
}
