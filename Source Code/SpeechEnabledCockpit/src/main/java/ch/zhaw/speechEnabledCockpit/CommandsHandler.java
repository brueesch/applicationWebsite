package ch.zhaw.speechEnabledCockpit;

public interface CommandsHandler {

	public boolean handleTurn(String utterance);

	public boolean handleSpeed(String utterance);

	public boolean handleSet(String utterance);

	public boolean handleSay(String utterance);

	public boolean handlePropeller(String utterance);

	public boolean handlePower(String utterance);

	public boolean handleNav(String utterance);

	public boolean handleGear(String utterance);

	public boolean handleHeading(String utterance);

	public boolean handleFlightPathMarker(String utterance);

	public boolean handleFlightDirector(String utterance);

	public boolean handleFlaps(String utterance);

	public boolean handleFinalCheck(String utterance);

	public boolean handleDescendCheck(String utterance);

	public boolean handleCruiseCheck(String utterance);

	public boolean handleCom(String utterance);

	public boolean handleClimbCheck(String utterance);

	public boolean handleAutopilot(String utterance);

	public boolean handleApproachCheck(String utterance);

	public boolean handleAirBrakes(String utterance);
}
