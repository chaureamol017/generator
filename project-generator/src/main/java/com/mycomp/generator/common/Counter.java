package com.mycomp.generator.common;

public class Counter {
	private Integer count;

	public Counter() {
		this.count = 0;
	}

	public Counter(Integer start) {
		this.count = start;
	}

	public Integer getCount() {
		return count;
	}

	public Integer getCountAndIncrement() {
		return count++;
	}

	public Integer incrementAndGetCount() {
		return ++count;
	}

	public void increment() {
		count++;
	}

	public void incrementBy(final Integer inc) {
		count = count + inc;
	}
}
