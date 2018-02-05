package com.codurance.socialnetworking.app.command;

import java.util.GregorianCalendar;

import com.codurance.socialnetworking.app.storage.MessageStore;
import com.codurance.socialnetworking.app.storage.SubscriberStore;

public class PostCommand implements Command {

	private MessageStore messageStore;
	private SubscriberStore subscriberStore;

	public PostCommand(MessageStore msgStore, SubscriberStore subscriberStore) {
		this.messageStore = msgStore;
		this.subscriberStore = subscriberStore;
	}

	public String executeCommand(String username, String message) {
		messageStore.addMessage(username, message, new GregorianCalendar());
		// As soon as user post something, it becomes eligible for subscription.
		subscriberStore.addUserSubscriber(username);
		return "";
	}
}