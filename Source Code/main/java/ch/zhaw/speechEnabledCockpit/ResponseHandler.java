package ch.zhaw.speechEnabledCockpit;

public interface ResponseHandler {
	public boolean handleButton(float value);
	public boolean handleConfirmation();
	public boolean handleNotConfirmed();
	public boolean handleSaySpeed(float value);
	public boolean handleSayEndurance(float value);
	public boolean handleSayFuel(float value);
}
