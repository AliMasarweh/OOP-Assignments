package assignment4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIAlpha {

	private JFrame frame;
	private JTextField sequenceField;
	private JTextField txtDeficulity;
	private JTextField output;
	private MaxSequenceSumGame game;
	protected Thread gameThread;
	public static volatile Character input = 'V';

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAlpha window = new GUIAlpha();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIAlpha() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		game = new MaxSequenceSumGame(6);

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		sequenceField = new JTextField();
		sequenceField.setBounds(12, 13, 408, 33);
		frame.getContentPane().add(sequenceField);
		sequenceField.setColumns(10);
		sequenceField.setText("Press Start!");

		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				game.reset();
				gameThread = new Thread(new Runnable() {

					@Override
					public void run() {
						game.startGUI(sequenceField, output);
					}
				});
				gameThread.start();
			}
		});
		btnStart.setBounds(168, 56, 99, 33);
		frame.getContentPane().add(btnStart);

		JButton btnLeft = new JButton("Left");
		btnLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				input = 'L';
			}
		});
		btnLeft.setBounds(86, 102, 99, 33);
		frame.getContentPane().add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				input = 'R';
			}
		});
		btnRight.setBounds(253, 102, 99, 33);
		frame.getContentPane().add(btnRight);

		JButton btnEasy = new JButton("Easy");
		btnEasy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				game.setAgentToRandom();
				output.setText("The difficulty is now easy");
			}
		});
		btnEasy.setBounds(131, 159, 99, 33);
		frame.getContentPane().add(btnEasy);

		JButton btnHard = new JButton("Hard");
		btnHard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				game.setAgentToSopisticated();
				output.setText("The difficulty is now hard");
			}
		});
		btnHard.setBounds(263, 159, 99, 33);
		frame.getContentPane().add(btnHard);

		txtDeficulity = new JTextField();
		txtDeficulity.setText("Difficulty:");
		txtDeficulity.setBounds(42, 155, 61, 41);
		frame.getContentPane().add(txtDeficulity);
		txtDeficulity.setColumns(10);

		output = new JTextField();
		output.setBounds(12, 205, 408, 35);
		frame.getContentPane().add(output);
		output.setColumns(10);
	}
}

