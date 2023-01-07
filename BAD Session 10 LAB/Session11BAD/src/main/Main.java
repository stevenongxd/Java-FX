package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.User;

public class Main extends Application {
	Scene scene;
	BorderPane bp;
	GridPane gp;
	HBox btnHBox;
	Label usernameLabel, passwordLabel;
	TextField usernameTF, passwordTF;
	Button addBtn;
	TableView<User> userTable;
	ArrayList<User> userList;
	Connect connect = Connect.getConnection();

	public void init() {
		bp = new BorderPane();
		gp = new GridPane();
		btnHBox = new HBox(10);

		usernameLabel = new Label("Username");
		passwordLabel = new Label("Password");

		usernameTF = new TextField();
		usernameTF.setPromptText("Input username");

		passwordTF = new TextField();
		passwordTF.setPromptText("Input password");

		addBtn = new Button("Add");

		userTable = new TableView<>();
		userList = new ArrayList<>();

		scene = new Scene(bp, 600, 600);
	}

	public void setLayout() {
		gp.add(usernameLabel, 0, 0);
		gp.add(usernameTF, 0, 1);
		gp.add(passwordLabel, 0, 2);
		gp.add(passwordTF, 0, 3);

		btnHBox.getChildren().add(addBtn);
		gp.add(btnHBox, 0, 5);

		gp.setPadding(new Insets(20));

		gp.setHgap(20);
		gp.setVgap(10);

		bp.setTop(gp);
		bp.setBottom(userTable);
	}

	public void setTable() {
		// 1 TODO: set table
		TableColumn<User, Integer> userIdColumn = new TableColumn<User, Integer>("User ID");
		TableColumn<User, String> usernameColumn = new TableColumn<User, String>("Username");
		TableColumn<User, String> passwordColumn = new TableColumn<User, String>("Password");
		
		userIdColumn.setMinWidth(200);
		usernameColumn.setMinWidth(200);
		passwordColumn.setMinWidth(200);
		
//		userIdColumn.setMinWidth(bp.getWidth() / 3);
		
		userIdColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
		
		userTable.getColumns().addAll(userIdColumn, usernameColumn, passwordColumn);
	}

	public void refreshTable() {
		userList.clear();
		getUser();
		ObservableList<User> userObs = FXCollections.observableArrayList(userList);
		userTable.setItems(userObs);
	}

	public void getUser() {
		// 2 TODO: get user from database button
		String query = "SELECT * FROM `user`";
		
		ResultSet rs = connect.executeQuery(query);
		
		try {
			while(rs.next()) {
				Integer userId = rs.getInt("userId");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				User user = new User(userId, username, password);
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setEvent() {
		// 3 TODO: add button
		addBtn.setOnMouseClicked(e -> {
			String username = usernameTF.getText();
			String password = passwordTF.getText();
			
			if(username.isEmpty() || password.isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR, "Username & Password Cannot Be Empty!");
				alert.showAndWait();
			}else {
//			String query = "INSERT INTO `user`(`userId`, `username`, `password`) VALUES ('[value-1]','[value-2]','[value-3]')";
				String query = String.format("INSERT INTO `user`(`userId`, `username`, `password`) VALUES ('%d', '%s', '%s')", 0, username, password);
				connect.executeUpdate(query);
				refreshTable();
				refreshLayout();
				Alert alert = new Alert(AlertType.INFORMATION, "User Successfully Added!");
				alert.showAndWait();
			}
			
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		setLayout();
		setTable();
		refreshTable();
		setEvent();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void refreshLayout() {
		usernameTF.setText("");
		passwordTF.setText("");
	}
}
