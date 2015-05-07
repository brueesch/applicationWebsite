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
import ch.zhaw.speechEnabledCockpit.util.ReceiveType;

public class UdpReceiver implements Runnable {

	private static boolean serverRuns = true;

	private final Short MSG_TYPE = 18;
	private final Short MSG_LENGTH = 10;
	private final static int PORT = 50011;
	private final static Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private short receiveType;

	private float receiveValue;

	public void turnOffServer() {
		serverRuns = false;
	}

	@Override
	public void run() {
		DatagramSocket serverSocket = null;
		try {
			serverSocket = new DatagramSocket(PORT);
			while (serverRuns) {
				byte[] receiveData = new byte[MSG_LENGTH];
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
		if (receiveData[0] == MSG_TYPE) {
			byte[] tmpReceiveTypeArray = { receiveData[4], receiveData[5] };
			ByteBuffer wrapped = ByteBuffer.wrap(tmpReceiveTypeArray).order(ByteOrder.LITTLE_ENDIAN);
			receiveType = wrapped.getShort();

			byte[] tmpReceiveValueArray = { receiveData[6], receiveData[7],
					receiveData[8], receiveData[9] };
			wrapped = ByteBuffer.wrap(tmpReceiveValueArray).order(ByteOrder.LITTLE_ENDIAN);
			receiveValue = wrapped.getFloat();
			LOGGER.info("Receive Type: " + receiveType + "Receive Value: "
					+ receiveValue);
		}
	}

	private void processData() {
		ResponseHandler responseHandler = new ResponseHandlerImpl();
		ReceiveType type = ReceiveType.getReceiveTypeFromShort(receiveType);
		if (type != null) {

			switch (type) {
			case BUTTON:
				responseHandler.handleButton(receiveValue);
				break;
			case CONFIRMATION:
				responseHandler.handleConfirmation();
				break;
			case NOTCONFIRMED:
				responseHandler.handleNotConfirmed();
				break;
			case SAYSPEED:
				responseHandler.handleSaySpeed(receiveValue);
				break;
			case SAYENDURANCE:
				responseHandler.handleSayEndurance(receiveValue);
				break;
			case SAYFUEL:
				responseHandler.handleSayFuel(receiveValue);
				break;
			}

		}
	}
}
