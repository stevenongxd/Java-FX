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
	
	Label emailL, passL, titleL;
	TextField emailTF;
	PasswordField passPF;
	Button signInB;
	
	public void initialize() {
		bPane = new BorderPane();
		gPane = new GridPane();
		fPane = new FlowPane();
		
		titleL = new Label("Login");
		emailL = new Label("Email");
		passL = new Label("Password");
		
		emailTF = new TextField();
		emailTF.setPromptText("Email");
		
		passPF = new PasswordField();
		passPF.setPromptText("Password");
		
		signInB = new Button("Sign In");
		
		scene = new Scene(bPane, 500, 200);
	}
	
	public void arrangeComp() {
		fPane.getChildren().add(emailTF);
		fPane.getChildren().add(passPF);
		
		gPane.add(emailL, 0, 0);
		gPane.add(passL, 0, 1);
		gPane.add(emailTF, 1, 0);
		gPane.add(passPF, 1, 1);
		
		
		BorderPane.setAlignment(titleL, Pos.CENTER);
		BorderPane.setAlignment(signInB, Pos.CENTER);
		gPane.setAlignment(Pos.CENTER);
		
		gPane.setHgap(10);
		gPane.setVgap(10);
		
		bPane.setPadding(new Insets(30));
		
		titleL.setFont(new Font("Roboto", 20));
		
		bPane.setTop(titleL);
		bPane.setCenter(gPane);
		bPane.setBottom(signInB);
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		arrangeComp();
		primaryStage.setScene(scene);
		primaryStage.setTitle("JH Store");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
