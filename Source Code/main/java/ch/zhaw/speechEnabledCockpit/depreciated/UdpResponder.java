package ch.zhaw.speechEnabledCockpit.depreciated;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import ch.zhaw.speechEnabledCockpit.SpeechSynthesizer;

public class UdpResponder {
	

	private static boolean responderRuns = true;

	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9877);

		while (responderRuns) {
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			System.out.println("server is running");
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			System.out.println("RECEIVED: " + sentence);
			SpeechSynthesizer.say(sentence);
			
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String capitalizedSentence = sentence.toUpperCase();
			sendData = capitalizedSentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
		serverSocket.close();
	}

	public static void turnOffServer() {
		responderRuns = false;
	}
	
	public static boolean isResponderRunning() {
		return responderRuns;
	}
}
