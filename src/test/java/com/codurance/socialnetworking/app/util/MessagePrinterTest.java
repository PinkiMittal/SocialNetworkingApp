package com.codurance.socialnetworking.app.util;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.codurance.socialnetworking.app.model.Message;

public class MessagePrinterTest {
	
	private static final String ALICE = "Alice";
	
	@Test
	public void testMessagePrinter() {
		MessagePrinter messagePrinter = new MessagePrinter();
		List<Message> messages = new ArrayList<Message>();
		messages.add(Message.createInEnglish(ALICE, "hi everyone", new GregorianCalendar()));
		String msgByAlice = messagePrinter.print(ALICE, messages);
		assertTrue(msgByAlice.indexOf("Alice - hi everyone (0 second ago)") >= 0);
	}

}

