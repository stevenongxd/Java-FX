package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application{
	
	//ga pake java fx yg extra, jadi menu bar agak aneh karena tabrakan sama title
	
	Scene scene;
	BorderPane mainPane;
	GridPane centerPane;
	FlowPane topPane, bottomPane;
	HBox genderPane;
	
	Label title, usernameL, passL, genderL, countryL, ageL, agreeL, addressL;
	TextField usernameTF;
	PasswordField passPF;
	RadioButton maleRB, femaleRB;
	ToggleGroup genderTG;
	ComboBox<String> countryCBX;
	Spinner<Integer> ageS;
	CheckBox agreeCB;
	TextArea addressTA;
	Button sub;
	MenuBar menuB;
	Menu menu;
	MenuItem addUserMI, exitMI;
	
	public void title() {
		title = new Label("Add User");
		title.setFont(Font.font("Roboto", FontWeight.BOLD, 15));
	}
	
	public void username() {
		usernameL = new Label("Username");
		usernameTF = new TextField();
		usernameTF.setPromptText("Input Username");
	}
	
	public void password() {
		passL = new Label("Password");
		passPF = new PasswordField();
		passPF.setPromptText("Input Password");
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
		countryCBX.getItems().add("Australia");
		countryCBX.getItems().add("Singapore");
		countryCBX.getSelectionModel().select(0);
	}
	
	public void age() {
		ageL = new Label("Age");
		ageS = new Spinner<>(1, 100, 1, 1);
	}
	
	public void address() {
		addressL = new Label("Address");
		addressTA = new TextArea();
	}
	
	public void agree() {
		agreeL = new Label();
		agreeCB = new CheckBox("Terms and Conditions");
	}
	
	public void submit() {
		sub = new Button("Add User");
	}
	
	public void menu() {
		menuB = new MenuBar();
		menu = new Menu("Menu");
		addUserMI = new MenuItem("Add User");
		exitMI = new MenuItem("Exit");
		
		menuB.getMenus().add(menu);
		menu.getItems().addAll(addUserMI, exitMI);
	}
	
	public void topPane() {
		topPane = new FlowPane();
		topPane.setPadding(new Insets(10, 0, 10, 0));
		topPane.getChildren().addAll(menuB, title);
		topPane.setHgap(10);
		topPane.setAlignment(Pos.CENTER);
	}
	
	public void centerPane() {
		centerPane = new GridPane();
		centerPane.setAlignment(Pos.CENTER);
		centerPane.setHgap(10);
		centerPane.setVgap(10);
		centerPane.add(usernameL, 0, 0);
		centerPane.add(usernameTF, 1, 0);
		
		centerPane.add(passL, 0, 1);
		centerPane.add(passPF, 1, 1);
		
		centerPane.add(genderL, 0, 2);
		centerPane.add(genderPane, 1, 2);
		
		centerPane.add(countryL, 0, 3);
		centerPane.add(countryCBX, 1, 3);
		
		centerPane.add(ageL, 0, 4);
		centerPane.add(ageS, 1, 4);
		
		centerPane.add(addressL, 0, 5);
		centerPane.add(addressTA, 1, 5);
		
		centerPane.add(agreeL, 0, 6);
		centerPane.add(agreeCB, 1, 6);
	}
	
	public void bottomPane() {
		bottomPane = new FlowPane();
		bottomPane.setPadding(new Insets(10, 0, 10, 0));
		bottomPane.getChildren().add(sub);
		bottomPane.setAlignment(Pos.CENTER);
	}
	
	public void mainPane() {
		mainPane = new BorderPane();
		mainPane.setTop(topPane);
		mainPane.setCenter(centerPane);
		mainPane.setBottom(bottomPane);
		
		scene = new Scene(mainPane);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		title();
		username();
		password();
		gender();
		country();
		age();
		address();
		agree();
		menu();
		submit();
		topPane();
		centerPane();
		bottomPane();
		mainPane();
		primaryStage.setTitle("JH Shop");
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
