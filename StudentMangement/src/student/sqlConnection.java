package student;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class sqlConnection {
	static Connection con = null;
	public static Connection connectorDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite2\\comp1.db");
			return con;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
