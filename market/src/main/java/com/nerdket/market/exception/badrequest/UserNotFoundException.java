package com.nerdket.market.exception.badrequest;

public class UserNotFoundException extends BadRequestException {
	public UserNotFoundException() {
		super("[ERROR] 해당 유저가 존재하지 않습니다.");
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}
}
