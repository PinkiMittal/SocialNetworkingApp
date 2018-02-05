package com.codurance.socialnetworking.app.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.codurance.socialnetworking.app.model.Message;
import com.codurance.socialnetworking.app.storage.MessageStore;
import com.codurance.socialnetworking.app.util.MessagePrinter;

public class ReadCommandTest {

	private static final String A_USER = "user";
	private static final ArrayList<Message> AN_EMPTY_LIST = new ArrayList<Message>();

	private MessageStore messageStore;
	private ReadCommand command;
	private MessagePrinter printer;

	@Before
	public void setUp() {
		messageStore = mock(MessageStore.class);
		printer = mock(MessagePrinter.class);
		command = new ReadCommand(messageStore, printer);
	}

	@Test
	public void retrieveMessagesOfUser() {
		command.executeCommand(A_USER, null);
		verify(messageStore).retrieveMessagesOrderedByTimeFor(A_USER);
	}

	@Test
	public void printMessagesOfUser() {
		command.executeCommand(A_USER, null);
		verify(printer).print(A_USER, AN_EMPTY_LIST);
	}

}
