package com.nerdket.market.exception;

public class DuplicatedUserEmailException extends BadRequestException {
	public DuplicatedUserEmailException() {
		super("[ERROR] 가입된 이메일이 존재합니다.");
	}

	public DuplicatedUserEmailException(String message) {
		super(message);
	}

	public DuplicatedUserEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedUserEmailException(Throwable cause) {
		super(cause);
	}
}
