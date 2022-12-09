package main;

import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application{
	
		public static void main(String[] args) {
		launch(args);
	}
	
	Label title;
	Label usernameL;
	Label passL;
	Label genderL;
	Label cmL;
	TextField usernameTF;
	PasswordField passPF;
	TextArea cmTA;
	RadioButton maleRB;
	RadioButton femaleRB;
	ToggleGroup genderTG;
	CheckBox agreeCB;
	Button registerB;
	Button cancelB;
	FlowPane topPane;
	GridPane centerPane;
	VBox genderPane;
	FlowPane bottomPane;
	BorderPane mainPane;
	Scene scene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		title = new Label("Registration Form");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		usernameL = new Label("Username");
		passL = new Label("Password");
		genderL = new Label("Gender");
		cmL = new Label("Comment");
		
		usernameTF = new TextField();
		passPF = new PasswordField();
		cmTA = new TextArea();
		maleRB = new RadioButton("Male");
		femaleRB = new RadioButton("Female");
		genderTG = new ToggleGroup();
		maleRB.setToggleGroup(genderTG);
		femaleRB.setToggleGroup(genderTG);
		agreeCB = new CheckBox("I Agree with the terms and conditions");
		
		registerB = new Button("Register");
		cancelB = new Button("Cancel");
		
		topPane = new FlowPane();
		topPane.setPadding(new Insets(10, 0, 10, 0));
		topPane.getChildren().add(title);
		topPane.setAlignment(Pos.TOP_CENTER);
		
		centerPane = new GridPane();
		centerPane.setAlignment(Pos.CENTER);
		centerPane.setHgap(10);
		centerPane.setVgap(10);
		centerPane.add(usernameL, 0, 0);
		centerPane.add(passL, 0, 1);
		centerPane.add(cmL, 0, 2);
		centerPane.add(genderL, 0, 3);
		centerPane.add(usernameTF, 1, 0);
		centerPane.add(passPF, 1, 1);
		centerPane.add(cmTA, 1, 2);
		
		genderPane = new VBox(5);
		genderPane.getChildren().add(maleRB);
		genderPane.getChildren().add(femaleRB);
		centerPane.add(genderPane, 1, 3);
		
		centerPane.add(agreeCB, 1, 4);
		
		bottomPane = new FlowPane();
		bottomPane.setPadding(new Insets(10, 0, 10, 0));
		bottomPane.setHgap(10);
		bottomPane.getChildren().addAll(registerB, cancelB);
		bottomPane.setAlignment(Pos.BOTTOM_CENTER);
		
		mainPane = new BorderPane();
		mainPane.setTop(topPane);
		mainPane.setCenter(centerPane);
		mainPane.setBottom(bottomPane);
		
		scene = new Scene(mainPane);
		primaryStage.show();
	}

}
