package ch.zhaw.speechEnabledCockpit.udp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.zhaw.speechEnabledCockpit.util.CommandType;
import ch.zhaw.speechEnabledCockpit.util.CommandValues;

public class TestUdpTransmitter {

	private UdpTransmitter transmitter;

	@Before
	public void setUp() {
		transmitter = new UdpTransmitter();
	}

	@Test
	public void testCreateUdp() {
		byte[] expected = { 17, 0, 10, 0, 11, 0, 0, 0, -56,66 };
		byte[] actual = transmitter.createUdp(CommandType.APPROACHCHECK.getValue(),
				CommandValues.NOVALUE.getValue());
		for (int i = 0; i < 10; i++) {
			assertEquals(expected[i], actual[i]);
		}
		
		int expectedInt = 10;
		int actualInt = (int) transmitter.createUdp(CommandType.APPROACHCHECK.getValue(),
				CommandValues.NOVALUE.getValue()).length;
		assertEquals(expectedInt, actualInt);

	}

}
