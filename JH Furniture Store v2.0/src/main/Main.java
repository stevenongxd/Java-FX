package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	BorderPane bp;
	GridPane gp, gp2;
	Label fID, fName, fType, fPrice;
	TextField id, name, price;
	ComboBox<String> typeCBX;
	TableView<Furniture> fTable;
	Button fadd;
	
	Scene sc;
	
	public void label() {
		fID = new Label("Furniture's ID");
		fName = new Label("Furniture's Name");
		fType = new Label("Furniture's Type");
		fPrice = new Label("Furniture's Price");
	}
	
	public void textfield() {
		id = new TextField();
		name = new TextField();
		price = new TextField();
	}
	
	public void typeCombo() {
		typeCBX = new ComboBox<>();
		typeCBX.getItems().add("Chair");
		typeCBX.getItems().add("Table");
		typeCBX.getItems().add("Bed");
		typeCBX.getItems().add("Wardrobe");
		typeCBX.getSelectionModel().select(null);
	}
	
	public void addButton() {
		fadd = new Button("Add New Furniture");
	}
	
	public void view() {
		gp = new GridPane();
		gp2 = new GridPane();
		
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(20);
		
		gp2.setAlignment(Pos.CENTER);
		
		gp2.add(fadd, 0, 0);
		
		gp.add(fID, 0, 0);
		gp.add(id, 1, 0);
		
		gp.add(fName, 0, 1);
		gp.add(name, 1, 1);
		
		gp.add(fType, 0, 2);
		gp.add(typeCBX, 1, 2);
		
		gp.add(fPrice, 0, 3);
		gp.add(price, 1, 3);
		
		gp.add(gp2, 0, 4);
		
		bp = new BorderPane();
		bp.setCenter(gp);
		bp.setBottom(gp2);
		bp.setPadding(new Insets(20));
		
		sc = new Scene(bp, 800, 650);
		
	}
	
	public void setTable() {
		fTable = new TableView<Furniture>();
		
		TableColumn<Furniture, String> fID = new TableColumn<Furniture, String>("Furniture's ID");
		TableColumn<Furniture, String> fName = new TableColumn<Furniture, String>("Furniture's Name");
		TableColumn<Furniture, String> fType = new TableColumn<Furniture, String>("Furniture's Type");
		TableColumn<Furniture, Integer> furniturePirce = new TableColumn<Furniture, Integer>("Furniture's Price");
		
		fID.setCellValueFactory(new PropertyValueFactory<Furniture, String>("furID"));
		fName.setCellValueFactory(new PropertyValueFactory<Furniture, String>("furName"));
		fType.setCellValueFactory(new PropertyValueFactory<Furniture, String>("furType"));
		furniturePirce.setCellValueFactory(new PropertyValueFactory<Furniture, Integer>("furPrice"));
	
		fID.setMinWidth(180);
		fName.setMinWidth(218);
		fType.setMinWidth(180);
		furniturePirce.setMinWidth(180);
		
		fTable.getColumns().addAll(fID, fName, fType, furniturePirce);
		
		bp.setTop(fTable);
	}
	
	public void eventHandler() {
		
		fadd.setOnAction(e ->{
		if (id.getText().isEmpty() || price.getText().isEmpty() || name.getText().isEmpty()) {
		    alertError();
		    return;
		}else {
		}
		
		if (!id.getText().startsWith("FR")) {
			alertError();
		    return;
		}else {
		}
		
		try {
		    Integer price2 = Integer.parseInt(price.getText());
		    if (price2 < 15000) {
		    	alertError();
			    return;
		    }else {
		    	fTable.getItems().add(new Furniture(id.getText(), name.getText(), typeCBX.getValue(), price2));
		    	alertSuccess();
		    	id.setText("");
		    	name.setText("");
		    	typeCBX.getSelectionModel().select(null);
		    	price.setText("");
			}
		} catch (NumberFormatException ex) {
			alertError();
		    return;
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
			alert.setContentText("Data has been successfully added");
			alert.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		label();
		textfield();
		typeCombo();
		addButton();
		view();
		setTable();
		eventHandler();
		primaryStage.setTitle("JH Furniture Store");
		primaryStage.setScene(sc);
		primaryStage.show();
	}

}
