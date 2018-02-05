package com.codurance.socialnetworking.app.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeDescriptionTest {

	@Test
	public void testTimeDescriptionFor1Sec() {
		TimeDescription timeDescription = new TimeDescription(1, TimeType.SECOND);
		assertEquals(timeDescription.description(), "1 second");
	}
	
	@Test
	public void testTimeDescriptionForZeroSec() {
		TimeDescription timeDescription = TimeDescription.ZERO_SECOND;
		assertEquals(timeDescription.description(), "0 second");
	}
	
	@Test
	public void testTimeDescriptionForTwoSecs() {
		TimeDescription timeDescription = new TimeDescription(2, TimeType.SECOND);
		assertEquals(timeDescription.description(), "2 seconds");
	}

	@Test
	public void testTimeDescriptionForOneMiniute() {
		TimeDescription timeDescription = new TimeDescription(1, TimeType.MINUTE);
		assertEquals(timeDescription.description(), "1 minute");
	}
	
	@Test
	public void testTimeDescriptionForTwoMiniutes() {
		TimeDescription timeDescription = new TimeDescription(2, TimeType.MINUTE);
		assertEquals(timeDescription.description(), "2 minutes");
	}
	
	@Test
	public void testTimeDescriptionForOneHour() {
		TimeDescription timeDescription = new TimeDescription(1, TimeType.HOUR);
		assertEquals(timeDescription.description(), "1 hour");
	}
	
	@Test
	public void testTimeDescriptionForTwoHours() {
		TimeDescription timeDescription = new TimeDescription(2, TimeType.HOUR);
		assertEquals(timeDescription.description(), "2 hours");
	}
	
	@Test
	public void testTimeDescriptionForOneDay() {
		TimeDescription timeDescription = new TimeDescription(1, TimeType.DAY);
		assertEquals(timeDescription.description(), "1 day");
	}
	
	@Test
	public void testTimeDescriptionForTwoDays() {
		TimeDescription timeDescription = new TimeDescription(2, TimeType.DAY);
		assertEquals(timeDescription.description(), "2 days");
	}
	
}
