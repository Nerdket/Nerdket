package com.nerdket.market.exception.badrequest;

public class NotValidatedTokenException extends BadRequestException {
	public NotValidatedTokenException() {
		super("[ERROR] 유효한 토큰이 아닙니다.");
	}

	public NotValidatedTokenException(String message) {
		super(message);
	}

	public NotValidatedTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotValidatedTokenException(Throwable cause) {
		super(cause);
	}
}
