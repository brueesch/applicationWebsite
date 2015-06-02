package ch.zhaw.speechEnabledCockpit.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class LoadProperties {
	
	private final static Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private static LoadProperties instance = null;

	private String hostComputer;
	private Short msgLength;
	private Short transmitterMsgType;
	private int transmitterPort;
	private Short receiverMsgType;
	private int receiverPort;

	public static LoadProperties getInstance() {
		if (instance == null) {
			instance = new LoadProperties();

		}
		return instance;
	}

	private LoadProperties() {

		InputStream in = null;
		Properties props = null;
		try {
			props = new Properties();
			in = getClass().getResourceAsStream("/properties/properties.properties");
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		fillUpProperties(props);
		logProperties();

	}

	private void logProperties() {
		LOGGER.info("Host Computer: " + getHostComputer());
		LOGGER.info("Msg Length: " + getMsgLength());
		LOGGER.info("Transmitter Port: " + getTransmitterPort());
		LOGGER.info("Transmitter Msg Type: " + getTransmitterMsgType());
		LOGGER.info("Receiver Port: " + getReceiverPort());
		LOGGER.info("Receiver Msg Type: " + getReceiverMsgType());
	}

	private void fillUpProperties(Properties props) {
		setHostComputer(props.getProperty("hostComputer"));
		setMsgLength(Short.parseShort(props.getProperty("msgLength")));
		setTransmitterPort(Integer.parseInt(props
				.getProperty("transmitterPort")));
		setTransmitterMsgType(Short.parseShort(props
				.getProperty("transmitterMsgType")));
		setReceiverPort(Integer.parseInt(props.getProperty("receiverPort")));
		setReceiverMsgType(Short.parseShort(props
				.getProperty("receiverMsgType")));
	}

	public String getHostComputer() {
		return hostComputer;
	}

	public void setHostComputer(String hostComputer) {
		this.hostComputer = hostComputer;
	}

	public Short getMsgLength() {
		return msgLength;
	}

	public void setMsgLength(Short msgLength) {
		this.msgLength = msgLength;
	}

	public Short getTransmitterMsgType() {
		return transmitterMsgType;
	}

	public void setTransmitterMsgType(Short transmitterMsgType) {
		this.transmitterMsgType = transmitterMsgType;
	}

	public int getTransmitterPort() {
		return transmitterPort;
	}

	public void setTransmitterPort(int transmitterPort) {
		this.transmitterPort = transmitterPort;
	}

	public Short getReceiverMsgType() {
		return receiverMsgType;
	}

	public void setReceiverMsgType(Short receiverMsgType) {
		this.receiverMsgType = receiverMsgType;
	}

	public int getReceiverPort() {
		return receiverPort;
	}

	public void setReceiverPort(int receiverPort) {
		this.receiverPort = receiverPort;
	}
}
