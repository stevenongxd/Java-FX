package main;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {
	
	BorderPane bp, loginPage, regPage;
	Window login;
	GridPane gp1, gp2;
	Label usernameL, passL, regL;
	TextField nameTF;
	PasswordField passPF;
	Button signIn;
	MenuBar mb;
	Menu m;
	MenuItem miLogin, miAdd, miExit;
	Scene sc;
	
	public void menuSet() {
		
		gp1 = new GridPane();
		gp2 = new GridPane();
		
		usernameL = new Label("Username");
		passL = new Label("Password");
		regL = new Label("Don't have an account?");
		
		nameTF = new TextField();
		
		passPF = new PasswordField();
		
		signIn = new Button("Sign In");
		
		gp2.add(signIn, 0, 0);
		
		gp1.add(usernameL, 0, 0);
		gp1.add(nameTF, 1, 0);
		
		gp1.add(passL, 0, 1);
		gp1.add(passPF, 1, 1);
		
		gp1.add(regL, 0, 2);
		
		gp1.add(gp2, 0, 3);
		
		gp1.setAlignment(Pos.CENTER);
		gp2.setAlignment(Pos.CENTER);
		
		gp1.setVgap(20);
		
		gp1.setPadding(new Insets(10));
		gp2.setPadding(new Insets(0, 0, 100, 0));
		
		mb = new MenuBar();
		m = new Menu("Menu");
		miLogin = new MenuItem("Login");
		miAdd = new MenuItem("Add User");
		miExit = new MenuItem("Exit");
		
		mb.getMenus().add(m);
		
		m.getItems().add(miLogin);
		m.getItems().add(miAdd);
		m.getItems().add(miExit);
		
	}
	
	public void view() {
		bp = new BorderPane();
		regPage = new Register();
		
		bp.setTop(mb);
		bp.setCenter(gp1);
		sc = new Scene(bp, 600, 600);
	}
	
	public void eventHandler() {
		signIn.setOnAction(e ->{
			if(nameTF.getText().isEmpty() || passPF.getText().isEmpty()) {
				alertError();
			}else {
				alertSuccess();
			}
			
		});
		
		regL.setOnMouseEntered(e->{
			regL.setUnderline(true);
			regL.setCursor(Cursor.HAND);
		});
		
		regL.setOnMouseExited(e ->{
			regL.setUnderline(false);
			regL.setCursor(Cursor.DEFAULT);
		});
		
		regL.setOnMouseClicked(e ->{
			bp.setCenter(regPage);
		});
		
		miLogin.setOnAction(e ->{
			bp.setCenter(gp1);
		});
		
		miAdd.setOnAction(e ->{
			bp.setCenter(regPage);
		});
		
		miExit.setOnAction(e ->{
			Alert confirm = new Alert(AlertType.CONFIRMATION);
			Optional<ButtonType> result = confirm.showAndWait();
			if(result.get() == ButtonType.CANCEL) {
				e.consume();
			}else {
				Alert bye = new Alert(AlertType.INFORMATION);
				bye.setHeaderText("Thank You For Using This Application!");
				bye.showAndWait();
				System.exit(0);
			}
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
			alert.setContentText("Welcome, " + nameTF.getText());
			alert.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		menuSet();
		view();
		eventHandler();
		primaryStage.setTitle("JH Shop");
		primaryStage.setScene(sc);
		primaryStage.show();
	}

}
