package com.nerdket.market.exception.handler;

import com.nerdket.market.app.controller.response.FailResponse;
import com.nerdket.market.exception.badrequest.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ControllerExceptionAdvice {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public FailResponse exceptionHandler(Exception e) {
		log.error("[exceptionHandler] ex", e);
		return new FailResponse("[ERROR] 서버 오류");
	}

	@ExceptionHandler
	public FailResponse badRequestExceptionHandler(BadRequestException e) {
		log.error("[{}] ex", e.getClass(), e);
		return new FailResponse(e.getMessage());
	}
}
