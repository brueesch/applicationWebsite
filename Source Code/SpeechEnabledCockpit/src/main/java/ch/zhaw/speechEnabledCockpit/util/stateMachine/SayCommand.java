package ch.zhaw.speechEnabledCockpit.util.stateMachine;

import java.util.logging.Logger;

import ch.zhaw.speechEnabledCockpit.SpeechRecognizer;
import ch.zhaw.speechEnabledCockpit.SpeechSynthesizer;

public class SayCommand extends State {
	
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Override
	public void handleButton(boolean pressed) {
		if (pressed) {
			SpeechSynthesizer.beep();
		}
		LOGGER.info("set Button Pressed: "+pressed);
		SpeechRecognizer.setButtonPressed(pressed);
	}

}
