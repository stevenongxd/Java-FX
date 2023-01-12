package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	private Stage primaryStage;

	public Stage getMainStage() {
		return primaryStage;
	}

	public void setMainStage(Stage mainStage) {
		this.primaryStage = mainStage;
	}
	
	public void setTitle(String title) {
		this.primaryStage.setTitle(title);
	}
	
	public void setScene(Scene scene) {
		this.primaryStage.setScene(scene);
	}
	
	public void show() {
		this.primaryStage.setResizable(false);
		this.primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setResizable(false);
		primaryStage.setScene(null);
		primaryStage.show();
	}

	public static void sceneChanger(Scene scene, String title) {
		mainStage.setTitle(title);
		mainStage.setScene(scene);
	}

}
