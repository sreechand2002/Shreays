package student;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.Color;

public class splash extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JProgressBar progressBar;
	public JLabel lblWelcomeToStudent;
	public JLabel label_1;
	public JLabel lblDesignedBySree;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public splash() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 527);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblWelcomeToStudent = new JLabel("WELCOME TO STUDENT MANAGEMETN PROJECT ");
		lblWelcomeToStudent.setFont(new Font("Georgia", Font.BOLD, 24));
		lblWelcomeToStudent.setBounds(113, 123, 645, 70);
		contentPane.add(lblWelcomeToStudent);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		progressBar.setBounds(0, 332, 817, 45);
		contentPane.add(progressBar);
		
		
		 label_1 = new JLabel("...");
		label_1.setBackground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_1.setBounds(370, 294, 32, 38);
		contentPane.add(label_1);
		
		lblDesignedBySree = new JLabel("Designed by Sree chand R");
		lblDesignedBySree.setForeground(Color.WHITE);
		lblDesignedBySree.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));
		lblDesignedBySree.setBounds(497, 412, 308, 38);
		contentPane.add(lblDesignedBySree);
	}

}
