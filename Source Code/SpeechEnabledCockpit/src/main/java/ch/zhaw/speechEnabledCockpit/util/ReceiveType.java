package ch.zhaw.speechEnabledCockpit.util;

public enum ReceiveType {
	BUTTON((short)1),
	CONFIRMATION((short)2),
	SAYSPEED(CommandType.SAYSPEED.getValue()),
	SAYFUEL(CommandType.SAYFUEL.getValue()),
	SAYENDURANCE(CommandType.SAYENDURANCE.getValue()),
	SAYALTITUDE(CommandType.SAYALTITUDE.getValue());
	
	
	private final short value;
	
	ReceiveType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
	
	public static ReceiveType getReceiveTypeFromShort(short type) {
	    if(BUTTON.getValue() == type) {
	    	return BUTTON;
	    } else if (CONFIRMATION.getValue()== type) {
	    	return CONFIRMATION;
	    } else if (SAYSPEED.getValue()== type) {
	    	return SAYSPEED;
	    } else if (SAYFUEL.getValue()== type) {
	    	return SAYFUEL;
	    } else if (SAYENDURANCE.getValue()== type) {
	    	return SAYENDURANCE;
	    } else if (SAYALTITUDE.getValue() == type) {
	    	return SAYALTITUDE;
	    }
		return null;
	}

}
