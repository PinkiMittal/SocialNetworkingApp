package com.codurance.socialnetworking.app.util;

public class EnglishTimeTranslator implements TimeTranslator {

	public String translate(Long timeInMills) {

		StringBuilder timeInEnglish = new StringBuilder();
		Integer numberOfSeconds = (int) (timeInMills / 1000);
		Integer numberOfMinutes = numberOfSeconds / 60;
		Integer numberOfHours = numberOfMinutes / 60;
		Integer numberOfDays = numberOfHours / 24;
		//TODO for month and years.

		TimeDescription[] timeDescriptions = new TimeDescription[] { new TimeDescription(numberOfDays, TimeType.DAY),
				new TimeDescription(numberOfHours, TimeType.HOUR),
				new TimeDescription(numberOfMinutes, TimeType.MINUTE),
				new TimeDescription(numberOfSeconds, TimeType.SECOND) };

		for (TimeDescription timeDescription : timeDescriptions)
			if (timeDescription.isMoreThanZero())
				return timeInEnglish.append(timeDescription.description()).append(" ago").toString();

		return timeInEnglish.append(TimeDescription.ZERO_SECOND.description()).append(" ago").toString();

	}

}