package com.nerdket.market.util.log;

public interface LogTrace {

	/**
	 * 로그 시작
	 */
	TraceStatus begin(String message);

	/**
	 * 로그 끝
	 */
	void end(TraceStatus status);

	/**
	 * 예외 상황
	 */
	void exception(TraceStatus status, Exception e);

}
