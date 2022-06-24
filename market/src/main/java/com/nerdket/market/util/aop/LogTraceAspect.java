package com.nerdket.market.util.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.nerdket.market.util.log.LogTrace;
import com.nerdket.market.util.log.TraceStatus;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class LogTraceAspect {

	private final LogTrace logTrace;

	@Around("execution(* com.nerdket.market.app..*(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		TraceStatus traceStatus = null;
		try {
			String message = joinPoint.getSignature().toShortString();
			traceStatus = logTrace.begin(message);

			Object result = joinPoint.proceed();

			logTrace.end(traceStatus);
			return result;
		} catch (Exception e) {
			logTrace.exception(traceStatus, e);
			throw e;
		}
	}
}
