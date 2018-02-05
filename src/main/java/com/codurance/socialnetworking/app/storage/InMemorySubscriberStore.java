package com.codurance.socialnetworking.app.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.codurance.socialnetworking.app.exceptions.UserNotExistsException;

public class InMemorySubscriberStore implements SubscriberStore {

	private Map<String, Set<String>> userSubscribers = new HashMap<String, Set<String>>();

	public void addUserSubscriber(String username) {
		if (userSubscribers.get(username) == null)
			userSubscribers.put(username, new HashSet<String>());
	}
	
	public void updateUserSubscribers(String username, String subscriber) {
		Set<String> subscriberToUpdateSet = userSubscribers.get(username);

        if(subscriberToUpdateSet == null)
            throw new UserNotExistsException();

		subscriberToUpdateSet.add(subscriber);
	}

	public List<String> retrieveUserSubscribers(String username) {
		Set<String> subscriptions = userSubscribers.get(username);
		ArrayList<String> result = new ArrayList<String>();

		if (subscriptions != null)
			result.addAll(subscriptions);

		return result;
	}
}
