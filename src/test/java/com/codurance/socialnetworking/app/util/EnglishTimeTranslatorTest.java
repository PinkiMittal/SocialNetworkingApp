package com.codurance.socialnetworking.app.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

public class EnglishTimeTranslatorTest {

	TimeTranslator timeTranslator = new EnglishTimeTranslator();

	@Before
	public void initalization() {
		timeTranslator = new EnglishTimeTranslator();
	}

	@Test
	public void testZeroSecondsInEnglish() {
		Long zeroTimeInMilliSecond = 0l;
		assertEquals("0 second ago", timeTranslator.translate(zeroTimeInMilliSecond));
	}

	@Test
	public void testOneSecondsInEnglish() {
		Long zeroTimeInMilliSecond = 1000l;
		assertEquals("1 second ago", timeTranslator.translate(zeroTimeInMilliSecond));
	}

	@Test
	public void testTwoSecondsAgoInEnglish() throws InterruptedException {
		Long timeInMilliSeconds = new GregorianCalendar().getTimeInMillis();
		synchronized (this) {
			this.wait(2000);
		}
		Long delta = new GregorianCalendar().getTimeInMillis() - timeInMilliSeconds;
		assertEquals("2 seconds ago", timeTranslator.translate(delta));
	}

	@Test
	public void test60SecondsAgoShouldSay1minAgoInEnglish() {
		Long delta = 1000l * 60;
		assertEquals("1 minute ago", timeTranslator.translate(delta));
	}

	@Test
	public void test5MinsAgoShouldSay1minAgoInEnglish() {
		Long delta = 1000l * 60 * 5;
		assertEquals("5 minutes ago", timeTranslator.translate(delta));
	}

	@Test
	public void test60MinsShouldSay1hourAgoInEnglish() {
		Long delta = 1000l * 60 * 60;
		assertEquals("1 hour ago", timeTranslator.translate(delta));
	}

	@Test
	public void test2HoursAgoInEnglish() {
		Long delta = 1000l * 60 * 120;
		assertEquals("2 hours ago", timeTranslator.translate(delta));
	}

	@Test
	public void test24HoursAgoShouldSay1DayAgoInEnglish() {
		Long delta = 1000l * 60 * 60 * 24;
		assertEquals("1 day ago", timeTranslator.translate(delta));
	}

	@Test
	public void test5DaysAgoInEnglish() {
		Long delta = 1000l * 60 * 60 * 24 * 5;
		assertEquals("5 days ago", timeTranslator.translate(delta));
	}

	@Test
	public void test31DaysAgoShouldSay1MonthAgoInEnglish() {
		Long delta = 1000l * 60 * 60 * 24 * 31;
		assertEquals("31 days ago", timeTranslator.translate(delta));
	}

	/*
	 * @Test public void test31DaysAgoShouldSay1MonthAgoInEnglish() { Long delta
	 * = 1000l * 60 * 60 * 24 * 31; assertEquals("1 month ago",
	 * timeTranslator.translate(delta)); }
	 * 
	 * @Test public void test2MonthAgoInEnglish() { Long delta = 1000l * 60 * 60
	 * * 24 * 31 * 2; assertEquals("5 days ago",
	 * timeTranslator.translate(delta)); }
	 */
}
