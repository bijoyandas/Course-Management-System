package com.College;
import java.sql.*;
public class MyConnection {
	
	    static Connection con=null;
	    public static Connection getConnection()
	    {
	        if (con != null) return con;
	        // get db, user, pass from settings file
	        return getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bijoyan", "admin");
	    }

	    private static Connection getConnection(String db_name,String user_name,String password)
	    {
	        try
	        {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            con=DriverManager.getConnection(db_name,user_name,password);
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }

	        return con;        
	    
	} 
}
