package javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JavaFX extends Application {

	Scene scene;
	BorderPane mainPane;
	GridPane centerPane;
	FlowPane topPane, bottomPane;
	HBox genderPane;
	
	Label usernameL, passL, confirmpassL, genderL, countryL;
	TextField usernameTF;
	PasswordField passPF, confirmpassPF;
	RadioButton maleRB, femaleRB;
	ToggleGroup genderTG;
	ComboBox<String> countryCBX;
	Button register;
	
	public void username() {
		usernameL = new Label("Username");
		usernameTF = new TextField();
	}
	
	public void password() {
		passL = new Label("Password");
		passPF = new PasswordField();
	}
	
	public void confirmpass() {
		confirmpassL = new Label("Confirm Password");
		confirmpassPF = new PasswordField();
	}
	
	public void gender() {
		genderL = new Label("Gender");
		maleRB = new RadioButton("Male");
		femaleRB = new RadioButton("Female");
		genderTG = new ToggleGroup();
		maleRB.setToggleGroup(genderTG);
		femaleRB.setToggleGroup(genderTG);
		
		genderPane = new HBox(10);
		genderPane.getChildren().add(maleRB);
		genderPane.getChildren().add(femaleRB);
	}
	
	public void country() {
		countryL = new Label("Country");
		countryCBX = new ComboBox<>();
		countryCBX.getItems().add("Indonesia");
		countryCBX.getItems().add("Malaysia");
		countryCBX.getItems().add("Singapora");
		countryCBX.getItems().add("Laos");
		countryCBX.getItems().add("Kamboja");
		countryCBX.getSelectionModel().select(0);
	}
	
	public void butreg() {
		register = new Button("Register");
	}
	
	public void centerPane() {
		centerPane = new GridPane();
		centerPane.setPadding(new Insets(5, 0, 0, 0));
		centerPane.setAlignment(Pos.CENTER);
		centerPane.setHgap(20);
		centerPane.setVgap(2);
		
		centerPane.add(usernameL, 0, 0);
		centerPane.add(usernameTF, 1, 0);
		
		centerPane.add(passL, 0, 1);
		centerPane.add(passPF, 1, 1);
		
		centerPane.add(confirmpassL, 0, 2);
		centerPane.add(confirmpassPF, 1, 2);
		
		centerPane.add(genderL, 0, 3);
		centerPane.add(genderPane, 1, 3);
		
		centerPane.add(countryL, 0, 4);
		centerPane.add(countryCBX, 1, 4);
	}
	
	public void bottomPane() {
		bottomPane = new FlowPane();
		bottomPane.setPadding(new Insets(5, 0, 5, 0));
		bottomPane.getChildren().add(register);
		bottomPane.setAlignment(Pos.CENTER);
	}
	
	public void mainPane() {
		mainPane = new BorderPane();
		mainPane.setCenter(centerPane);
		mainPane.setBottom(bottomPane);
		
		scene = new Scene(mainPane);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		username();
		password();
		confirmpass();
		gender();
		country();
		butreg();
		centerPane();
		bottomPane();
		mainPane();
		primaryStage.setTitle("Register Page");
		primaryStage.setMaxWidth(350); 
		primaryStage.setMaxHeight(175);
		primaryStage.setResizable(true);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
