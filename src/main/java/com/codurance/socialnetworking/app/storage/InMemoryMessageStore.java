package com.codurance.socialnetworking.app.storage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import com.codurance.socialnetworking.app.model.Message;

public class InMemoryMessageStore implements MessageStore {

	private HashMap<String, TreeSet<Message>> messagesByUsername = new HashMap<String, TreeSet<Message>>();

	public List<Message> retrieveMessagesOrderedByTimeFor(String username) {
		ArrayList<Message> userMessages = new ArrayList<Message>();
		TreeSet<Message> messagesSet = messagesByUsername.get(username);
		if (messagesSet != null)
			userMessages.addAll(messagesSet);
		return userMessages;
	}

	private void initiateMessageSetForNewUser(String username) {
		if (messagesByUsername.get(username) == null)
			messagesByUsername.put(username, new TreeSet<Message>());
	}

	public void addMessage(String username, String message, Calendar timeOfExecution) {
		initiateMessageSetForNewUser(username);
		TreeSet<Message> messages = messagesByUsername.get(username);
		messages.add(Message.createInEnglish(username, message, timeOfExecution));
	}
}
