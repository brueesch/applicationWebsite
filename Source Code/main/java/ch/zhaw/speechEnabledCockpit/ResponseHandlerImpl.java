package ch.zhaw.speechEnabledCockpit;

import ch.zhaw.speechEnabledCockpit.util.ReceiveValue;

public class ResponseHandlerImpl implements ResponseHandler {
	

	//TODO: Handle Button
	@Override
	public boolean handleButton(float value) {
		if(value == ReceiveValue.TRUE.getValue()) {
			HandleButtonInput.getInstance().handleButton(true);
			return true;
		} else if (value == ReceiveValue.FALSE.getValue()) {
			HandleButtonInput.getInstance().handleButton(false);
			return true;
		}
		return false;
	}

	@Override
	public boolean handleConfirmation() {
		SpeechSynthesizer.doubleBeep();
		return true;
	}

	@Override
	public boolean handleNotConfirmed() {
		SpeechSynthesizer.say("Action not confirmed, something went wrong!");
		return true;
	}

	@Override
	public boolean handleSaySpeed(float value) {
		if(value>=0){
			SpeechSynthesizer.say("speed is "+(int) value);
			return true;
		}
		return false;
	}

	@Override
	public boolean handleSayEndurance(float value) {
		if(value>=0) {
			SpeechSynthesizer.say("The momentary Endurance is "+(int) value);
			return true;
		}
		return false;
	}

	@Override
	public boolean handleSayFuel(float value) {
		if(value>=0) {
			SpeechSynthesizer.say("The momentary Fuel is "+(int) value);
			return true;
		}
		return false;
	}

	
		
	

}
