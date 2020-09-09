package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class Connectdb {
	
	static Connection con=null;
	static String name="";
	static String url="jdbc:mysql://localhost:3306/"+name;
	static String username="xxxxxxxx";
	static String pswd="xxxxxxxxxxx";
	
	public static boolean getdata(String n,int a,int p) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			con=DriverManager.getConnection(url, username, pswd);
		} catch (SQLException e) {
			// TODO Auto-generate catch block
			e.printStackTrace();
		}
	String query=("SELECT * FROM bank.accountdetails WHERE name=? AND pin=? AND accno=?");
	PreparedStatement s= con.prepareStatement(query);
	s.setString(1, n);
	s.setInt(2, p);
	s.setInt(3, a);
	ResultSet rs=s.executeQuery();
	 if (rs.next())
		 return true;
     else
    	 JOptionPane.showMessageDialog(null,"Invalid Credentials");
	     return false;
	 
		
	}

}
