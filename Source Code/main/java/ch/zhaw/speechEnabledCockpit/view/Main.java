package ch.zhaw.speechEnabledCockpit.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.xml.ws.handler.HandlerResolver;

import ch.zhaw.speechEnabledCockpit.ResponseHandler;
import ch.zhaw.speechEnabledCockpit.ResponseHandlerImpl;
import ch.zhaw.speechEnabledCockpit.SpeechRecognizer;
import ch.zhaw.speechEnabledCockpit.udp.UdpReceiver;
import ch.zhaw.speechEnabledCockpit.util.ReceiveValue;

public class Main extends JFrame  {
	
	public static JTextArea outputArea;

	public static void main(String[] args) throws Exception {
		Main main = new Main();
	}
	
	public Main() {
		outputArea = new JTextArea();
		outputArea.setEditable(false);
//		outputArea.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressButtonAction");
//		outputArea.getInputMap().put(KeyStroke.getKeyStroke("UP"), "releaseButtonAction");
//		outputArea.getActionMap().put("pressButtonAction", new PressButtonAction());
//		outputArea.getActionMap().put("releaseButtonAction", new ReleaseButtonAction());
		
		
		
		JScrollPane scrollpane = new JScrollPane(outputArea);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setAutoscrolls(true);
		JFrame frame = new JFrame("Speech Enabled Cockpit");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(scrollpane);
		frame.setVisible(true);
		
		
		new Thread(new UdpReceiver()).start();
		new SpeechRecognizer();
	}
	
//	
//	private class PressButtonAction extends AbstractAction {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			SpeechRecognizer.setButtonPressed(true);
//			
//		}
//		
//	}
//	
//	private class ReleaseButtonAction extends AbstractAction {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			SpeechRecognizer.setButtonPressed(false);
//			
//		}
//		
//	}
}
