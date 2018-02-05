package com.codurance.socialnetworking.app.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.codurance.socialnetworking.app.model.Message;

public class MessagePrinter {
	private String formatMessage;

	public MessagePrinter() {
		formatMessage = "%{username} - %{description} (%{time})";
	}
	
	public MessagePrinter(String formatMessage) {
		this.formatMessage = formatMessage;
	}
	
	public String print(String username, List<Message> messages) {
		Calendar timeOfPrint = new GregorianCalendar();
		String output = "";
		if (messages.isEmpty())
			return output;
		
		String separator = "";
		for (Message message : messages) {
			output += separator + message.description(formatMessage, timeOfPrint);
			separator = "\n";
		}
		return output;
	}
}
