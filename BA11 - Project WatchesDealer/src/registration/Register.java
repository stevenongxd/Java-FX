package registration;

import user.CheckUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;

public class Register {
	
	Scene scene;
	BorderPane bp;
	GridPane gp;
	FlowPane topPane, bottomPane;
	HBox genderHB;

	Label registerLbl, nameLbl, genderLbl, emaiReglLbl, passwordRegLbl, confirmButtonLbl;
	TextField emailTF, nameTF;
	RadioButton maleRB, femaleRB;
	ToggleGroup  genderTG;
	PasswordField passwordPF, confirmPF;
	Button registerBtn, backBtn;

	Integer atValidate = 0;
	Integer dotValidate = 0;
	
	public void init() {
		bp = new BorderPane();
		gp = new GridPane();
		topPane = new FlowPane(Orientation.HORIZONTAL);
		bottomPane = new FlowPane(Orientation.HORIZONTAL);
		
		registerLbl = new Label("Register");
		registerLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		nameLbl = new Label("Name :");
		genderLbl = new Label("Gender :");
		emaiReglLbl = new Label("Email :");
		passwordRegLbl = new Label("Password :");
		confirmButtonLbl = new Label("Confirm Password :");
		
		emailTF = new TextField();
		emailTF.setPrefWidth(250);
		emailTF.setPromptText("Email Address");
		nameTF = new TextField();
		nameTF.setPromptText("Name");
		
		maleRB = new RadioButton("Male");
		femaleRB = new RadioButton("Female");
		
		genderTG = new ToggleGroup();
		maleRB.setToggleGroup(genderTG);
		femaleRB.setToggleGroup(genderTG);
		
		genderHB = new HBox();
		genderHB.getChildren().add(maleRB);
		genderHB.getChildren().add(femaleRB);
		genderHB.setSpacing(10);
		
		passwordPF = new PasswordField();
		passwordPF.setPromptText("Password");
		confirmPF = new PasswordField();
		confirmPF.setPromptText("Confirm Password");
		
		registerBtn = new Button("Register");
		registerBtn.setPrefWidth(250);
		registerBtn.setPrefHeight(30);
		registerBtn.setStyle("-fx-background-color: black");
		registerBtn.setTextFill(Color.WHITE);
		registerBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		
		backBtn = new Button("Back to Login");
		backBtn.setPrefWidth(250);
		backBtn.setPrefHeight(30);
		backBtn.setStyle("-fx-background-color: black");
		backBtn.setTextFill(Color.WHITE);
		backBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

		scene = new Scene(bp, 600, 700);
		scene.setFill(Color.AQUA);
		
//		topPane.getChildren().add(titleLbl);
//		topPane.setAlignment(Pos.CENTER);

		gp.add(registerLbl, 0, 0);
		GridPane.setHalignment(registerLbl, HPos.CENTER);
		
		gp.add(nameLbl, 0, 1);
		gp.add(nameTF, 0, 2);
		
		gp.add(genderLbl, 0, 3);
		gp.add(genderHB, 0, 4);
		
		gp.add(emaiReglLbl, 0, 5);
		gp.add(emailTF, 0, 6);
		
		gp.add(passwordRegLbl, 0, 7);
		gp.add(passwordPF, 0, 8);
		
		gp.add(confirmButtonLbl, 0, 9);
		gp.add(confirmPF, 0, 10);
		
		gp.add(registerBtn, 0, 14);
		gp.add(backBtn, 0, 15);
		
		gp.setVgap(12);
		gp.setMaxSize(400, 600);
		
		bp.setTop(topPane);
		bp.setCenter(gp);
		bp.setBottom(bottomPane);
		
		bp.setStyle("-fx-background-color: #D1FFF3;");
		gp.setStyle("-fx-background-color: white");
		topPane.setStyle("-fx-background-color: white");
		
		bp.setPadding(new Insets(50, 0, 50, 0));
		gp.setPadding(new Insets(50, 0, 0, 70));
		
	}
	
		public void eventHandler() {
			
			registerBtn.setOnMouseClicked(e -> {
				
				String error = "";
				if(nameTF.getText().length() < 5 || nameTF.getText().length() > 40) {
					error = "Username Invalid";
					
				}else if(maleRB.isSelected() == false && femaleRB.isSelected() == false) {
					error = "Select a gender";
					
				}else if(passwordPF.getText().length() < 6 || passwordPF.getText().length() > 20) {
					error = "Password Invalid";
					
				}else if(confirmPF.getText().equals(passwordPF.getText()) == false) {
					error = "Password is not the same";
					
				}
				
				for (int i = 0; i < emailTF.getText().length(); i++) {
						if (emailTF.getText().charAt(i) == '@') {
							atValidate++;
							
							if(atValidate > 1) {
								error = "Error";
							}
						} else if (emailTF.getText().charAt(i) == '.') {
							dotValidate++;
							
							if(dotValidate > 1) {
								error = "Error";
							}
						}

						if (emailTF.getText().charAt(i) == '.' && atValidate < 1) {
							error = "Error";
						}

						if (emailTF.getText().charAt(i) == '@' && emailTF.getText().length() > 1 && emailTF.getText().charAt(i + 1) == '.') {
							error = "Error";
						}
					}
					
					if (emailTF.getText().startsWith("@") || emailTF.getText().endsWith("@")) {
						error = "Error";
					}

					if (emailTF.getText().startsWith(".") || emailTF.getText().endsWith(".")) {
						error = "Error";
					}
					
					if(emailTF.getText().endsWith(".com") == false) {
						error = "Error";
					}
				
				if(error.length() > 0) {
					Alert popUp = new Alert(AlertType.ERROR);
					popUp.setHeaderText(error);
					popUp.setContentText("Invalid Credential");
					popUp.show();
				}else {
					Alert popUp = new Alert(AlertType.INFORMATION);
					popUp.setHeaderText("Message");
					popUp.setContentText("Register Successful!");
					popUp.show();
				}
				String genderSelect;
				
				if(maleRB.isSelected()) {
				genderSelect = maleRB.getText();
				
				} else if(femaleRB.isSelected()) {
				genderSelect = femaleRB.getText();
				
				} else {
				genderSelect = null;
				}
				
				CheckUser.registerUser(nameTF.getText(), emailTF.getText(), passwordPF.getText(), genderSelect);
			});
			
			backBtn.setOnMouseClicked(e -> {
				Login log = Login.getData();
				log.sceneControl();
			});
			
		}
		
private static Register registerForm;
	
	public static Register getData() {
	if (registerForm.equals(null)) {
		registerForm = new Register();
	}
	return registerForm;
}
	
	public void sceneControl() {
		init();
		eventHandler();
		Main.sceneChanger(scene, "Register");
	}
	
}
