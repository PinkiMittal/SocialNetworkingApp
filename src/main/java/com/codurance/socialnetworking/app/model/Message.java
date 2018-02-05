package com.codurance.socialnetworking.app.model;

import java.util.Calendar;

import com.codurance.socialnetworking.app.util.TimeTranslator;
import com.codurance.socialnetworking.app.util.EnglishTimeTranslator;

public class Message implements Comparable<Message> {

	private String description;
	private Calendar timeOfCreation;
	private String userName;
	private TimeTranslator timeTranslator;

	private Message(String userName, String description, Calendar timeOfCreation, TimeTranslator timeTranslator) {
		this.userName = userName;
		this.description = description;
		this.timeOfCreation = timeOfCreation;
		this.timeTranslator = timeTranslator;
	}

	public String description(String format, Calendar timeOfExecution) {
		return format.replace("%{username}", userName).replace("%{description}", description).replace("%{time}",
				deltaTime(timeOfExecution, timeOfCreation));
	}

	private String deltaTime(Calendar endTime, Calendar startTime) {
		Long delta = endTime.getTimeInMillis() - startTime.getTimeInMillis();
		return timeTranslator.translate(delta);
	}
	
	public static Message createInEnglish(String user, String description, Calendar time) {
		TimeTranslator timeTranslator = new EnglishTimeTranslator();
		return new Message(user, description, time, timeTranslator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Message anotherMessage) {
		return (int) (anotherMessage.timeOfCreation.getTimeInMillis() - timeOfCreation.getTimeInMillis());
	}
}