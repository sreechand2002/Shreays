package student;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;


import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class HomePage {

	public JFrame frame;
	public final ButtonGroup buttonGroup = new ButtonGroup();
	public JTextField textField;
	public JPasswordField passwordField;
	
	Connection con = null;
	ResultSet rs= null;
	PreparedStatement ps = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
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
	public HomePage() {
		con = sqlConnection.connectorDB();
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
		
		JTextArea txtrDayanandaSagarCollege = new JTextArea();
		txtrDayanandaSagarCollege.setBackground(SystemColor.textHighlight);
		txtrDayanandaSagarCollege.setFont(new Font("Goudy Old Style", Font.BOLD | Font.ITALIC, 24));
		txtrDayanandaSagarCollege.setForeground(SystemColor.text);
		txtrDayanandaSagarCollege.setText("DAYANANDA SAGAR COLLEGE OF ENGINEERING");
		txtrDayanandaSagarCollege.setBounds(243, 103, 605, 39);
		frame.getContentPane().add(txtrDayanandaSagarCollege);
		
		JRadioButton rdbtnAdmin = new JRadioButton("ADMIN");
		rdbtnAdmin.setSelected(true);
		rdbtnAdmin.setForeground(SystemColor.text);
		rdbtnAdmin.setFont(new Font("Franklin Gothic Demi", Font.ITALIC, 16));
		rdbtnAdmin.setBackground(SystemColor.textHighlight);
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setBounds(448, 237, 77, 25);
		frame.getContentPane().add(rdbtnAdmin);
		
		JRadioButton rdbtnStudent = new JRadioButton("STUDENT");
		rdbtnStudent.setForeground(SystemColor.text);
		rdbtnStudent.setFont(new Font("Franklin Gothic Demi", Font.ITALIC, 16));
		rdbtnStudent.setBackground(SystemColor.textHighlight);
		buttonGroup.add(rdbtnStudent);
		rdbtnStudent.setBounds(558, 237, 101, 25);
		frame.getContentPane().add(rdbtnStudent);
		
		JLabel lblUsername = new JLabel("USERNAME:");
		lblUsername.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		lblUsername.setForeground(SystemColor.text);
		lblUsername.setBounds(322, 296, 108, 25);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		lblPassword.setForeground(SystemColor.text);
		lblPassword.setBounds(322, 359, 101, 25);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(448, 291, 193, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(448, 354, 193, 39);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select username,password from login where username=?";
					ps = con.prepareStatement(query);
					ps.setString(1,textField.getText());
					rs = ps.executeQuery();
					if(passwordField.getText().contentEquals(rs.getString("password")) && (rdbtnAdmin.isSelected() && textField.getText().contentEquals("admin"))) {
						AdminDashboard ad = new AdminDashboard();
						frame.dispose();
						ad.frame.setVisible(true);
					}
					else if(passwordField.getText().contentEquals(rs.getString("password")) && (rdbtnStudent.isSelected())) {
						String msg = textField.getText();
						String msg1 = passwordField.getText();
						new StudentDashboard(msg,msg1).frame.setVisible(true);
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "wrong credentials or wrong category");
						HomePage hp = new HomePage();
						hp.frame.setVisible(true);
						frame.dispose();
					}
					
						
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "username not registered");
					HomePage hp = new HomePage();
					hp.frame.setVisible(true);
					frame.dispose();
					
				} finally {
					try { rs.close(); } catch (Exception e) { /* ignored */ }
					try { ps.close(); } catch (Exception e) { /* ignored */ }
					try { con.close(); } catch (Exception e) { /* ignored */ }
				}
			}
		});
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.YELLOW, Color.YELLOW));
		btnLogin.setBounds(503, 439, 97, 25);
		frame.getContentPane().add(btnLogin);
	}
}
