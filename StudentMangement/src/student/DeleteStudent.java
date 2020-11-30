package student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class DeleteStudent {

	public JFrame frame;
	private JTextField textField;
	Connection con = null;
	ResultSet rs= null;
	PreparedStatement ps = null,ps1=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteStudent window = new DeleteStudent();
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
	public DeleteStudent() {
		con = sqlConnection.connectorDB();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 1058, 664);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterUsnTo = new JLabel("Enter USN to delete :");
		lblEnterUsnTo.setFont(new Font("Century", Font.ITALIC, 18));
		lblEnterUsnTo.setForeground(SystemColor.text);
		lblEnterUsnTo.setBounds(322, 258, 194, 24);
		frame.getContentPane().add(lblEnterUsnTo);
		
		textField = new JTextField();
		textField.setBounds(520, 261, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int input = JOptionPane.showConfirmDialog(null, "Confirm your Deletion","Confirmation",JOptionPane.YES_NO_OPTION);
				try {
					if(input == 0 ) {
						String query = "delete from data where usn = ?";
						ps = con.prepareStatement(query);
						ps.setString(1,textField.getText());
						int n = ps.executeUpdate();
						String query1 = "delete from login where username = ?";
						ps1 = con.prepareStatement(query1);
						ps1.setString(1,textField.getText());
						 n = n + ps1.executeUpdate();
						if(n ==2)
						JOptionPane.showMessageDialog(null, "successfully deleted");
						else
							JOptionPane.showMessageDialog(null, "Data not found");
						textField.setText("");
					}
					else
						JOptionPane.showMessageDialog(null, "Please fill in all the fields");
					} catch(Exception e) {
						System.out.println(e);
					}finally {
						try { rs.close(); } catch (Exception e) { /* ignored */ }
						try { ps.close(); } catch (Exception e) { /* ignored */ }
						try { ps1.close(); } catch (Exception e) { /* ignored */ }
						try { con.close(); } catch (Exception e) { /* ignored */ }
					}
			}
		});
		btnDelete.setBounds(446, 319, 97, 25);
		frame.getContentPane().add(btnDelete);
		
		JButton button = new JButton("\uF0E7");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminDashboard ad = new AdminDashboard();
				ad.frame.setVisible(true);
				frame.dispose();
			}
		});
		button.setFont(new Font("Wingdings", Font.PLAIN, 13));
		button.setBounds(67, 62, 81, 32);
		frame.getContentPane().add(button);
	}
}
