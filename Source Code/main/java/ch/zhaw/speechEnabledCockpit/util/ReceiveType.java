package ch.zhaw.speechEnabledCockpit.util;

public enum ReceiveType {
	BUTTON((short)1),
	CONFIRMATION((short)2),
	NOTCONFIRMED((short)3),
	SAYSPEED(CommandType.SAYSPEED.getValue()),
	SAYFUEL(CommandType.SAYFUEL.getValue()),
	SAYENDURANCE(CommandType.SAYENDURANCE.getValue());
	
	
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
	    } else if (NOTCONFIRMED.getValue()== type) {
	    	return NOTCONFIRMED;
	    } else if (SAYSPEED.getValue()== type) {
	    	return SAYSPEED;
	    } else if (SAYFUEL.getValue()== type) {
	    	return SAYFUEL;
	    } else if (SAYENDURANCE.getValue()== type) {
	    	return SAYENDURANCE;
	    }
		return null;
	}

}
