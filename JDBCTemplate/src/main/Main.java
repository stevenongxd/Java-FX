package main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Registrant;
import util.Connect;

public class Main extends Application{

	Scene scene;
	BorderPane bp;
	FlowPane fp;
	TableView table;
	Label titleLbl, nameLbl, addressLbl, ageLbl, ipkLbl, viewLbl;
	Vector<Registrant> registrants;
	VBox vbox;
	HBox hbox;
	TextField nameField, addressField, ageField, ipkField, viewTextField;
	GridPane gp;
	Button addBtn, updateBtn, deleteBtn, viewButton;
	Connect connect = Connect.getInstance();
	
	Integer tempID = null;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void init() {
		bp = new BorderPane();
		fp = new FlowPane();
		table = new TableView<Registrant>();
		titleLbl = new Label("Manage Registrant");
		nameLbl = new Label("Name");
		addressLbl = new Label("Address");
		ageLbl = new Label("Age");
		ipkLbl = new Label("IPK");
		viewLbl = new Label("View");
		registrants = new Vector<Registrant>(); 
		vbox = new VBox();
		hbox = new HBox();
		nameField = new TextField();
		addressField = new TextField(); 
		ageField= new TextField();
		ipkField= new TextField();
		viewTextField = new TextField();
		gp = new GridPane();
		addBtn = new Button("add");
		updateBtn = new Button("Update");
		deleteBtn = new Button("Delete");
		viewButton = new Button("View");
		
		refreshTable();
		
		TableColumn<Registrant, Integer> idCol = new TableColumn("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
		
		TableColumn<Registrant, String> nameCol = new TableColumn("name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Registrant, String> addressCol = new TableColumn("address");
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		TableColumn<Registrant, Integer> ipkCol = new TableColumn("ipk");
		ipkCol.setCellValueFactory(new PropertyValueFactory<>("ipk"));
		
		TableColumn<Registrant, Integer> ageCol = new TableColumn("age");
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		
		table.getColumns().addAll(idCol, nameCol, ageCol, addressCol, ipkCol);
		
		
		
	}
	
	public void styling() {
		gp.add(nameLbl, 0, 0);
		gp.add(nameField, 1, 0);
		gp.add(ageLbl, 0, 1);
		gp.add(ageField, 1, 1);
		gp.add(addressLbl, 0, 2);
		gp.add(addressField, 1, 2);
		gp.add(ipkLbl, 0, 3);
		gp.add(ipkField, 1, 3);
		gp.add(viewLbl, 0, 4);
		gp.add(viewTextField, 1, 4);
		
		vbox.getChildren().add(gp);
		hbox.getChildren().addAll(addBtn, deleteBtn, updateBtn, viewButton);
		
		bp.setTop(table);
		bp.setCenter(vbox);
		bp.setBottom(hbox);
	}
	
	public void addData(String name, String address, Integer age, Double ipk) {
		String query = "INSERT INTO registrant " + "VALUES ('0','"+name+"', '"+age+"','"+address+"','"+ipk+"')";
		connect.execUpdate(query);
	}
	
	public void getData() {
		registrants.removeAllElements();
		
		String query = "SELECT * FROM registrant";
		
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				Integer id = connect.rs.getInt("ID");
				String name = connect.rs.getString("Name");
				String address = connect.rs.getString("Address");
				Integer age = connect.rs.getInt("Age");
				Double ipk = connect.rs.getDouble("IPK");
				registrants.add(new Registrant(id, name, address, age, ipk));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void refreshTable() {
		getData();
		ObservableList<Registrant> regObs = FXCollections.observableArrayList(registrants);
		table.setItems(regObs);
	}
	
	public void handle() {
		addBtn.setOnAction((event) -> {
			String name = nameField.getText();
			String address = addressField.getText();;
			Integer age = Integer.valueOf(ageField.getText());
			Double ipk = Double.valueOf(ipkField.getText());
			
			addData(name, address, age, ipk);
			refreshTable();
		});
		
		viewButton.setOnAction((event) -> {
			String query = String.format("SELECT * FROM registrant WHERE name = '%s'", viewTextField.getText());
			System.out.println(query);
			connect.rs = connect.execQuery(query);
			
			try {
				while(connect.rs.next()) {
					System.out.println(connect.rs.getString("Name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		deleteBtn.setOnAction((event) -> {
//			code here (delete)
			
				
		});
		
		updateBtn.setOnAction((event) -> {
			// code here (update)
			
			
		});
		
		table.setOnMouseClicked((event) -> {
			TableSelectionModel<Registrant> tableSelectionModel = table.getSelectionModel();
			tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
			Registrant regis = tableSelectionModel.getSelectedItem();
			
			nameField.setText(regis.getName());
			addressField.setText(regis.getAddress());
			ageField.setText(regis.getAge().toString());
			ipkField.setText(regis.getIpk().toString());
			tempID = regis.getID();
		});
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		init();
		styling();
		handle();
		scene = new Scene(bp,800,800);
		stage.setScene(scene);
		stage.show();
		
	}

}
