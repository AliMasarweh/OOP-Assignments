package assignment4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ali Masarweh
 * NOTE: this GUI currently is not working, please use GUIAlpha
 */
public class GUI {

	private JFrame frame;
	private JTextField sequenceField;
	private JTextField txtDeficulity;
	private JTextField output;
	private MaxSequenceSumGame game;
	protected Thread gameThread;
	public final static PipedOutputStream pipedOut = new PipedOutputStream();
	public final static PipedInputStream pipedIn = new PipedInputStream();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		game = new MaxSequenceSumGame(20);

		PipedOutputThread outputPipe = new PipedOutputThread();
		PipedInputThread inputPipe = new PipedInputThread();

		Thread outputThread = new Thread(outputPipe);
		outputThread.start();

		Thread inputThread = new Thread(inputPipe);
		inputThread.start();

		try {
			pipedOut.connect(pipedIn);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

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
						game.startGUI(inputPipe, sequenceField, output);
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
				outputPipe.output = 'L';
			}
		});
		btnLeft.setBounds(86, 102, 99, 33);
		frame.getContentPane().add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnRight.setBounds(253, 102, 99, 33);
		frame.getContentPane().add(btnRight);

		JButton btnEasy = new JButton("Easy");
		btnEasy.setBounds(131, 159, 99, 33);
		frame.getContentPane().add(btnEasy);

		JButton btnHard = new JButton("Hard");
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

class PipedOutputThread implements Runnable {

	public char output;
	private Object lock = new Object();

	public PipedOutputThread() {
		output = PipedInputThread.NO_INPUT;
	}

	/*
	 * public char output() { return output; }
	 */

	@Override
	public void run() {
		while (true) {
			synchronized (lock) {
				try {
					// To not output an invalid input
					while (output == PipedInputThread.NO_INPUT)
						;
					GUI.pipedOut.write((output + "").getBytes());
					output = PipedInputThread.NO_INPUT;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}

class PipedInputThread implements Runnable {

	public final static char NO_INPUT = 'V';
	private char currentInput;
	private List<Character> readSoFar;
	private Object lock = new Object();

	public PipedInputThread() {
		currentInput = NO_INPUT;
		readSoFar = new ArrayList<>();
	}

	public char read() {
		if (readSoFar.isEmpty())
			return NO_INPUT;
		return readSoFar.remove(0);
	}

	@Override
	public void run() {
		while (true) {
			try {
				int i = 0;
				while ((i = GUI.pipedIn.read()) != -1) {
					synchronized (lock) {
						currentInput = (char) i;
						readSoFar.add(currentInput);
						currentInput = NO_INPUT;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
