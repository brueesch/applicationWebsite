/*
 * Copyright 2013 Carnegie Mellon University.
 * Portions Copyright 2004 Sun Microsystems, Inc.
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 */

package ch.zhaw.speechEnabledCockpit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import ch.zhaw.speechEnabledCockpit.view.Main;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class SpeechRecognizer implements Runnable {

	private static final String ACOUSTIC_MODEL = "resource:/edu/cmu/sphinx/models/en-us/en-us";
	private static final String DICTIONARY_PATH = "resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict";
	private static final String GRAMMAR_PATH = "resource:/dialogs/";
	

	private CommandsHandler commandsHandler;
	private static boolean buttonIsPressed = false;
	private final static Object buttonLock = new Object();
	private final static Object programLock = new Object();
	private static boolean programIsRunning = true;

	private final static Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private static final Map<String, Integer> DIGITS = new HashMap<String, Integer>();
	

	static {
		DIGITS.put("oh", 0);
		DIGITS.put("zero", 0);
		DIGITS.put("one", 1);
		DIGITS.put("two", 2);
		DIGITS.put("three", 3);
		DIGITS.put("four", 4);
		DIGITS.put("five", 5);
		DIGITS.put("six", 6);
		DIGITS.put("seven", 7);
		DIGITS.put("eight", 8);
		DIGITS.put("nine", 9);
	}

	

	@Override
	public void run() {
		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath(ACOUSTIC_MODEL);
		configuration.setDictionaryPath(DICTIONARY_PATH);
		configuration.setGrammarPath(GRAMMAR_PATH);
		configuration.setUseGrammar(true);

		configuration.setGrammarName("dialog2");

		commandsHandler = new CommandsHandlerImpl();

		// StreamSpeechRecognizer cockpitRecognizer = new
		// StreamSpeechRecognizer(configuration);
		// InputStream stream =
		// InputStream.class.getResourceAsStream("/edu/cmu/sphinx/cockpit/recordings/recording_Flaps_approach.wav");
		// TranscriberDemo.class.getResourceAsStream("/edu/cmu/sphinx/cockpit/recordings/recording_Flaps_approach.wav");
		// stream.skip(500);
		// recognizerCockpit(cockpitRecognizer, stream);

		LiveSpeechRecognizer cockpitRecognizer = null;
		try {
			cockpitRecognizer = new LiveSpeechRecognizer(configuration);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recognizerCockpit(cockpitRecognizer);
		
	}

	// private void recognizerCockpit(StreamSpeechRecognizer recognizer,
	// InputStream stream) {
	private void recognizerCockpit(LiveSpeechRecognizer recognizer) {
		Main.outputArea.append("This is the cockpit recognizer \n");
		Main.outputArea.append("------------------------------- \n");
		Main.outputArea.append("Example: say speed \n");
		Main.outputArea.append("Example: gear up / gear down \n");
		Main.outputArea.append("Example: flaps up / flaps full \n");
		Main.outputArea.append("-------------------------------\n");

		
		;
		SpeechResult result;
		recognizer.startRecognition(true);
		while (isProgramRunning()) {
			if (isButtonPressed()) {
				result = recognizer.getResult();
				if (isButtonPressed()) {
					setButtonPressed(false);
					String utterance = result.getHypothesis();
					LOGGER.info(utterance);
					String[] words = utterance.split("\\s");
					if (words.length >= 2) {
						String firstWord = words[0];
						String secoundWord = words[1];
						utterance = convertNumbers(utterance);
						Main.outputArea.append(utterance + "\n");
						LOGGER.info(utterance);
						determineCommand(utterance, firstWord, secoundWord);
					}
				}

			}
		}

	}

	// TODO: Com 1 and Nav 1
	private String convertNumbers(String utterance) {
		utterance = utterance.replaceAll("oh ?", "" + DIGITS.get("oh"));
		utterance = utterance.replaceAll("zero ?", "" + DIGITS.get("zero"));
		utterance = utterance.replaceAll("one ?", "" + DIGITS.get("one"));
		utterance = utterance.replaceAll("two ?", "" + DIGITS.get("two"));
		utterance = utterance.replaceAll("three ?", "" + DIGITS.get("three"));
		utterance = utterance.replaceAll("four ?", "" + DIGITS.get("four"));
		utterance = utterance.replaceAll("five ?", "" + DIGITS.get("five"));
		utterance = utterance.replaceAll("six ?", "" + DIGITS.get("six"));
		utterance = utterance.replaceAll("seven ?", "" + DIGITS.get("seven"));
		utterance = utterance.replaceAll("eight ?", "" + DIGITS.get("eight"));
		utterance = utterance.replaceAll("nine ?", "" + DIGITS.get("nine"));
		utterance = utterance.replaceAll("point ", ".");
		return utterance;
	}

	private void determineCommand(String utterance, String firstWord,
			String secoundWord) {
		switch (firstWord) {
		case "air":
			commandsHandler.handleAirBrakes(utterance);
			break;
		case "approach":
			commandsHandler.handleApproachCheck(utterance);
			break;
		case "autopilot":
			commandsHandler.handleAutopilot(utterance);
			break;
		case "climb":
			commandsHandler.handleClimbCheck(utterance);
			break;
		case "com":
			commandsHandler.handleCom(utterance);
			break;
		case "cruise":
			commandsHandler.handleCruiseCheck(utterance);
			break;
		case "descend":
			commandsHandler.handleDescendCheck(utterance);
			break;
		case "final":
			commandsHandler.handleFinalCheck(utterance);
			break;
		case "flaps":
			commandsHandler.handleFlaps(utterance);
			break;
		case "flight":
			if (secoundWord.equalsIgnoreCase("director")) {
				commandsHandler.handleFlightDirector(utterance);
			} else {
				commandsHandler.handleFlightPathMarker(utterance);
			}
			break;
		case "heading":
			commandsHandler.handleHeading(utterance);
			break;
		case "gear":
			commandsHandler.handleGear(utterance);
			break;
		case "nav":
			commandsHandler.handleNav(utterance);
			break;
		case "power":
			commandsHandler.handlePower(utterance);
			break;
		case "propeller":
			commandsHandler.handlePropeller(utterance);
			break;
		case "say":
			commandsHandler.handleSay(utterance);
			break;
		case "set":
			commandsHandler.handleSet(utterance);
			break;
		case "speed":
			commandsHandler.handleSpeed(utterance);
			break;
		case "turn":
			commandsHandler.handleTurn(utterance);
			break;
		default:
			Main.outputArea.append(utterance);
			break;
		}

	}

	private boolean isButtonPressed() {
		synchronized (buttonLock) {
			return buttonIsPressed;
		}
	}

	public static void setButtonPressed(boolean buttonPressed) {
		synchronized (buttonLock) {
			buttonIsPressed = buttonPressed;
		}

	}
	
	private boolean isProgramRunning() {
		synchronized (programLock) {
			return programIsRunning;
		}
	}

	
	public static void stopProgram() {
		synchronized (programLock) {
			programIsRunning = false;
		}
	}


}
