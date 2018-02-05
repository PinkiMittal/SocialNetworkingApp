package com.codurance.socialnetworking.app.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.codurance.socialnetworking.app.storage.SubscriberStore;

@RunWith(MockitoJUnitRunner.class)
public class FollowCommandTest {
	private static final String ALICE = "Alice";
	private static final String CHARLIE = "Charlie";

	@Test
	public void userFollowsAnotherUser() {
		SubscriberStore subscriberStore = mock(SubscriberStore.class);

		FollowCommand command = new FollowCommand(subscriberStore);

		command.executeCommand(ALICE, CHARLIE);

		verify(subscriberStore).updateUserSubscribers(ALICE, CHARLIE);
	}
	
	@Test
	public void twoUsersCanFollowEachOther() {
		SubscriberStore subscriberStore = mock(SubscriberStore.class);

		FollowCommand command = new FollowCommand(subscriberStore);

		command.executeCommand(ALICE, CHARLIE);
		command.executeCommand(CHARLIE, ALICE);

		verify(subscriberStore).updateUserSubscribers(ALICE, CHARLIE);
		verify(subscriberStore).updateUserSubscribers(CHARLIE, ALICE);
	}

	@Test
	public void userCanNotFollowItself() {
		SubscriberStore subscriberStore = mock(SubscriberStore.class);

		FollowCommand command = new FollowCommand(subscriberStore);

		command.executeCommand(ALICE, ALICE);

		verify(subscriberStore, never()).updateUserSubscribers(ALICE, ALICE);
	}
}
