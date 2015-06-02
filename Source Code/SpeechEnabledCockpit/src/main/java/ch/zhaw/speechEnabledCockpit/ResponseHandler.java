package ch.zhaw.speechEnabledCockpit;

public interface ResponseHandler {
	public boolean handleButton(float value);
	public boolean handleConfirmation();
	public boolean handleSay(String utterance, float value);
}
