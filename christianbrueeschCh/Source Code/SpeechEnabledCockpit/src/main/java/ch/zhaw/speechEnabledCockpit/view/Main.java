package ch.zhaw.speechEnabledCockpit.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import ch.zhaw.speechEnabledCockpit.SpeechRecognizer;
import ch.zhaw.speechEnabledCockpit.udp.UdpReceiver;
import ch.zhaw.speechEnabledCockpit.util.LoadProperties;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class Main extends JFrame {

	public static JTextArea outputArea;

	private JPanel contentPane;
	private boolean testButtonPressed;

	private Thread recognizerThread;

	private Thread receiverThread;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Main frame = new Main();
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		initView();
		this.setVisible(true);
	}

	private void startSpeechRecognition() {
		receiverThread = new Thread(new UdpReceiver());
		receiverThread.start();
		recognizerThread = new Thread(new SpeechRecognizer());
		recognizerThread.start();
		
	}

	private void initView() {
		setResizable(false);
		setTitle("Spracherkennung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JButton startButton = new JButton("Spracherkennung starten");
		JButton stopButton = new JButton("Spracherkennung stoppen");
		JButton testButton = new JButton("Test Button");
		testButton.setEnabled(false);
		
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startSpeechRecognition();
				stopButton.setEnabled(true);
				startButton.setEnabled(false);
				testButton.setEnabled(true);
			}
		});
		contentPane.add(startButton, "4, 8");

		
		stopButton.setEnabled(false);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(recognizerThread != null) {
					recognizerThread = null;
					receiverThread = null;
				}
				
				stopButton.setEnabled(false);
				startButton.setEnabled(true);
				testButton.setEnabled(false);
			}
		});
		contentPane.add(stopButton, "16, 8");

		JLabel lblErkannteBefehle = new JLabel("Erkannte Befehle");
		contentPane.add(lblErkannteBefehle, "4, 12");

		outputArea = new JTextArea();
		outputArea.setEditable(false);
		
		 JScrollPane scrollpane = new JScrollPane(outputArea);
		 scrollpane
		 .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scrollpane.setAutoscrolls(true);
		 
		contentPane.add(scrollpane, "4, 14, 13, 25, fill, fill");

		
		 testButton.addMouseListener(new MouseAdapter() {
		        public void mousePressed(MouseEvent e) {
		            testButtonPressed = true;
		            new Thread() {
		                public void run() {
		                    while (testButtonPressed) {
		                    	SpeechRecognizer.setButtonPressed(true);	
		                    }
		                    SpeechRecognizer.setButtonPressed(false);
		                }

		            }.start();
		        }

		        public void mouseReleased(MouseEvent e) {
		        	testButtonPressed = false;
		        }

		    });
		contentPane.add(testButton, "16, 42");
	}

}
