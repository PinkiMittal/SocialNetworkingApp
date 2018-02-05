package com.codurance.socialnetworking.app.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CommandDispatcherTest {
	
	private CommandDispatcher commandDispatcher;

	@Before
	public void setUp() {
		commandDispatcher = new CommandDispatcher(null, null);
	}
	
	
	@Test
	public void nullParameterShouldDispatchReadCommand() throws Exception {
		Command command = commandDispatcher.dispatchCommand(null);
		
		assertEquals(ReadCommand.class, command.getClass());
	}

	@Test
	public void postingParameterShouldDispatchPostCommand() throws Exception {
		Command command = commandDispatcher.dispatchCommand("->");
		
		assertEquals(PostCommand.class, command.getClass());
	}

	@Test
	public void followsParameterShouldDispatchPostCommand() throws Exception {
		Command command = commandDispatcher.dispatchCommand("follows");
		
		assertEquals(FollowCommand.class, command.getClass());
	}
	
	@Test
	public void wallParameterShouldDispatchPostCommand() throws Exception {
		Command command = commandDispatcher.dispatchCommand("wall");
		
		assertEquals(WallCommand.class, command.getClass());
	}
}
