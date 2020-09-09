package backend;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class With extends Connectdb{
	
   
	
	public static float getdata(String c,int p,float a) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
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
		 if(c=="w") {
			 
		 bal=rs.getFloat("balance");
		 if (bal<a || a>25000) {
			 JOptionPane.showMessageDialog(null,"Amount Exceeding Limit");  //checking withdraw limit
			 
		 }
		 else
			 bal=bal-a;   //withdraw
		  
		 }
		 else if (c=="d") {
		 bal=rs.getFloat("balance")+a;//deposit
		 }
		 else
			 return rs.getFloat("balance");
		 String sql=("update bank.accountdetails set balance=? where pin=?");
		 PreparedStatement s1= con.prepareStatement(sql);
		 s1.setFloat(1, bal);
		 s1.setInt(2, p);
		 int rs1=s1.executeUpdate();
		 
		JOptionPane.showMessageDialog(null,"Current Balance :"+bal);
		 
		 
	 }
     else
    	 JOptionPane.showMessageDialog(null,"Invalid Credentials");
	return 0;
	      
	     
	 
		
	}

}
