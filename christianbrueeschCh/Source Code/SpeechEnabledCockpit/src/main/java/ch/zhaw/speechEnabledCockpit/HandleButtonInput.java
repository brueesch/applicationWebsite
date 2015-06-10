package ch.zhaw.speechEnabledCockpit;

import ch.zhaw.speechEnabledCockpit.util.stateMachine.ConfirmCommand;
import ch.zhaw.speechEnabledCockpit.util.stateMachine.SayCommand;
import ch.zhaw.speechEnabledCockpit.util.stateMachine.State;

public class HandleButtonInput {
	
	private State[] buttonState = {new SayCommand(), new ConfirmCommand()};
	private int current = 0;
	
	private static HandleButtonInput instance;
	
	
	public static HandleButtonInput getInstance() {
		if(instance == null) {
			instance = new HandleButtonInput();
		}
		return instance;
	}
	
	private HandleButtonInput() {
		
	}
	
	public void next() {
		if(current == 0) {
			current = 1;
		} else {
			current = 0;
			buttonState[1].resetCount();
		}
	}
	
	public synchronized void handleButton(boolean pressed) {
		buttonState[current].handleButton(pressed);
	}
	
	public synchronized int getCount() {
		return buttonState[current].getCount();
	}
	
	

	

}
