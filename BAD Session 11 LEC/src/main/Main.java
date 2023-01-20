package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

	Vector<Mahasiswa> vectorMahasiswa = new Vector<>();
	
	public void getData() {
		
		//connect ke database dan isi data ke vector
		try {
			//1. Load Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. Setup Connection
			final String username = "root"; // change with your MySQL username, the default username is 'root'
			final String password = ""; // change with your MySQL password, the default password is empty
			final String database = "session11badlec"; // change with the database name that you use
			final String server = "localhost:3306"; // change with your MySQL host, the default port is 3306
			final String CconnectionString = String.format("jdbc:mysql://%s/%s", server, database);
			
			//3. Connect to DB
				Connection con = DriverManager.getConnection(CconnectionString, username, password);
			
			//4. Execute Query
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Mahasiswa");
			
			//5. Get Data from RS & Insert to Vector
			while(rs.next()) {
				String NIM = rs.getString("nim");
				String name = rs.getString("Name");
				Integer age = rs.getInt("Age");
				
				Mahasiswa mhs = new Mahasiswa(NIM, name, age);
				vectorMahasiswa.add(mhs);
			}
			
			//6. Close DB
			con.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		TableView<Mahasiswa> tableMhs = new TableView<Mahasiswa>();
		
		//Setup Kolom NIM
		TableColumn<Mahasiswa, String> nimColumn = new TableColumn<Mahasiswa, String>("NIM");
		nimColumn.setCellValueFactory(new PropertyValueFactory<>("nim"));
		nimColumn.setMinWidth(100);
		
		//Setup Kolom Nama
		TableColumn<Mahasiswa, String> nameColumn = new TableColumn<Mahasiswa, String>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setMinWidth(100);
				
		//Setup Kolom Umur
		TableColumn<Mahasiswa, String> ageColumn = new TableColumn<Mahasiswa, String>("Age");
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		ageColumn.setMinWidth(100);
		
		//Add Columns to Table
		tableMhs.getColumns().addAll(nimColumn, nameColumn, ageColumn);
		tableMhs.setMaxWidth(300);
		tableMhs.setMinWidth(300);
		
		//Add Rows to Table
		getData();
		ObservableList<Mahasiswa> data = FXCollections.observableArrayList(vectorMahasiswa);
		tableMhs.setItems(data);
		
		//Add Table to GUI Layout
		BorderPane mainLayout = new BorderPane();
		mainLayout.setCenter(tableMhs);
		
		//Set Scene to Use BorderPane
		Scene scene = new Scene(mainLayout, 300, 600);
		
		//Set Stage to Use Scene
		primaryStage.setScene(scene);
		primaryStage.setTitle("Mahasiswa Apps v1.0");
		primaryStage.show();
		
	}

}
