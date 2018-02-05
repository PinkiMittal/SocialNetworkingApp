package com.codurance.socialnetworking.app.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import com.codurance.socialnetworking.app.model.Message;
import com.codurance.socialnetworking.app.storage.MessageStore;
import com.codurance.socialnetworking.app.storage.SubscriberStore;
import com.codurance.socialnetworking.app.util.MessagePrinter;

public class WallCommandTest {

	private static final String ANOTHER_USER = "another_user";
	private static final String A_USER = "user";
	private static final ArrayList<Message> AN_EMPTY_LIST = new ArrayList<Message>();
	private static final ArrayList<String> A_LIST_WITH_ANOTHER_USER = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add(ANOTHER_USER);
		}
	};

	private MessageStore messageStore;
	private WallCommand command;
	private SubscriberStore subscriberStore;
	private MessagePrinter printer;

	@Before
	public void setUp() {
		messageStore = mock(MessageStore.class);
		subscriberStore = mock(SubscriberStore.class);
		printer = mock(MessagePrinter.class);
		command = new WallCommand(messageStore, subscriberStore, printer);
	}

	@Test
	public void retrieveWhoFollowsTheUser() {
		command.executeCommand(A_USER, null);

		verify(subscriberStore).retrieveUserSubscribers(A_USER);
	}

	@Test
	public void retrieveMessagesOfUser() {
		command.executeCommand(A_USER, null);

		verify(messageStore).retrieveMessagesOrderedByTimeFor(A_USER);
	}

	@Test
	public void retrieveMessagesOfWhoFollowsTheUser() {
		when(subscriberStore.retrieveUserSubscribers(A_USER)).thenReturn(A_LIST_WITH_ANOTHER_USER);

		command.executeCommand(A_USER, null);

		verify(messageStore).retrieveMessagesOrderedByTimeFor(ANOTHER_USER);
	}

	@Test
	public void printWallOfUser() {
		command.executeCommand(A_USER, null);

		verify(printer).print(A_USER, AN_EMPTY_LIST);
	}
}
