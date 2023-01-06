package main;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Login extends BorderPane{

	Label logL;
	
	public Login() {
		logL = new Label("Login Page");
		
		setCenter(logL);
	}

}
