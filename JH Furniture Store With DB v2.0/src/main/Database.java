package main;

import java.sql.*;

public final class Database {
	
	private final String USERNAME = "root"; // change with your MySQL username, the default username is 'root'
	private final String PASSWORD = ""; // change with your MySQL password, the default password is empty
	private final String DATABASE = "case12badlab"; // change with the database name that you use
	private final String HOST = "localhost:3306"; // change with your MySQL host, the default port is 3306
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	private Connection con;
	private Statement st;
	private static Database connect;
	
    private Database() {
    	try {  
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);  
            st = con.createStatement(); 
        } catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("Failed to connect the database, the system is terminated!");
        	System.exit(0);
        }  
    }
    
    public static synchronized Database getConnection() {
		return connect = (connect == null) ? new Database() : connect;
    }

    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
    	try {
            rs = st.executeQuery(query);
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return rs;
    }

    public void executeUpdate(String query) {
    	try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public PreparedStatement prepareStatement(String query) {
    	PreparedStatement ps = null;
    	try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ps;
    }
}
