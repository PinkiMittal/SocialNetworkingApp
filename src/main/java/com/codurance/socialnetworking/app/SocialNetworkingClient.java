package com.codurance.socialnetworking.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codurance.socialnetworking.app.command.Command;
import com.codurance.socialnetworking.app.command.CommandDispatcher;

public class SocialNetworkingClient {

	private CommandDispatcher commandFactory;

	public SocialNetworkingClient(CommandDispatcher commandDispatcher) {
		this.commandFactory = commandDispatcher;
	}

	public String process(String commandToInterpret) {
		String result = null;
		Pattern pattern = Pattern.compile("(\\w+)(\\s(->|follows|wall)(\\s(.*))*)*");
		Matcher matcher = pattern.matcher(commandToInterpret);
		if (matcher.matches()) {

			String username = matcher.group(1);
			String action = matcher.group(3);
			String arguments = matcher.group(5);

			Command command = commandFactory.dispatchCommand(action);
			result = command.executeCommand(username, arguments);
		}
		return result;
	}
}
