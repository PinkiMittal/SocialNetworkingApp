package com.codurance.socialnetworking.app.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.codurance.socialnetworking.app.model.Message;
import com.codurance.socialnetworking.app.storage.MessageStore;
import com.codurance.socialnetworking.app.storage.SubscriberStore;
import com.codurance.socialnetworking.app.util.MessagePrinter;

public class WallCommand implements Command {

	private MessageStore messageStore;
	private SubscriberStore subscriberStore;
	private MessagePrinter printer;

	public WallCommand(MessageStore messageStore, SubscriberStore subscriberStore) {
		this.messageStore = messageStore;
		this.subscriberStore = subscriberStore;
		this.printer = new MessagePrinter();
	}

	public WallCommand(MessageStore messageStore, SubscriberStore subscriberStore, MessagePrinter printer) {
		this.messageStore = messageStore;
		this.subscriberStore = subscriberStore;
		this.printer = printer;
	}

	/**
	 * @param username
	 * @param argument
	 *            should pass null always, created to adhere command pattern.
	 * @param timeOfExecution
	 *            time to see wall.
	 * @return messages on wall.
	 */
	public String executeCommand(String username, String argument) {
		List<String> subscriberList = retrieveSubscribersOfUser(username);
		List<Message> messages = retrieveMessagesFromSubscribers(subscriberList);
		Collections.sort(messages);
		return printer.print(username, messages);
	}

	private List<String> retrieveSubscribersOfUser(String username) {
		List<String> subscribersList = subscriberStore.retrieveUserSubscribers(username);
		subscribersList.add(username);
		return subscribersList;
	}

	private List<Message> retrieveMessagesFromSubscribers(List<String> subscribersList) {
		List<Message> messages = new ArrayList<Message>();
		for (String user : subscribersList) {
			List<Message> messagesOfUser = messageStore.retrieveMessagesOrderedByTimeFor(user);
			messages.addAll(messagesOfUser);
		}
		return messages;
	}

}
