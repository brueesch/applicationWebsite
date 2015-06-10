package ch.zhaw.speechEnabledCockpit.util.stateMachine;

import java.util.logging.Logger;


public class ConfirmCommand extends State {

	private int count = 0;

	private final static Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Override
	public void handleButton(boolean pressed) {
		if (pressed) {
			count++;
			LOGGER.info("Current button count: "+count);
		}
	}

	public int getCount() {
		return count;
	}

	public void resetCount() {
		count = 0;
	}

}
