package admin;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import admin.ManageBrand;
import brand.Brand;

	public class ManageBrand extends Application {

	BorderPane bPane;
	GridPane gPane, gPane2;
	Label brandNameLbl;
	Button insertBrand, updateBrand, deleteBrand;
	TextField brandNameTF;
	TableView<Brand> tableBrand;
	Scene scene;

	public void init() {
		bPane = new BorderPane();
		
		gPane = new GridPane();
		gPane2 = new GridPane();
		
		brandNameLbl = new Label("Brand Name: ");
		brandNameLbl.setFont((Font.font("Arial", FontWeight.BOLD, 14)));
		
		brandNameTF = new TextField();
		brandNameTF.setPromptText("Brand Name");
		
		insertBrand = new Button("Insert Brand");
		updateBrand = new Button("Update Brand");
		deleteBrand = new Button("Delete Brand");
		
		scene = new Scene(bPane, 600, 400);
		
		bPane.setCenter(gPane);
		bPane.setBottom(gPane2);
		
		gPane.setAlignment(Pos.CENTER);
		gPane2.setAlignment(Pos.CENTER);
		
		gPane.add(brandNameLbl, 0, 0);
		gPane.add(brandNameTF, 1, 0);
		gPane2.add(insertBrand, 0, 0);
		gPane2.add(updateBrand, 1, 0);
		gPane2.add(deleteBrand, 2, 0);
		
		bPane.setPadding(new Insets(10, 10, 10, 10));
		gPane2.setHgap(20);
		gPane.setVgap(20);

	}

	public void setTable() {
		TableColumn<Brand, Integer> brandID = new TableColumn<Brand, Integer>("Brand ID");
		TableColumn<Brand, String> brandName = new TableColumn<Brand, String>("Brand Name");

		brandID.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("brandID"));
		brandName.setCellValueFactory(new PropertyValueFactory<Brand, String>("brandName"));
		
		brandID.setMinWidth(300);
		brandName.setMinWidth(300);

		tableBrand.getColumns().add(brandID);
		tableBrand.getColumns().add(brandName);
		tableBrand.getItems().clear();
		tableBrand.getItems().addAll(Brand.brandList());
	}

	public void eventHandler() {
		tableBrand.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Brand>() {

			@Override
			public void changed(ObservableValue<? extends Brand> observable, Brand oldValue, Brand newValue) {
				if (newValue.equals(null) == false) {
					Brand.setSelectedBrand(newValue);
					brandNameTF.setText(newValue.getBrandName());
				}
			}
		});

		insertBrand.setOnMouseClicked(e -> {
			Brand.insertBrand(brandNameTF.getText());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Message");
			alert.setContentText("Brand Successfully Inserted!");
			alert.show();
			
			brandNameTF.setText("");
			Brand.removeSelectedBrand();
			tableBrand.getSelectionModel().clearSelection();
			tableBrand.getItems().clear();
			tableBrand.getItems().addAll(Brand.brandList());
		});

		updateBrand.setOnMouseClicked(e -> {
			if (Brand.SelectedBrand() == false) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("You must select a brand from the table first!");
				alert.show();
			}

			Brand.updateBrand(Brand.getSelectedBrand().getBrandID(), brandNameTF.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Message");
			alert.setContentText("Brand Successfully Updated!");
			alert.show();
			
			brandNameTF.setText("");
			Brand.removeSelectedBrand();
			tableBrand.getSelectionModel().clearSelection();
			tableBrand.getItems().clear();
			tableBrand.getItems().addAll(Brand.brandList());
		});

		deleteBrand.setOnMouseClicked(e -> {
			Brand.updateBrand(Brand.getSelectedBrand().getBrandID(), brandNameTF.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Message");
			alert.setContentText("Brand Successfully Deleted!");
			alert.show();
			
			brandNameTF.setText("");
			Brand.removeSelectedBrand();
			tableBrand.getSelectionModel().clearSelection();
			tableBrand.getItems().clear();
			tableBrand.getItems().addAll(Brand.brandList());
		});
	}
	
	public static ManageBrand getData() {
		ManageBrand manageBrand = new ManageBrand();
		return manageBrand;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		setTable();
		eventHandler();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Manage Brand");
		primaryStage.show();
		
	}

}
