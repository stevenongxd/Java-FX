package main;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import user.User;


public class Login extends Application{
	
	Scene scene;
	BorderPane bp;
	GridPane gp;
	FlowPane topPane, bottomPane;
	Label loginLbl, emaiReglLbl, passwordRegLbl;
	TextField emailTF;
	PasswordField passwordPF;
	Button loginBtn, backBtn;
	
	public void init() {
		bp = new BorderPane();
		gp = new GridPane();
		topPane = new FlowPane(Orientation.HORIZONTAL);
		bottomPane = new FlowPane(Orientation.HORIZONTAL);
		
		loginLbl = new Label("Watches Dealer Login");
		loginLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		emaiReglLbl = new Label("Email :");
		passwordRegLbl = new Label("Password :");
		
		emailTF = new TextField();
		emailTF.setPrefWidth(250);
		emailTF.setPromptText("Email Address");
		
		passwordPF = new PasswordField();
		passwordPF.setPromptText("Password");
			
		loginBtn = new Button("Login");
		loginBtn.setPrefWidth(250);
		loginBtn.setPrefHeight(30);
		loginBtn.setStyle("-fx-background-color: black");
		loginBtn.setTextFill(Color.WHITE);
		loginBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		
		backBtn = new Button("Register Instead");
		backBtn.setPrefWidth(250);
		backBtn.setPrefHeight(30);
		backBtn.setStyle("-fx-background-color: black");
		backBtn.setTextFill(Color.WHITE);
		backBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

		scene = new Scene(bp, 600, 700);
		scene.setFill(Color.AQUA);
		
//		topPane.getChildren().add(titleLbl);
//		topPane.setAlignment(Pos.CENTER);

		gp.add(loginLbl, 0, 0);
		GridPane.setHalignment(loginLbl, HPos.CENTER);
		
		gp.add(emaiReglLbl, 0, 2);
		gp.add(emailTF, 0, 3);
		
		gp.add(passwordRegLbl, 0, 4);
		gp.add(passwordPF, 0, 5);
		
		gp.add(loginBtn, 0, 8);
		gp.add(backBtn, 0, 9);
		
		gp.setVgap(12);
		gp.setMaxSize(400, 400);
		
		bp.setTop(topPane);
		bp.setCenter(gp);
		bp.setBottom(bottomPane);
		
		bp.setStyle("-fx-background-color: #D1FFF3;");
		gp.setStyle("-fx-background-color: white");
		topPane.setStyle("-fx-background-color: white");
		
		bp.setPadding(new Insets(50, 0, 50, 0));
		gp.setPadding(new Insets(70, 0, 0, 70));
		
	}
	
	public void eventHandler() {
		loginBtn.setOnMouseClicked(e -> {

			if (emailTF.getText().isEmpty() || passwordPF.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Fields cannot be empty!");
				alert.showAndWait();
				
			} else {
				User.login(emailTF.getText(), passwordPF.getText());
			}
		});
		
		backBtn.setOnMouseClicked(e -> {
			Register register = Register.getData();
		});
	}
	
	
	public static Login getData() {
		Login loginForm = new Login();
	return loginForm;
}

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		eventHandler();
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}