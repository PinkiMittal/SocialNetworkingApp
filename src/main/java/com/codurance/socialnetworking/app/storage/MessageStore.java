package com.codurance.socialnetworking.app.storage;

import java.util.Calendar;
import java.util.List;

import com.codurance.socialnetworking.app.model.Message;

public interface MessageStore {

	List<Message> retrieveMessagesOrderedByTimeFor(String username);

	void addMessage(String username, String message, Calendar timeOfExecution);
}
