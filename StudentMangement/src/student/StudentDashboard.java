package student;


import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class StudentDashboard  extends HomePage{

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//StudentDashboard window = new StudentDashboard();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the application.
	 */
	public StudentDashboard() {
		initialize();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public StudentDashboard(String s, String s1) {
		initialize();
		textField.setText(s1);
		textField_1.setText(s);
		final String voicename = "kevin16";
		Voice voice;
		VoiceManager vm =  VoiceManager.getInstance();
		voice = vm.getVoice(voicename);
		
		voice.allocate();
		
		try {
			voice.speak("welcome to indian railways" + textField.getText().toString());
		} catch (Exception e) {
			
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 1058, 664);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnViewMarks = new JButton("View Marks");
		btnViewMarks.setBounds(56, 252, 136, 51);
		frame.getContentPane().add(btnViewMarks);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(247, 107, 562, 417);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Welcome:");
		lblName.setBounds(56, 43, 76, 22);
		frame.getContentPane().add(lblName);
		
		JLabel lblUsn = new JLabel("USN");
		lblUsn.setBounds(297, 46, 56, 16);
		frame.getContentPane().add(lblUsn);
		
		JLabel lblSemester = new JLabel("Semester");
		lblSemester.setBounds(510, 46, 56, 16);
		frame.getContentPane().add(lblSemester);
		
		textField = new JTextField();
		textField.setBounds(121, 43, 116, 22);
		frame.getContentPane().add(textField);
		textField.setEditable(false);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(331, 43, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(578, 43, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
	}
}
