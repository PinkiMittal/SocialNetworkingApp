package com.codurance.socialnetworking.app.command;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.codurance.socialnetworking.app.model.Message;
import com.codurance.socialnetworking.app.storage.MessageStore;
import com.codurance.socialnetworking.app.storage.SubscriberStore;

public class PostCommandTest {

	private static final String A_USER = "USER_A";
	private static final String A_MESSAGE = "message";
	private static final String B_MESSAGE = "b message";
	private static final String C_MESSAGE = "c message";
	private static final String D_MESSAGE = "d message";

	private MessageStore messageStore;
	private SubscriberStore subscriberStore;
	private PostCommand command;

	@Before
	public void setUp() {
		messageStore = mock(MessageStore.class);
		subscriberStore = mock(SubscriberStore.class);
		command = new PostCommand(messageStore, subscriberStore);
	}

	@Test
	public void firstPostForOneUserShouldSaveThatUserInMessageStore() {
		command.executeCommand(A_USER, A_MESSAGE);

		@SuppressWarnings("unchecked")
		List<Message> mockList = mock(ArrayList.class);
		when(mockList.size()).thenReturn(1);
		   
		when(messageStore.retrieveMessagesOrderedByTimeFor(A_USER)).thenReturn(mockList);
		
	}
	@Test
	public void postingFourMessageForOneUserShouldReturnFourMessgaeForThatUserInMessageStore() {
		command.executeCommand(A_USER, A_MESSAGE);
		command.executeCommand(A_USER, B_MESSAGE);
		command.executeCommand(A_USER, C_MESSAGE);
		command.executeCommand(A_USER, D_MESSAGE);

		
		verify(messageStore, times(4)).addMessage(any(String.class), any(String.class), any(GregorianCalendar.class));
		
	}

	@Test
	public void firstPostForOneUserShouldSaveThatUserInSubscriberStore() {
		command.executeCommand(A_USER, A_MESSAGE);

		verify(subscriberStore).addUserSubscriber(A_USER);
	}

	@Test
	public void addMessageCalledOnce() throws Exception {
		command.executeCommand(A_USER, A_MESSAGE);

		 //exact number of invocations verification
		verify(messageStore, times(1)).addMessage(any(String.class), any(String.class), any(GregorianCalendar.class));
		 
	}
	
}