package ch.zhaw.speechEnabledCockpit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestCommandsHandler {

	private CommandsHandler commandsHandler;

	@Before
	public void setUp() {
		try {
			commandsHandler = new CommandsHandlerImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHandleTurn() {
		boolean expected = true;
		boolean actual = commandsHandler.handleTurn("turn left heading 43");
		assertEquals(expected, actual);

		actual = commandsHandler.handleTurn("turn right heading 21");
		assertEquals(expected, actual);

		actual = commandsHandler.handleTurn("turn right heading 21.23");
		assertEquals(expected, actual);

		expected = false;
		actual = commandsHandler.handleTurn("turn heading");
		assertEquals(expected, actual);

		expected = false;
		actual = commandsHandler.handleTurn("turn left heading");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSpeed("");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleSpeed() {
		boolean expected = true;
		boolean actual = commandsHandler.handleSpeed("speed 45");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSpeed("speed 21.23");
		assertEquals(expected, actual);

		expected = false;
		actual = commandsHandler.handleSpeed("speed");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSpeed("speed full");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSpeed("");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleSet() {
		boolean expected = true;
		boolean actual = commandsHandler.handleSet("set climb power");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set cruise power");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set take off power");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set idle power");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set heading 123");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set course 123");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set course 123.23");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleSet("set cruise 123");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set climb 123");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("climb power");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("cruise power");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set cruise");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set climb");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set take off 123");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set idle 123");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("take off power");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("idle power");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set take off");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set idle");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set course power");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set heading power");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("course 123");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("heading 123");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set heading");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSet("set course");
		assertEquals(expected, actual);

	}

	@Test
	public void testHandleSay() {
		boolean expected = true;
		boolean actual = commandsHandler.handleSay("say speed");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSay("say endurance");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSay("say fuel");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSay("say altitude");
		assertEquals(expected, actual);

		expected = false;
		actual = commandsHandler.handleSay("say");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSay("speed");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSay("endurance");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSay("fuel");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSay("altitude");
		assertEquals(expected, actual);

		actual = commandsHandler.handleSay("");
		assertEquals(expected, actual);

	}

	@Test
	public void testHandlePropeller() {
		boolean expected = true;
		boolean actual = commandsHandler.handlePropeller("propeller 3000 rpm");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePropeller("propeller 2389 rpm");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePropeller("propeller 2389.21 rpm");
		assertEquals(expected, actual);

		expected = false;
		actual = commandsHandler.handlePropeller("propeller rpm");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePropeller("propeller");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePropeller("rpm");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePropeller("2380");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePropeller("");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandlePower() {
		boolean expected = true;

		boolean actual = commandsHandler.handlePower("power 41");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePower("power 100");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePower("power 86.3");
		assertEquals(expected, actual);

		expected = false;
		actual = commandsHandler.handlePower("power 123");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePower("power 40");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePower("power 101");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePower("power");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePower("1234");
		assertEquals(expected, actual);

		actual = commandsHandler.handlePower("");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleNav() {
		boolean expected = true;
		boolean actual = commandsHandler.handleNav("nav one set 123.452");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav two set 123.452");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav one standby 123.452");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav two standby 123.452");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav two switch frequency");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav two switch frequency");
		assertEquals(expected, actual);

		expected = false;
		// TODO: Gültiger Wertebereich
		// actual = commandsHandler.handleNav("nav one set 123.452");
		// assertEquals(expected, actual);
		//
		// actual = commandsHandler.handleNav("nav two set 123.452");
		// assertEquals(expected, actual);

		actual = commandsHandler.handleNav("");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav set");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav 123.45");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav one");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav one set");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav one 123.45");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav two");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav two set");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav two 123.45");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav standby");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav one standby");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav two standby");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav switch frequency");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav switch");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav frequency");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav one switch");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav two switch");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav one frequency");
		assertEquals(expected, actual);

		actual = commandsHandler.handleNav("nav two frequency");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleGear() {
		boolean expected = true;
		boolean actual = commandsHandler.handleGear("gear down");
		assertEquals(expected, actual);

		actual = commandsHandler.handleGear("gear up");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleGear(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleGear("gear");
		assertEquals(expected, actual);

		actual = commandsHandler.handleGear("up");
		assertEquals(expected, actual);

		actual = commandsHandler.handleGear("down");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleHeading() {
		boolean expected = true;
		boolean actual = commandsHandler.handleHeading("heading 123.12");
		assertEquals(expected, actual);

		expected = false;
		actual = commandsHandler.handleHeading("123.12");
		assertEquals(expected, actual);

		actual = commandsHandler.handleHeading(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleHeading("heading");
		assertEquals(expected, actual);

	}

	@Test
	public void testHandleFlightPathMarker() {
		boolean expected = true;
		boolean actual = commandsHandler
				.handleFlightPathMarker("flight path marker on");
		assertEquals(expected, actual);

		actual = commandsHandler
				.handleFlightPathMarker("flight path marker off");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleFlightPathMarker(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlightPathMarker("flight path marker");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlightPathMarker("off");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlightPathMarker("on");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleFlightDirector() {
		boolean expected = true;
		boolean actual = commandsHandler
				.handleFlightDirector("flight director on");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlightDirector("flight director off");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleFlightDirector(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlightDirector("flight director");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlightDirector("off");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlightDirector("on");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleFlaps() {
		boolean expected = true;
		boolean actual = commandsHandler.handleFlaps("flaps up");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlaps("flaps full");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlaps("flaps approach");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleFlaps(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlaps("flaps");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlaps("full");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlaps("up");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFlaps("approach");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleFinalCheck() {
		boolean expected = true;
		boolean actual = commandsHandler.handleFinalCheck("final check");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleFinalCheck(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFinalCheck("final");
		assertEquals(expected, actual);

		actual = commandsHandler.handleFinalCheck("check");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleDescendCheck() {
		boolean expected = true;
		boolean actual = commandsHandler.handleDescendCheck("descend check");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleDescendCheck(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleDescendCheck("descend");
		assertEquals(expected, actual);

		actual = commandsHandler.handleDescendCheck("check");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleCruiseCheck() {
		boolean expected = true;
		boolean actual = commandsHandler.handleCruiseCheck("cruise check");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleCruiseCheck(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCruiseCheck("cruise");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCruiseCheck("check");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleCom() {
		boolean expected = true;
		boolean actual = commandsHandler.handleCom("com one set 123.452");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com two set 123.452");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com one standby 123.452");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com two standby 123.452");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com two switch frequency");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com two switch frequency");
		assertEquals(expected, actual);

		expected = false;
		// TODO: Gültiger Wertebereich
		// actual = commandsHandler.handleCom("com one set 123.452");
		// assertEquals(expected, actual);
		//
		// actual = commandsHandler.handleCom("com two set 123.452");
		// assertEquals(expected, actual);

		actual = commandsHandler.handleCom("");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com set");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com 123.45");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com one");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com one set");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com one 123.45");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com two");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com two set");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com two 123.45");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com standby");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com one standby");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com two standby");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com switch frequency");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com switch");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com frequency");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com one switch");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com two switch");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com one frequency");
		assertEquals(expected, actual);

		actual = commandsHandler.handleCom("com two frequency");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleClimbCheck() {
		boolean expected = true;
		boolean actual = commandsHandler.handleClimbCheck("climb check");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleClimbCheck(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleClimbCheck("climb");
		assertEquals(expected, actual);

		actual = commandsHandler.handleClimbCheck("check");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleAutopilot() {
		boolean expected = true;
		boolean actual = commandsHandler.handleAutopilot("autopilot on");
		assertEquals(expected, actual);

		actual = commandsHandler.handleAutopilot("autopilot off");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleAutopilot(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleAutopilot("autopilot");
		assertEquals(expected, actual);

		actual = commandsHandler.handleAutopilot("off");
		assertEquals(expected, actual);

		actual = commandsHandler.handleAutopilot("on");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleApproachCheck() {
		boolean expected = true;
		boolean actual = commandsHandler.handleApproachCheck("approach check");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleApproachCheck(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleApproachCheck("approach");
		assertEquals(expected, actual);

		actual = commandsHandler.handleApproachCheck("check");
		assertEquals(expected, actual);
	}

	@Test
	public void testHandleAirBrakes() {
		boolean expected = true;
		boolean actual = commandsHandler.handleAirBrakes("air brakes on");
		assertEquals(expected, actual);

		actual = commandsHandler.handleAirBrakes("air brakes off");
		assertEquals(expected, actual);

		expected = false;

		actual = commandsHandler.handleAirBrakes(" ");
		assertEquals(expected, actual);

		actual = commandsHandler.handleAirBrakes("air brakes");
		assertEquals(expected, actual);

		actual = commandsHandler.handleAirBrakes("off");
		assertEquals(expected, actual);

		actual = commandsHandler.handleAirBrakes("on");
		assertEquals(expected, actual);
	}

}
