package student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class AddStudent {

	public JFrame frame;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JButton btnAddDetails;
	public JButton btnBack;
	
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
					AddStudent window = new AddStudent();
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
	public AddStudent() {
		con = sqlConnection.connectorDB();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 16));
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBackground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 1058, 664);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblUsn = new JLabel("USN");
		springLayout.putConstraint(SpringLayout.WEST, lblUsn, 366, SpringLayout.WEST, frame.getContentPane());
		lblUsn.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 16));
		frame.getContentPane().add(lblUsn);
		
		JLabel lblName = new JLabel("Name");
		springLayout.putConstraint(SpringLayout.WEST, lblName, 0, SpringLayout.WEST, lblUsn);
		lblName.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 16));
		frame.getContentPane().add(lblName);
		
		JLabel lblSemester = new JLabel("Semester");
		springLayout.putConstraint(SpringLayout.WEST, lblSemester, 347, SpringLayout.WEST, frame.getContentPane());
		lblSemester.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 16));
		frame.getContentPane().add(lblSemester);
		
		textField = new JTextField();
		textField.setFont(new Font("Franklin Gothic Book", Font.BOLD, 16));
		springLayout.putConstraint(SpringLayout.NORTH, lblUsn, 5, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, lblUsn, -75, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, 163, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 488, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, -424, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Franklin Gothic Book", Font.BOLD, 16));
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 5, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, lblName, -75, SpringLayout.WEST, textField_1);
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 228, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField_1, -352, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -28, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 488, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lblSemester, 5, SpringLayout.NORTH, textField_2);
		springLayout.putConstraint(SpringLayout.EAST, lblSemester, -60, SpringLayout.WEST, textField_2);
		textField_2.setFont(new Font("Franklin Gothic Book", Font.BOLD, 16));
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, 23, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_2, -292, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 488, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		btnAddDetails = new JButton("Add Details");
		springLayout.putConstraint(SpringLayout.NORTH, btnAddDetails, 59, SpringLayout.SOUTH, textField_2);
		springLayout.putConstraint(SpringLayout.WEST, btnAddDetails, 407, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddDetails, -196, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnAddDetails, 523, SpringLayout.WEST, frame.getContentPane());
		btnAddDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				if(!textField.getText().isEmpty() && !textField_1.getText().isEmpty() && !textField_2.getText().isEmpty()) {
					String query = "insert into data values (?,?,?)";
					ps = con.prepareStatement(query);
					ps.setString(1,textField.getText());
					ps.setString(2,textField_1.getText());
					ps.setInt(3, Integer.parseInt(textField_2.getText()));
					int n = ps.executeUpdate();
					String query1 = "insert into login values (?,?)";
					ps1=con.prepareStatement(query1);
					ps1.setString(1, textField.getText());
					ps1.setString(2, textField_1.getText());
					n = n+ ps1.executeUpdate();
					if(n==2)
						JOptionPane.showMessageDialog(null, "added successfully");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
				}
				else
					JOptionPane.showMessageDialog(null, "Please fill in all the fields");
				} catch(Exception e) {
					System.out.println(e);
				}finally {
					try { rs.close(); } catch (Exception e) { /* ignored */ }
					try { ps.close(); } catch (Exception e) { /* ignored */ }
					try { ps1.close();} catch (Exception e) {}
					try { con.close(); } catch (Exception e) { /* ignored */ }
				}
			}
		});
		frame.getContentPane().add(btnAddDetails);
		
		btnBack = new JButton("\uF0E7");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDashboard ad = new AdminDashboard();
				ad.frame.setVisible(true);
				frame.dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, 76, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnBack, 144, SpringLayout.WEST, frame.getContentPane());
		btnBack.setFont(new Font("Wingdings", Font.PLAIN, 13));
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 44, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 63, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(btnBack);
	}
}
