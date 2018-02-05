package com.codurance.socialnetworking.app.command;

import java.util.HashMap;
import java.util.Map;

import com.codurance.socialnetworking.app.storage.MessageStore;
import com.codurance.socialnetworking.app.storage.SubscriberStore;
import com.codurance.socialnetworking.app.util.MessagePrinter;

public class CommandDispatcher {
	private Map<String, Command> commandDispatcher;

	public CommandDispatcher(MessageStore messageStore, SubscriberStore subscriberStore) {
		commandDispatcher = new HashMap<String, Command>();
		commandDispatcher.put(null, new ReadCommand(messageStore, new MessagePrinter()));
		commandDispatcher.put("->", new PostCommand(messageStore, subscriberStore));
		commandDispatcher.put("follows", new FollowCommand(subscriberStore));
		commandDispatcher.put("wall", new WallCommand(messageStore, subscriberStore, new MessagePrinter()));
	}

	public Command dispatchCommand(String action) {
		return commandDispatcher.get(action);
	}
}