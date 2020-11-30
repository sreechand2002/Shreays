package student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminDashboard {

	public JFrame frame;
	public JButton btnAddStudent;
	public JButton btnDeleteStudent;
	public JButton btnViewAllStudent;
	public JButton btnSearchStudent;
	public JButton btnLogout;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard window = new AdminDashboard();
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
	public AdminDashboard() {
		initialize();
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
		
		btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent as = new AddStudent();
				as.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnAddStudent.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnAddStudent.setBounds(301, 142, 152, 46);
		frame.getContentPane().add(btnAddStudent);
		
		btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteStudent ds = new DeleteStudent();
				ds.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnDeleteStudent.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnDeleteStudent.setBounds(301, 259, 152, 46);
		frame.getContentPane().add(btnDeleteStudent);
		
		btnViewAllStudent = new JButton("View All Student");
		btnViewAllStudent.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnViewAllStudent.setBounds(575, 259, 152, 46);
		frame.getContentPane().add(btnViewAllStudent);
		
		btnSearchStudent = new JButton("Search Student");
		btnSearchStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateStudent us = new UpdateStudent();
				us.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnSearchStudent.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnSearchStudent.setBounds(575, 142, 152, 46);
		frame.getContentPane().add(btnSearchStudent);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomePage hp = new HomePage();
				hp.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnLogout.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(Color.RED);
		btnLogout.setBounds(449, 413, 152, 46);
		frame.getContentPane().add(btnLogout);
	}

}
