package backend;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Transfer extends Connectdb {
	
	
	public static float getdata(int acc ,int p,float a) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
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
		 if (bal<a || a>25000) {
			 JOptionPane.showMessageDialog(null,"Amount Exceeding Limit");  //checking Transfer limit
			 
		 }
		 else
			 bal=bal-a;   //withdraw
		  
		 
		 String sql=("update bank.accountdetails set balance=? where pin=?");
		 PreparedStatement s1= con.prepareStatement(sql);
		 s1.setFloat(1, bal);
		 s1.setInt(2, p);
		 int rs1=s1.executeUpdate();
		 
		 
		 String query1=("SELECT balance FROM bank.accountdetails WHERE accno=? ");
			float bal1;
			PreparedStatement s3= con.prepareStatement(query1);
			s3.setInt(1, acc);
			ResultSet rs3=s3.executeQuery();
			 if (rs3.next()) {
				 bal1=rs3.getFloat("balance");
			     bal1=bal1+a;            //Depositing
			
		 
		 String sql1=("update bank.accountdetails set balance=? where accno=?");
		 PreparedStatement s2= con.prepareStatement(sql1);
		 s2.setFloat(1, bal1);
		 s2.setInt(2, acc);
		 int rs2=s2.executeUpdate();
		 
		JOptionPane.showMessageDialog(null," Amount Tranfered Current Balance :"+bal);
			 }
	 }
     else
    	 JOptionPane.showMessageDialog(null,"Invalid Credentials");
	return 0;
	      
	     
	 
		
	}

}



