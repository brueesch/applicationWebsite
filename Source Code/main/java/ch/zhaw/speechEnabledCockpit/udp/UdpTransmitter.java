package ch.zhaw.speechEnabledCockpit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class UdpTransmitter {

	private final Short MSG_TYPE = 17;
	private final Short MSG_LENGTH = 10;
	private final int PORT = 50010;
	private final String HOST_COMPUTER = "clt-dsk-t-8405\0";

	private DatagramSocket clientSocket = null;

	public static void main(String[] args) {
		if (args.length < 2) {
			return;
		} else {
			new UdpTransmitter().createUdp(Short.valueOf(args[0]),
					Integer.valueOf(args[1]));
		}
	}

	public byte[] createUdp(Short commandType, float commandValue) {
		byte[] sendData = null;
		try {
			sendData = createPacket(commandType, commandValue);
			sendPacket(sendData);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			clientSocket.close();
		}
		return sendData;
	}

	private void sendPacket(byte[] sendData) throws SocketException,
			UnknownHostException, IOException {
		clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName(HOST_COMPUTER);
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, PORT);
		clientSocket.send(sendPacket);
	}

	//TODO: Test Value if number value
	private byte[] createPacket(Short commandType, float commandValue) {

		byte[] msgTypeBytes = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(MSG_TYPE).array();
		byte[] msgLengthBytes = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(MSG_LENGTH)
				.array();
		byte[] commandTypeBytes = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(commandType)
				.array();
		byte[] commandValueBytes = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(commandValue)
				.array();

		byte[] sendData = { msgTypeBytes[0], msgTypeBytes[1],
				msgLengthBytes[0], msgLengthBytes[1], commandTypeBytes[0],
				commandTypeBytes[1], commandValueBytes[0],
				commandValueBytes[1], commandValueBytes[2],
				commandValueBytes[3] };
		return sendData;
	}

}
