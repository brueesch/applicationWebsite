/**
 * This enum defines the value of the Command Values in the Udp Package.
 * This values should only be changed, if they are accordingly changed on the D-Six.
 * 
 * 
 * Author: Christian Brüesch Date: 03.05.2015
 */
package ch.zhaw.speechEnabledCockpit.util;

public enum CommandValues {
	ON(1.0f),
	OFF(2.0f),
	UP(0.0f),
	DOWN(1.0f),
	APPROACH(1.0f),
	FULL(2.0f),
	NOVALUE(100.0f);
	
	
	private final float value;
	
	CommandValues(float value) {
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}

}
