package backend;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Bal extends Connectdb{
	
	public static boolean getdata(int p) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			con=DriverManager.getConnection(url, username, pswd);
		} catch (SQLException e) {
			// TODO Auto-generate catch block
			e.printStackTrace();
		}
		String query=("SELECT balance FROM bank.accountdetails WHERE pin=? ");
		float bal;
		PreparedStatement s= con.prepareStatement(query);
		s.setInt(1, p);
		ResultSet rs=s.executeQuery();
		 if (rs.next()) {
			 bal=rs.getFloat("balance");
			 JOptionPane.showMessageDialog(null,"Current Balance :"+bal);
			 return true;
		 }
		 else
			 return false;
	}
		

}
