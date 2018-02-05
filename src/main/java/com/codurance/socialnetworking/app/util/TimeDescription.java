package com.codurance.socialnetworking.app.util;

public class TimeDescription {
	
	public static final TimeDescription ZERO_SECOND = new TimeDescription(0, TimeType.SECOND);

	private Integer number;
	private TimeType timeType;

	public TimeDescription(Integer number, TimeType timeType) {
		this.number = number;
		this.timeType = timeType;
	}

	public String description() {
		return number + " " + timeType.name().toLowerCase() + (isPlural() ? "s" : "");
	}

	private boolean isPlural() {
		return number > 1;
	}

	public boolean isMoreThanZero() {
		return number > 0;
	}
}
