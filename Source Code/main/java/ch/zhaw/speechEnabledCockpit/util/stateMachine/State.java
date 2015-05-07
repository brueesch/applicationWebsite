package ch.zhaw.speechEnabledCockpit.util.stateMachine;

import java.util.logging.Logger;

public abstract class State {
	private final static Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public void handleButton(boolean pressed) {
		LOGGER.info("Something went Wrong with the Button states");
		
	}
	
	public int getCount() {
		LOGGER.info("Something went Wrong with the Button states");
		return 0;
	}
	
	public void resetCount() {
		LOGGER.info("Something went Wrong with the Button states");
	}

}
