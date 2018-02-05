package com.codurance.socialnetworking.app;

import java.util.Scanner;

import com.codurance.socialnetworking.app.command.CommandDispatcher;
import com.codurance.socialnetworking.app.storage.InMemoryMessageStore;
import com.codurance.socialnetworking.app.storage.InMemorySubscriberStore;
import com.codurance.socialnetworking.app.storage.MessageStore;
import com.codurance.socialnetworking.app.storage.SubscriberStore;

public class App {
	private SocialNetworkingClient client;

	public App(SocialNetworkingClient client) {
		this.client = client;
	}

	public static void main(String[] args) {
		CommandDispatcher commandDispatcher = createCommandDispatcher();
		SocialNetworkingClient client = new SocialNetworkingClient(commandDispatcher);
		App app = new App(client);
		app.run();
	}

	private static CommandDispatcher createCommandDispatcher() {
		MessageStore messageStore = new InMemoryMessageStore();
		SubscriberStore subscriberStore = new InMemorySubscriberStore();
		return new CommandDispatcher(messageStore, subscriberStore);
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		String command;
		System.out.print("> ");
		try {
			while ((command = scanner.nextLine()) != null) {
				String result = client.process(command);

				if (!result.isEmpty())
					System.out.println(result);

				System.out.print("> ");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			scanner.close();
		}
		
	}
}
