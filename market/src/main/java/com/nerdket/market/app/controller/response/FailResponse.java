package com.nerdket.market.app.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FailResponse {
	private final String state = "FAIL";
	private String message;
}
