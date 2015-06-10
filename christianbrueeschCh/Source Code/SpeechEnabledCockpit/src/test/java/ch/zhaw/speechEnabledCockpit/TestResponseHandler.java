package ch.zhaw.speechEnabledCockpit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.speechEnabledCockpit.util.ReceiveValue;

public class TestResponseHandler {
	private ResponseHandler responseHandler;

	@Before
	public void setUp() throws Exception {
		responseHandler = new ResponseHandlerImpl();
	}

	@Test
	public void testHandleButton() {
		boolean expected = true;
		boolean actual = responseHandler.handleButton(ReceiveValue.TRUE.getValue());
		assertEquals(expected, actual);
		
		actual = responseHandler.handleButton(ReceiveValue.FALSE.getValue());
		assertEquals(expected, actual);
		
		expected = false;
		actual = responseHandler.handleButton(-1);
		assertEquals(expected, actual);
		
		actual = responseHandler.handleButton(0);
		assertEquals(expected, actual);
		
		actual = responseHandler.handleButton(3);
		assertEquals(expected, actual);
		
		actual = responseHandler.handleButton(120);
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testHandleConfirmation() {
		responseHandler.handleConfirmation();
		//Assertion: You should hear: "Action confirmed"
	}
	
	@Test
	public void testHandleSay() {
		boolean expected = true;
		boolean actual = responseHandler.handleSay("speed is",340);
		assertEquals(expected, actual);
		
		actual = responseHandler.handleSay("altitude is",12450);
		assertEquals(expected, actual);
		
		actual = responseHandler.handleSay("speed is",1000);
		assertEquals(expected, actual);
		
		actual = responseHandler.handleSay("speed is",0);
		assertEquals(expected, actual);
		
		expected = false;
		actual = responseHandler.handleSay("speed is",-1);
		assertEquals(expected, actual);
		
		actual = responseHandler.handleSay("speed is",-123);
		assertEquals(expected, actual);
		
		
	}

}
