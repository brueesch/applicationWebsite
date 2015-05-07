/**
 * CommandsHandlerImpl.java
 * 
 * This class determines the command spoken and gives the correct data to the UdpTransmitter.
 * 
 * Author: Christian Brüesch Date: 03.05.2015
 */
package ch.zhaw.speechEnabledCockpit;

import java.util.logging.Logger;

import ch.zhaw.speechEnabledCockpit.udp.UdpTransmitter;
import ch.zhaw.speechEnabledCockpit.util.CommandType;
import ch.zhaw.speechEnabledCockpit.util.CommandValues;

public class CommandsHandlerImpl implements CommandsHandler {
	private UdpTransmitter udpTransmitter = new UdpTransmitter();
	private final static Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);


	public boolean handleTurn(String utterance) {
		LOGGER.info(utterance);
		if (utterance.matches("turn right heading [0-9]+.?[0-9]*")) {
			float value = getValueOutOfUtterance(utterance);
			udpTransmitter.createUdp(CommandType.TURNRIGHTHEADING.getValue(), value);
			return true;
		} else if (utterance.matches("turn left heading [0-9]+.?[0-9]*")) {
			float value = getValueOutOfUtterance(utterance);
			udpTransmitter.createUdp(CommandType.TURNLEFTHEADING.getValue(),value );
			return true;
		} else {
			return false;
		}
	}

	public boolean handleSpeed(String utterance) {
		LOGGER.info(utterance);
		if (utterance.matches("speed [0-9]+.?[0-9]*")) {
			float value = getValueOutOfUtterance(utterance);
			udpTransmitter.createUdp(CommandType.SPEED.getValue(), value);
			return true;
		}
		return false;
	}

	public boolean handleSet(String utterance) {
		LOGGER.info(utterance);
		if (utterance.matches("set climb power")) {
			udpTransmitter.createUdp(CommandType.SETCLIMBPOWER.getValue(), CommandValues.NOVALUE.getValue());
			return true;
		} else if (utterance.matches("set cruise power")) {
			udpTransmitter.createUdp(CommandType.SETCRUISEPOWER.getValue(), CommandValues.NOVALUE.getValue());
			return true;
		} else if (utterance.matches("set heading [0-9]+.?[0-9]*")) {
			float value = getValueOutOfUtterance(utterance);
			udpTransmitter.createUdp(CommandType.SETHEADING.getValue(), value);
			return true;
		} else if (utterance.matches("set course [0-9]+.?[0-9]*")) {
			float value = getValueOutOfUtterance(utterance);
			udpTransmitter.createUdp(CommandType.SETCOURSE.getValue(), value);
			return true;
		}

		return false;
	}

	public boolean handleSay(String utterance) {
		LOGGER.info(utterance);
		if (utterance.equalsIgnoreCase("say speed")) {
			udpTransmitter.createUdp(CommandType.SAYSPEED.getValue(), CommandValues.NOVALUE.getValue());
			return true;
		} else if (utterance.equalsIgnoreCase("say fuel")) {
			udpTransmitter.createUdp(CommandType.SAYFUEL.getValue(), CommandValues.NOVALUE.getValue());
			return true;
		} else if (utterance.equalsIgnoreCase("say endurance")) {
			udpTransmitter.createUdp(CommandType.SAYENDURANCE.getValue(), CommandValues.NOVALUE.getValue());
			return true;
		}
		return false;
	}

	public boolean handlePropeller(String utterance) {
		LOGGER.info(utterance);
		if (utterance.matches("propeller [0-9]+.?[0-9]* rpm")) {
			float value = getValueOutOfUtterance(utterance);
			udpTransmitter.createUdp(CommandType.PROPELLER.getValue(), value);
			return true;
		}
		return false;
	}

	public boolean handlePower(String utterance) {
		LOGGER.info(utterance);
		if (utterance.matches("power [0-9]+.?[0-9]*")) {
			float value = getValueOutOfUtterance(utterance);
			udpTransmitter.createUdp(CommandType.POWER.getValue(), value);
			return true;
		}
		return false;
	}

	//TODO: Handle NAV
	public boolean handleNav(String utterance) {
		LOGGER.info(utterance);
		if (checkIfAllowedComOrNavCommand(utterance)) {
			if (isSet(utterance)) {

				return determineChannel(utterance);

			}
			if (isStandby(utterance)) {
				return determineChannel(utterance);
			}

			if (isSwitchFrequency(utterance)) {
				return determineChannel(utterance);
			}
		}
		return false;
	}

	public boolean handleGear(String utterance) {
		LOGGER.info(utterance);
		if (utterance.equalsIgnoreCase("gear down")) {
			confirmCommand(utterance, CommandType.GEAR.getValue(), CommandValues.DOWN.getValue());
			return true;
		} else if (utterance.equalsIgnoreCase("gear up")) {
			confirmCommand(utterance, CommandType.GEAR.getValue(), CommandValues.UP.getValue());
			return true;
		}
		return false;
	}

	

	public boolean handleHeading(String utterance) {
		LOGGER.info(utterance);
		if (utterance.matches("heading [0-9]+.?[0-9]*")) {
			float value = getValueOutOfUtterance(utterance);
			udpTransmitter.createUdp(CommandType.HEADING.getValue(), value);
			return true;
		}
		return false;
	}

	public boolean handleFlightPathMarker(String utterance) {
		LOGGER.info(utterance);
		if (utterance.equalsIgnoreCase("flight path marker on")) {
			udpTransmitter.createUdp(CommandType.FLIGHTPATHMARKER.getValue(), CommandValues.ON.getValue());
			return true;
		} else if (utterance.equalsIgnoreCase("flight path marker off")) {
			udpTransmitter.createUdp(CommandType.FLIGHTPATHMARKER.getValue(), CommandValues.OFF.getValue());
			return true;
		}
		return false;
	}

	public boolean handleFlightDirector(String utterance) {
		LOGGER.info(utterance);
		if (utterance.equalsIgnoreCase("flight director on")) {
			udpTransmitter.createUdp(CommandType.FLIGHTDIRECTOR.getValue(), CommandValues.ON.getValue());
			return true;
		} else if (utterance.equalsIgnoreCase("flight director off")) {
			udpTransmitter.createUdp(CommandType.FLIGHTDIRECTOR.getValue(), CommandValues.OFF.getValue());
			return true;
		}
		return false;
	}

	public boolean handleFlaps(String utterance) {
		LOGGER.info(utterance);
		if (utterance.equalsIgnoreCase("flaps up")) {
			confirmCommand(utterance, CommandType.FLAPS.getValue(), CommandValues.UP.getValue());
			return true;
		} else if (utterance.equalsIgnoreCase("flaps approach")) {
			confirmCommand(utterance, CommandType.FLAPS.getValue(), CommandValues.APPROACH.getValue());
			return true;
		} else if (utterance.equalsIgnoreCase("flaps full")) {
			confirmCommand(utterance, CommandType.FLAPS.getValue(), CommandValues.FULL.getValue());
			return true;
		}
		return false;
	}

	public boolean handleFinalCheck(String utterance) {
		LOGGER.info(utterance);
		if (utterance.equalsIgnoreCase("final check")) {
			udpTransmitter.createUdp(CommandType.FINALCHECK.getValue(), CommandValues.NOVALUE.getValue());
			return true;
		}
		return false;
	}

	public boolean handleDescendCheck(String utterance) {
		LOGGER.info(utterance);
		if (utterance.equalsIgnoreCase("descend check")) {
			udpTransmitter.createUdp(CommandType.DESCENDCHECK.getValue(), CommandValues.NOVALUE.getValue());
			return true;
		}
		return false;
	}

	public boolean handleCruiseCheck(String utterance) {
		LOGGER.info(utterance);
		if (utterance.equalsIgnoreCase("cruise check")) {
			udpTransmitter.createUdp(CommandType.CRUISECHECK.getValue(), CommandValues.NOVALUE.getValue());
			return true;
		}
		return false;
	}

	//TODO: Handle Com
	public boolean handleCom(String utterance) {
		LOGGER.info(utterance);
		if (checkIfAllowedComOrNavCommand(utterance)) {
			if (isSet(utterance)) {
				return determineChannel(utterance);
			}
			if (isStandby(utterance)) {
				return determineChannel(utterance);
			}
			if (isSwitchFrequency(utterance)) {
				return determineChannel(utterance);
			}
		}
		return false;

	}

	public boolean handleClimbCheck(String utterance) {
		LOGGER.info(utterance);
		if (utterance.equalsIgnoreCase("climb check")) {
			udpTransmitter.createUdp(CommandType.CLIMBCHECK.getValue(), CommandValues.NOVALUE.getValue());
			return true;
		}
		return false;
	}

	public boolean handleAutopilot(String utterance) {
		LOGGER.info(utterance);
		if(utterance.equalsIgnoreCase("autopilot on")) {
			udpTransmitter.createUdp(CommandType.AUTOPILOT.getValue(), CommandValues.ON.getValue());
			return true;
		} else if (utterance.equalsIgnoreCase("autopilot off")) {
			udpTransmitter.createUdp(CommandType.AUTOPILOT.getValue(), CommandValues.OFF.getValue());
			return true;
		}
		return false;
	}

	public boolean handleApproachCheck(String utterance) {
		LOGGER.info(utterance);
		if (utterance.equalsIgnoreCase("approach check")) {
			udpTransmitter.createUdp(CommandType.APPROACHCHECK.getValue(), CommandValues.NOVALUE.getValue());
			return true;
		}
		return false;
	}

	public boolean handleAirBrakes(String utterance) {
		LOGGER.info(utterance);
		if(utterance.equalsIgnoreCase("air brakes on")) {
			udpTransmitter.createUdp(CommandType.AIRBRAKES.getValue(), CommandValues.ON.getValue());
			return true;
		} else if (utterance.equalsIgnoreCase("air brakes off")) {
			udpTransmitter.createUdp(CommandType.AIRBRAKES.getValue(), CommandValues.OFF.getValue());
			return true;
		}
		return false;
	}

	private boolean checkIfAllowedComOrNavCommand(String utterance) {
		if (utterance
				.matches("(nav|com) (one|two) (set [0-9]+.?[0-9]*|switch frequency|standby [0-9]+.?[0-9]*)")) {
			return true;
		}
		return false;
	}

	private boolean determineChannel(String utterance) {
		String channel = utterance.split(" ")[1];
		if (channel.equalsIgnoreCase("one")) {
			return true;
		} else if (channel.equalsIgnoreCase("two")) {
			return true;
		}
		return false;
	}

	private boolean isSwitchFrequency(String utterance) {
		if (utterance.contains("switch frequency")) {
			return true;
		}
		return false;
	}

	private boolean isStandby(String utterance) {
		if (utterance.contains("standby")) {
			return true;
		}
		return false;
	}

	private boolean isSet(String utterance) {
		if (utterance.contains("set")) {
			return true;
		}
		return false;
	}
	

	private float getValueOutOfUtterance(String utterance) {
		float result = Float.valueOf(utterance.replaceAll("[^\\d.]+|\\.(?!\\d)", ""));
		return result;
	}
	
	private void confirmCommand(String command, short commandType, float value) {
		
		HandleButtonInput.getInstance().next();
		SpeechSynthesizer.say("confirm "+command);
		sleepFor(1500);
		if(HandleButtonInput.getInstance().getCount()==2) {
			SpeechSynthesizer.doubleBeep();
			udpTransmitter.createUdp(commandType, value);
		}
		HandleButtonInput.getInstance().next();
	}
	
	

	private void sleepFor(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
