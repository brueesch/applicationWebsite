/**
 * This enum defines the value of the Command Type position in the Udp Package.
 * This values should only be changed, if they are accordingly changed on the D-Six.
 * 
 * 
 * Author: Christian Brüesch Date: 03.05.2015
 */
package ch.zhaw.speechEnabledCockpit.util;

public enum CommandType {
	FLAPS ((short) 1), 
	GEAR((short)2),
	POWER((short)3), 
	PROPELLER((short)4), 
	COM((short)6), 
	NAV((short)7), 
	CLIMBCHECK((short)8), 
	CRUISECHECK((short)9), 
	DESCENDCHECK((short)10), 
	APPROACHCHECK((short)11), 
	FINALCHECK((short)12), 
	FLIGHTDIRECTOR((short)14), 
	FLIGHTPATHMARKER((short)15),
	AUTOPILOT((short)16),
	AIRBRAKES((short)17),
	SPEED((short)18),
	HEADING((short)19),
	TURNRIGHTHEADING((short)20),
	TURNLEFTHEADING((short)21),
	SETHEADING((short)24),
	SETCOURSE((short)25),
	SAYSPEED((short)26),
	SAYENDURANCE((short)27),
	SAYFUEL((short)28),
	SAYALTITUDE((short)29);
	
	
	private final short value;
	
	CommandType(short value) {
		this.value = value;
	}
	
	public short getValue() {
		return value;
	}
}
