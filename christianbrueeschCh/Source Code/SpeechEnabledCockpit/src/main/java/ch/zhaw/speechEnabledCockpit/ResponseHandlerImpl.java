package ch.zhaw.speechEnabledCockpit;

import ch.zhaw.speechEnabledCockpit.util.ReceiveValue;

public class ResponseHandlerImpl implements ResponseHandler {

	@Override
	public boolean handleButton(float value) {
		if (value == ReceiveValue.TRUE.getValue()) {
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
	public boolean handleSay(String utterance, float value) {
		if (value >= 0) {
			SpeechSynthesizer.say(utterance + (int) value);
			return true;
		}
		return false;
	}

}
