package student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;


public class UpdateStudent {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

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
					UpdateStudent window = new UpdateStudent();
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
	public UpdateStudent() {
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
		
		textField = new JTextField();
		textField.setBounds(170, 51, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterUsn = new JLabel("Enter USN");
		lblEnterUsn.setBounds(90, 48, 58, 29);
		frame.getContentPane().add(lblEnterUsn);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(225, 155, 56, 16);
		frame.getContentPane().add(lblName);
		
		JLabel lblSemester = new JLabel("semester");
		lblSemester.setBounds(212, 224, 58, 22);
		frame.getContentPane().add(lblSemester);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(293, 152, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(293, 221, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setEnabled(false);
		btnReset.setBounds(626, 50, 97, 25);
		frame.getContentPane().add(btnReset);
		
		JButton btnUpdateMarks = new JButton("Update");
		btnUpdateMarks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String text = textField_2.getText();
					String query = "update data set semester=? where usn=?";
					ps = con.prepareStatement(query);
					ps.setInt(1, Integer.parseInt(text));
					ps.setString(2, textField.getText());
					int n  = ps.executeUpdate();
					if(n==1)
						JOptionPane.showMessageDialog(null, "Updated successfully");
					System.out.print(n);
				}catch (Exception e) {
					
				}finally {
					try { rs.close(); } catch (Exception e) { /* ignored */ }
					try { ps.close(); } catch (Exception e) { /* ignored */ }
					try { con.close(); } catch (Exception e) { /* ignored */ }
				}
			}
		});
		btnUpdateMarks.setEnabled(false);
		btnUpdateMarks.setBounds(463, 379, 111, 47);
		frame.getContentPane().add(btnUpdateMarks);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = sqlConnection.connectorDB();
					if(textField.getText().isEmpty() == false) {
						btnReset.setEnabled(true);
						String query = "select name,semester from data where usn =?";
						ps = con.prepareStatement(query);
						ps.setString(1,textField.getText());
						ResultSet rs = ps.executeQuery();
						textField_1.setText(rs.getString("name"));
						textField_2.setText(rs.getString("semester"));
						System.out.println(table.getValueAt(1, 1));
					}
				} catch(Exception e) {
					
				}
				textField_2.getDocument().addDocumentListener(new DocumentListener() {

					@Override
					public void changedUpdate(DocumentEvent arg0) {
						
						
					}

					@Override
					public void insertUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						btnUpdateMarks.setEnabled(true);
						
					}

					@Override
					public void removeUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
				});
				
			}
		});
		btnSearch.setBounds(298, 50, 97, 25);
		frame.getContentPane().add(btnSearch);
		
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Subject", "Marks"},
				{"SUBJECT 1", null},
				{"SUBJECT 2", null},
				{"SUBJECT 3", null},
				{"SUBJECT 4", null},
				{"SUBJECT 5", null},
				{"SUBJECT 6", null},
			},
			new String[] {
				"Subject", "Marks"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(585, 138, 294, 112);
		frame.getContentPane().add(table);
	}
}
