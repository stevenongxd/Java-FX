package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Load Drive
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String username = "root";
			String password = "";
			String server = "localhost:3306";
			String databaseName = "binus";
			String connectionString = String.format("jdbc:mysql://%s/%s", server, databaseName);
			
		//Connect to Database
			Connection con = DriverManager.getConnection(connectionString, username, password);
					
		//Execute Query
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Mahasiswa");
			
		//Insert New Data
			int totalAffectedRow = st.executeUpdate("INSERT INTO Mahasiswa VALUES ('2501959384', 'Setifen', 2) ");
			
		//Get Value from ResultSet
			while(rs.next()){
				String NIM = rs.getString("NIM");
				String Nama = rs.getString("Nama");
				Integer Semester = rs.getInt("Semester");
				
				System.out.println(NIM + " " + Nama + " " + Semester);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
