package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Register extends BorderPane{
	
	GridPane gp, gp2;
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
	Button addUserB;
	
	public Register() {
		usernameL = new Label("Username");
		usernameTF = new TextField();
		
		passL = new Label("Password");
		passPF = new PasswordField();
		
		genderL = new Label("Gender");
		maleRB = new RadioButton("Male");
		femaleRB = new RadioButton("Female");
		genderTG = new ToggleGroup();
		maleRB.setToggleGroup(genderTG);
		femaleRB.setToggleGroup(genderTG);
		
		genderPane = new HBox(10);
		genderPane.getChildren().add(maleRB);
		genderPane.getChildren().add(femaleRB);
		
		countryL = new Label("Country");
		countryCBX = new ComboBox<>();
		countryCBX.getItems().add("Indonesia");
		countryCBX.getItems().add("Malaysia");
		countryCBX.getItems().add("Australia");
		countryCBX.getItems().add("Singapore");
		countryCBX.getSelectionModel().select(0);
		
		ageL = new Label("Age");
		ageS = new Spinner<>(1, 100, 0, 1);
		
		addressL = new Label("Address");
		addressTA = new TextArea();
		
		agreeL = new Label();
		agreeCB = new CheckBox("Terms and Conditions");
		
		addUserB = new Button("Add User");
		addUserB.disableProperty().bind(agreeCB.selectedProperty().not());
		
		gp = new GridPane();
		gp2 = new GridPane();
		
		gp.setAlignment(Pos.CENTER);
		gp2.setAlignment(Pos.CENTER);
		
		gp.setHgap(10);
		gp.setVgap(10);
		
		gp2.setPadding(new Insets(0, 0, 30, 0));
		
		
		gp2.add(addUserB, 0, 0);
		
		gp.add(usernameL, 0, 0);
		gp.add(usernameTF, 1, 0);
		
		gp.add(passL, 0, 1);
		gp.add(passPF, 1, 1);
		
		gp.add(genderL, 0, 2);
		gp.add(genderPane, 1, 2);
		
		gp.add(countryL, 0, 3);
		gp.add(countryCBX, 1, 3);
		
		gp.add(ageL, 0, 4);
		gp.add(ageS, 1, 4);
		
		gp.add(addressL, 0, 5);
		gp.add(addressTA, 1, 5);
		
		gp.add(agreeL, 0, 6);
		gp.add(agreeCB, 1, 6);
		
		gp.add(gp2, 0, 7);
		
		setCenter(gp);
		setBottom(gp2);
		
		addUserB.setOnAction(e ->{
			if(usernameTF.getText().isEmpty()) {
				alertError();
				return;
			}
			
			if(passPF.getText().isEmpty()) {
				alertError();
				return;
			}
			
			if(genderTG.getSelectedToggle().isSelected() == false) {
				alertError();
				return;
			}
			
			if(countryCBX.getSelectionModel().getSelectedItem() == null) {
				alertError();
				return;
			}
			
			if(ageS.getValue() < 1) {
				alertError();
				return;
			}
			
			if(addressTA.getText().isEmpty()) {
				alertError();
				return;
			}
			
			if(agreeCB.isSelected() == false) {
				alertError();
				return;
			}
			
			alertSuccess();
			
		});
	}
	
	public void alertError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Validation Error");
		alert.setContentText("Please fill all fields first");
		alert.show();
	}
	
	public void alertSuccess() {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Information");
			alert.setContentText("Welcome, " + usernameTF.getText());
			alert.show();
	}

}
