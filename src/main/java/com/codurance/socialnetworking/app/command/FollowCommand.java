package com.codurance.socialnetworking.app.command;

import com.codurance.socialnetworking.app.exceptions.UserNotExistsException;
import com.codurance.socialnetworking.app.storage.SubscriberStore;

public class FollowCommand implements Command {

	private SubscriberStore subscriberStore;

	public FollowCommand(SubscriberStore subscriberStore) {
		this.subscriberStore = subscriberStore;
	}

	public String executeCommand(String username, String followedUsername) {
		try {
			if (areDifferentUsers(username, followedUsername))
				subscriberStore.updateUserSubscribers(username, followedUsername);
			return "";
		} catch (UserNotExistsException e) {
			return "Something went wrong " + username + ". Please be sure that user (" + followedUsername
					+ ") you want to subscribe, exists.";
		}
	}

	private boolean areDifferentUsers(String username, String followedUsername) {
		return !username.equals(followedUsername);
	}
}
