package main;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Register extends BorderPane{

	Label regL;
	
	public Register() {
		regL = new Label("Register Page");
		
		setCenter(regL);
	}

}
