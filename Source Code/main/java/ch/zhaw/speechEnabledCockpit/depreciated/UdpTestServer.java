package ch.zhaw.speechEnabledCockpit.depreciated;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

public class UdpTestServer {

	private static boolean serverRuns = true;

	private final Short MSG_TYPE = 17;
	private final Short MSG_LENGTH = 10;
	private final static int PORT = 50010;
	private final static Logger LOGGER = Logger
			.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(PORT);

		while (serverRuns) {
			byte[] receiveData = new byte[10];
			// byte[] sendData = new byte[1024];
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
			// InetAddress IPAddress = receivePacket.getAddress();
			// int port = receivePacket.getPort();
			// String capitalizedSentence = sentence.toUpperCase();
			// sendData = capitalizedSentence.getBytes();
			// DatagramPacket sendPacket = new DatagramPacket(sendData,
			// sendData.length, IPAddress, port);
			// serverSocket.send(sendPacket);
		}
		serverSocket.close();
		LOGGER.info("server is turned off");
	}

	public static void turnOffServer() {
		serverRuns = false;
	}
}
