package com.nerdket.market.util.log;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Trace {

	private final String id;
	private final int level;

	public Trace() {
		this.id = createId();
		this.level = 0;
	}

	private Trace(String id, int level) {
		this.id = id;
		this.level = level;
	}

	private String createId() {
		return UUID.randomUUID().toString().substring(0, 8);
	}

	public Trace createNextTrace() {
		return new Trace(id, level + 1);
	}

	public Trace createPreviousTrace(){
		return new Trace(id, level - 1);
	}

	public boolean isFirstLevel() {
		return level == 0;
	}
}
