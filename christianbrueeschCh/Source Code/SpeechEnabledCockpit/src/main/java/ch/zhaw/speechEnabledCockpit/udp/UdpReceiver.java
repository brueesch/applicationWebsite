package ch.zhaw.speechEnabledCockpit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Logger;

import ch.zhaw.speechEnabledCockpit.ResponseHandler;
import ch.zhaw.speechEnabledCockpit.ResponseHandlerImpl;
import ch.zhaw.speechEnabledCockpit.util.LoadProperties;
import ch.zhaw.speechEnabledCockpit.util.ReceiveType;

public class UdpReceiver implements Runnable {

	private static boolean serverRuns = true;

	private Short msgType;
	private Short msgLength;
	private int port;
	private final static Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private short receiveType;

	private float receiveValue;

	private ResponseHandler responseHandler = new ResponseHandlerImpl();;

	public void turnOffServer() {
		serverRuns = false;
	}

	@Override
	public void run() {
		DatagramSocket serverSocket = null;
		LoadProperties properties = LoadProperties.getInstance();
		msgType = properties.getReceiverMsgType();
		msgLength = properties.getMsgLength();
		port = properties.getReceiverPort();
		
		
		try {
			serverSocket = new DatagramSocket(port);
			while (serverRuns) {
				byte[] receiveData = new byte[msgLength];
				LOGGER.info("server is running");
				DatagramPacket receivePacket = new DatagramPacket(receiveData,
						receiveData.length);
				serverSocket.receive(receivePacket);

				LOGGER.info("RECEIVED: ");
				LOGGER.info("" + receiveData[0] + ", " + receiveData[1] + ", "
						+ receiveData[2] + ", " + receiveData[3] + ", "
						+ receiveData[4] + ", " + receiveData[5] + ", "
						+ receiveData[6] + ", " + receiveData[7] + ", "
						+ receiveData[8] + ", " + receiveData[9]);
				unpackPackage(receiveData);
				processData();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			serverSocket.close();
		}
		LOGGER.info("server is turned off");

	}

	private void unpackPackage(byte[] receiveData) {
		if (receiveData[0] == msgType) {
			byte[] tmpReceiveTypeArray = { receiveData[4], receiveData[5] };
			ByteBuffer wrapped = ByteBuffer.wrap(tmpReceiveTypeArray).order(
					ByteOrder.LITTLE_ENDIAN);
			receiveType = wrapped.getShort();

			byte[] tmpReceiveValueArray = { receiveData[6], receiveData[7],
					receiveData[8], receiveData[9] };
			wrapped = ByteBuffer.wrap(tmpReceiveValueArray).order(
					ByteOrder.LITTLE_ENDIAN);
			receiveValue = wrapped.getFloat();
			LOGGER.info("Receive Type: " + receiveType + "Receive Value: "
					+ receiveValue);
		}
	}

	private void processData() {
		ReceiveType type = ReceiveType.getReceiveTypeFromShort(receiveType);
		if (type != null) {

			switch (type) {
			case BUTTON:
				responseHandler.handleButton(receiveValue);
				break;
			case CONFIRMATION:
				responseHandler.handleConfirmation();
				break;
			case SAYSPEED:
				responseHandler.handleSay("speed is", receiveValue);
				break;
			case SAYENDURANCE:
				responseHandler.handleSay("endurance is",receiveValue);
				break;
			case SAYFUEL:
				responseHandler.handleSay("fuel is",receiveValue);
				break;
			case SAYALTITUDE:
				responseHandler.handleSay("altitude is", receiveValue);
				break;
			}

		}
	}
}
