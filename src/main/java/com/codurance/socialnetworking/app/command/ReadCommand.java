package com.codurance.socialnetworking.app.command;

import java.util.List;

import com.codurance.socialnetworking.app.model.Message;
import com.codurance.socialnetworking.app.storage.MessageStore;
import com.codurance.socialnetworking.app.util.MessagePrinter;

public class ReadCommand implements Command {

	private MessageStore messageStore;
	private MessagePrinter printer;

	public ReadCommand(MessageStore messageStore, MessagePrinter printer) {
		this.messageStore = messageStore;
		this.printer = printer;
	}

	
	/* 
	 * 
	 */
	public String executeCommand(String username, String argument) {
		List<Message> messages = messageStore.retrieveMessagesOrderedByTimeFor(username);
		return printer.print(username, messages);
	}

}