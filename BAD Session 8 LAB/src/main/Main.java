package main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application{
	
	Label nameLbl, ageLbl;
	TextField nameTF;
	Spinner<Integer> ageSpin;
	Button submitBtn, deleteBtn, fileBtn;
	GridPane gp;
	BorderPane bp;
	Scene scene;
	ScrollPane sp;
	FileChooser fc;
	
	TableView<User> tableUser;
	
	public void init() {
		nameLbl = new Label("Name");
		ageLbl = new Label("Age");
		nameTF = new TextField();
		ageSpin = new Spinner<>(1, 100, 1, 1);
		submitBtn = new Button("Submit");
		deleteBtn = new Button("Delete");
		fileBtn = new Button("Open File");
		gp = new GridPane();
		bp = new BorderPane();
		sp = new ScrollPane();
		fc = new FileChooser();
		
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("image", "*.png", "*.jpg");
		fc.getExtensionFilters().add(filter);
		
		gp.add(nameLbl, 0, 0);
		gp.add(nameTF, 1, 0);
		gp.add(ageLbl, 0, 1);
		gp.add(ageSpin, 1, 1);
		gp.add(submitBtn, 0, 2);
		gp.add(deleteBtn, 1, 2);
		
		bp.setTop(gp);
		bp.setBottom(fileBtn);
		sp.setContent(bp);
		
		scene = new Scene(sp);
	}
	
	public void setTable() {
		tableUser = new TableView<User>();
		TableColumn<User, String> col1 = new TableColumn<User, String>();
		TableColumn<User, Integer> col2 = new TableColumn<User, Integer>();
		
		col1.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		col2.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));
		
		tableUser.getColumns().addAll(col1, col2);
		tableUser.getItems().add(new User("Test", 69));
		
		bp.setCenter(tableUser);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		setTable();
		// Arrow Function () -> {} -- Shortcut buat bikin event / Lambda Expression
		submitBtn.setOnAction((event) -> {
			if(nameTF.getText().equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				
				//Set Header = yang diatas
				alert.setHeaderText("Error Name");
				
				//Set Content = yang dibawah
				alert.setContentText("Invalid Name");
				alert.show();
			}else {
				tableUser.getItems().add(new User(nameTF.getText(), ageSpin.getValue()));
			}
			
		});
		
		deleteBtn.setOnAction((event2) -> {
			tableUser.getItems().removeAll(tableUser.getSelectionModel().getSelectedItems());
		});
		
		fileBtn.setOnAction((event3) -> {
			File file = fc.showOpenDialog(primaryStage);
			System.out.println(file.getName());
			Desktop desktop = Desktop.getDesktop();
			
			try {
				desktop.open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.setResizable(true);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
