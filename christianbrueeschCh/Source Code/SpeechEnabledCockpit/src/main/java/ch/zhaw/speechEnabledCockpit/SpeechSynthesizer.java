package ch.zhaw.speechEnabledCockpit;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;



public class SpeechSynthesizer {

	private static final String VOICENAME = "kevin16";

	private static VoiceManager voiceManager = VoiceManager.getInstance();
	
	public static void say(String word) {
		Voice voice = voiceManager.getVoice(VOICENAME);
		voice.allocate();
		voice.speak(word);
	}
	
	public static void beep() {
		java.awt.Toolkit.getDefaultToolkit().beep();
	}
	
	public static void doubleBeep() {
		beep();
		sleepFor(400);
		beep();
	}
	
	/**
	 * Thread sleeps for x ms
	 * 
	 * Paramter x: int ms
	 */
	private static void sleepFor(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
