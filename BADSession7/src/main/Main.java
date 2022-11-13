package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	Scene scene;
	BorderPane bPane;
	GridPane gPane;
	TextArea tArea, tArea2;
	RadioButton maleRB, femaleRB, otherRB;
	ToggleGroup genderTG;
	CheckBox agreementCB;
	Spinner<Double> gpaSpn;
	ComboBox<String> countryCBX;
	ListView<String> itemLV;
	MenuBar menuB;
	Menu fileM, editM;
	MenuItem newMI, openFileMI, copyMI;
	
	public void initialize(){
		bPane = new BorderPane();
		gPane = new GridPane();
		
		tArea = new TextArea("Input Here");
		tArea2 = new TextArea();
		tArea2.setPromptText("Input Here");
		
		maleRB = new RadioButton("Male");
		femaleRB = new RadioButton("Female");
		otherRB = new RadioButton("Other");
		
		genderTG = new ToggleGroup();
		maleRB.setToggleGroup(genderTG);
		femaleRB.setToggleGroup(genderTG);
		otherRB.setToggleGroup(genderTG);
		
		agreementCB = new CheckBox("I agree to the Terms of Service and Conditions");
		
		gpaSpn = new Spinner<>(0.0, 4.0, 0.0, 0.1);
		
		countryCBX = new ComboBox<>();
		countryCBX.getItems().add("Indonesia");
		countryCBX.getItems().add("Singapore");
		countryCBX.getItems().add("Australia");
		countryCBX.getItems().add("Malaysia");
//		countryCBX.setPromptText("Input Country");
		countryCBX.getSelectionModel().select(0); // bisa pake selectfirst / selectlast
		
		itemLV = new ListView<>();
		itemLV.getItems().add("Monitor");
		itemLV.getItems().add("Mouse");
		itemLV.getItems().add("Earphone");
		itemLV.getItems().add("Keyboard");
		itemLV.getSelectionModel().select(0); // bisa pake selectfirst / selectlast
		
		menuB = new MenuBar();
		fileM = new Menu("File");
		editM = new Menu("Edit");
		newMI = new MenuItem("New");
		openFileMI = new MenuItem("Open File");
		copyMI = new MenuItem("Copy");
		
		menuB.getMenus().add(fileM);
		menuB.getMenus().add(editM);
		
		fileM.getItems().add(newMI);
		fileM.getItems().add(openFileMI);
		editM.getItems().add(copyMI);
		
//		gPane.add(tArea, 0, 0);
//		gPane.add(tArea2, 0, 1);
		gPane.add(maleRB, 0, 0);
		gPane.add(femaleRB, 0, 1);
		gPane.add(otherRB, 0, 2);
		gPane.add(agreementCB, 0, 3);
		gPane.add(gpaSpn, 0, 4);
		gPane.add(countryCBX, 0, 5);
		gPane.add(itemLV, 0, 6);
				
		bPane.setTop(menuB);
		bPane.setCenter(gPane);
		scene = new Scene(bPane, 500, 500);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
