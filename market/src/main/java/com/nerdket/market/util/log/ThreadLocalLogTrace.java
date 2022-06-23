package com.nerdket.market.util.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

	private static final String START_PREFIX = "-->";
	private static final String COMPLETE_PREFIX = "<--";
	private static final String EX_PREFIX = "<X-";

	private final ThreadLocal<Trace> traceHolder = new ThreadLocal<>();

	@Override
	public TraceStatus begin(String message) {
		syncTrace();
		Trace trace = traceHolder.get();
		Long startTimeMs = System.currentTimeMillis();
		log.info("[{}] {}{}", trace.getId(), addSpace(START_PREFIX, trace.getLevel()), message);

		return new TraceStatus(trace, startTimeMs, message);
	}

	private void syncTrace() {
		Trace trace = traceHolder.get();
		if (trace == null) {
			traceHolder.set(new Trace());
		} else {
			traceHolder.set(trace.createNextTrace());
		}
	}

	private String addSpace(String prefix, int level) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < level; i++) {
			sb.append((i == level - 1) ? "|" + prefix : "|   ");
		}
		return sb.toString();
	}

	@Override
	public void end(TraceStatus status) {
		complete(status, null);
	}

	@Override
	public void exception(TraceStatus status, Exception e) {
		complete(status, e);
	}

	private void complete(TraceStatus status, Exception e) {
		Long stopTimeMs = System.currentTimeMillis();
		long resultTimeMs = stopTimeMs - status.getStartTimeMs();
		Trace trace = status.getTrace();
		if (e == null) {
			log.info("[{}] {}{} time={}ms", trace.getId(), addSpace(COMPLETE_PREFIX, trace.getLevel()), status.getMessage(), resultTimeMs);
		} else {
			log.info("[{}] {}{} time={}ms ex={}", trace.getId(), addSpace(EX_PREFIX, trace.getLevel()), status.getMessage(), resultTimeMs, e.toString());
		}
		releaseTrace();
	}

	private void releaseTrace() {
		Trace trace = traceHolder.get();
		if (trace.isFirstLevel()) {
			traceHolder.remove();
		} else {
			traceHolder.set(trace.createPreviousTrace());
		}
	}
}
