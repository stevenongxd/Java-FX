package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	
	Scene scene;
	BorderPane bPane;
	GridPane gPane;
	FlowPane fPane;
	
	Label nameLabel, passwordLabel, titleLabel;
	TextField firstNameTF, lastNameTF;
	PasswordField passwordPF;
	Button registerB;
	
	public void initialize() {
		bPane = new BorderPane();
		gPane = new GridPane();
		fPane = new FlowPane();
		
		nameLabel = new Label("Name");
		passwordLabel = new Label("Password");
		titleLabel = new Label("Register Form");
		
		firstNameTF = new TextField();
		firstNameTF.setPromptText("First Name");
		
		lastNameTF = new TextField();
		lastNameTF.setPromptText("Last Name");
		
		passwordPF = new PasswordField();
		passwordPF.setPromptText("Password");
		
		registerB = new Button("Register");
		
		scene = new Scene(bPane, 500, 200);
	}
	
	public void arrageComponent() {
//		bPane.setTop(new Label("Top"));
//		bPane.setCenter(new Label("Center"));
//		bPane.setLeft(new Label("Left"));
//		bPane.setRight(new Label("Right"));
//		bPane.setBottom(new Label("Bottom"));
		
		firstNameTF.setMinWidth(170);
		lastNameTF.setMinWidth(170);
		passwordPF.setMaxWidth(350);
		
		fPane.getChildren().add(firstNameTF);
		fPane.getChildren().add(lastNameTF);
		fPane.setHgap(10);
		
		gPane.add(nameLabel, 0, 0);
		gPane.add(passwordLabel, 0, 1);
		gPane.add(fPane, 1, 0);
		gPane.add(passwordPF, 1, 1);
		
		BorderPane.setAlignment(titleLabel, Pos.CENTER);
		BorderPane.setAlignment(registerB, Pos.CENTER);
//		BorderPane.setAlignment(gPane, Pos.CENTER); <- Cuman bisa object
		gPane.setAlignment(Pos.CENTER);
		
		gPane.setHgap(10);
		gPane.setVgap(10);
		
		bPane.setPadding(new Insets(10));
		
		titleLabel.setFont(new Font("Roboto", 20));
		
		bPane.setTop(titleLabel);
		bPane.setCenter(gPane);
		bPane.setBottom(registerB);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		arrageComponent();
		primaryStage.setScene(scene);
		primaryStage.setTitle("BAD Session 6");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
