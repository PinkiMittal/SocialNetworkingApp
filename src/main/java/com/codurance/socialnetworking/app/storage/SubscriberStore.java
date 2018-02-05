package com.codurance.socialnetworking.app.storage;

import java.util.List;

public interface SubscriberStore {

	void addUserSubscriber(String username);
	
	void updateUserSubscribers(String username, String followedUsername);
	
	List<String> retrieveUserSubscribers(String username);	

}