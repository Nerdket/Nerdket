package com.nerdket.market.exception;

public class NoPatternMatchException extends BadRequestException {
	public NoPatternMatchException() {
		super("[ERROR] 패턴이 일치하지 않습니다.");
	}

	public NoPatternMatchException(String message) {
		super(message);
	}

	public NoPatternMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoPatternMatchException(Throwable cause) {
		super(cause);
	}
}
