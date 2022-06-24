package com.nerdket.market.util.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TraceStatus {

	private final Trace trace;
	private final Long startTimeMs;
	private final String message;
}
