package assignment4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class GUI {

	private JFrame frame;
	private JTextField sequenceField;
	private JTextField txtDeficulity;
	private JTextField output;
	private MaxSequenceSumGame game;
	protected Thread gameThread;
	public static InputStream in = new InputStream() {
		
		@Override
		public int read() throws IOException {
			
			return 0;
		}
	};

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

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		sequenceField = new JTextField();
		sequenceField.setBounds(12, 13, 408, 33);
		frame.getContentPane().add(sequenceField);
		sequenceField.setColumns(10);
		sequenceField.setText(game.sequence());

		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				game.reset();
				gameThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						game.startGUI(new BufferedReader(new InputStreamReader(System.in)),sequenceField, output);
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
				byte[] b = {'L'};
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							System.out.println("READING");
							System.in.read(b, 0, 1);
							System.out.println("FINISHED");
							System.out.println(System.in.available());

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
		btnLeft.setBounds(86, 102, 99, 33);
		frame.getContentPane().add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				byte[] b = {'R'};
				try {
					System.in.read(b);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
