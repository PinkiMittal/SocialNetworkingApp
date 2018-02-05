package com.codurance.socialnetworking.app.command;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.codurance.socialnetworking.app.SocialNetworkingClient;
import com.codurance.socialnetworking.app.storage.InMemoryMessageStore;
import com.codurance.socialnetworking.app.storage.InMemorySubscriberStore;
import com.codurance.socialnetworking.app.storage.MessageStore;
import com.codurance.socialnetworking.app.storage.SubscriberStore;

@RunWith(MockitoJUnitRunner.class)
public class MixedCommandTest {

	private static final String ALICE = "Alice";
	private static final String CHARLIE = "Charlie";
	private static final String BOB = "Bob";

	private MessageStore messageStore = mock(MessageStore.class);
	private SubscriberStore subscriberStore = mock(SubscriberStore.class);

	@Test
	public void createTwoUsersAndFollowsEachOther() {
		CommandDispatcher commandDispatcher = new CommandDispatcher(messageStore, subscriberStore);
		Command postCommand = new PostCommand(messageStore, subscriberStore);
		Command followCommand = new FollowCommand(subscriberStore);

		postCommand.executeCommand(ALICE, "Hello There");
		postCommand.executeCommand(CHARLIE, "Hi Everyone");
		followCommand.executeCommand(ALICE, CHARLIE);
		followCommand.executeCommand(CHARLIE, ALICE);

		verify(subscriberStore).updateUserSubscribers(ALICE, CHARLIE);
		verify(subscriberStore).updateUserSubscribers(CHARLIE, ALICE);
		List<String> userList = new ArrayList<String>();
		userList.add(ALICE);
		given(subscriberStore.retrieveUserSubscribers(CHARLIE)).willReturn(userList);
		userList.clear();
	}

	@Test
	public void checkingWholeSecnaioBehaviourTest() {

		MessageStore messageStore = new InMemoryMessageStore();
		SubscriberStore subscriberStore = new InMemorySubscriberStore();
		CommandDispatcher commandDispatcher = new CommandDispatcher(messageStore, subscriberStore);
		SocialNetworkingClient client = new SocialNetworkingClient(commandDispatcher);

		StringBuilder result = new StringBuilder();
		client.process("Alice -> I love the weather today");
		client.process("Bob -> Damn! We lost!");
		client.process("Bob -> Good game though.");

		result.append(client.process(ALICE));
		assertTrue(result.lastIndexOf("I love the weather today") >= 0);
		result.delete(0, result.length());

		result.append(client.process(BOB));
		//assertTrue(result.lastIndexOf("Bob - Good game though.") >= 0);
		//assertTrue(result.lastIndexOf("Bob - Damn! We lost!") >= 0);

		client.process("Charlie -> I'm in New York today! Anyone want to have a coffee?");
		client.process("Charlie follows Alice");
		result.append(client.process("Charlie wall"));

		//assertTrue(result.lastIndexOf("Charlie - I am in New York today! Anyone want to have a coffee?") >= 0);
		//assertTrue(result.lastIndexOf("Alice - I love the weather today") >= 0);

		client.process("Charlie follows Bob");
		result.append(client.process("Charlie wall"));
		//assertTrue(result.lastIndexOf("Charlie - I am in New York today! Anyone wants to have a coffee?") >= 0);
		//assertTrue(result.lastIndexOf("Bob - Good game though.") >= 0);
		//assertTrue(result.lastIndexOf("Bob - Damn! We lost!") >= 0);
		//assertTrue(result.lastIndexOf("Alice - I love the weather today") >= 0);

	}
}
