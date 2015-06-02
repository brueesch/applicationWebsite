package ch.zhaw.speechEnabledCockpit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

public class TestUdpReceiver {

	private DatagramSocket clientSocket = null;
	private String HOST_COMPUTER = "localhost";
	private int PORT = 50011;

	@Before
	public void setUp() {
//		receiver = new UdpResponder();
	}

	@Test
	public void testCreateUdp() {
		
		byte[] sendData = { 0, 18, 0, 10, 0, 26, 0, 0, 0, 20 };
		createUdp(sendData);
		
		
		
//		boolean[] expected = { true, true, true, true };
//		byte[] actual = receiver.createUdp(CommandType.APPROACHCHECK.getValue(),
//				CommandValues.NOVALUE.getValue());
//		for (int i = 0; i < 10; i++) {
//			assertEquals(expected[i], actual[i]);
//		}
//		
//		int expectedInt = 10;
//		int actualInt = (int) receiver.createUdp(CommandType.APPROACHCHECK.getValue(),
//				CommandValues.NOVALUE.getValue()).length;
//		assertEquals(expectedInt, actualInt);

	}
	
	private byte[] createUdp(byte[] sendData) {
		try {
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

}
