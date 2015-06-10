package ch.zhaw.speechEnabledCockpit.util;

public enum ReceiveValue {
	TRUE(1.0f),
	FALSE(2.0f);
	
	
	private final float value;
	
	private ReceiveValue(float value) {
		this.value = value;
	}
	
	
	public float getValue() {
		return value;
	}
}
