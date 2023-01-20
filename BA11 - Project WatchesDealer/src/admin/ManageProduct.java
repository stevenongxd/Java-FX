package admin;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import admin.ManageProduct;
import brand.Brand;
import watch.Product;

public class ManageProduct extends Application{

	BorderPane bp;
	GridPane gp, gp2, gp3;
	
	TableView<Product> tableProduct;
	Label watchNameL, watchStockL, watchPriceL, watchBrandL;
	TextField watchNameTF, watchPriceTF;
	Spinner<Integer> watchStockS;
	ComboBox<Brand> watchBrandCB;
	Button insertB, updateB, deleteB;
	
	Scene scene;

	public void init() {
		bp = new BorderPane();
		
		scene = new Scene(bp, 1000, 600);

		gp = new GridPane();
		gp2 = new GridPane();
		gp3 = new GridPane();

		tableProduct = new TableView<>();

		watchNameL = new Label("Watch Name:");
		watchStockL = new Label("Watch Stock:");
		watchPriceL = new Label("Watch Price:");
		watchBrandL = new Label("Watch Brand:");

		watchNameTF = new TextField();
		watchNameTF.setPromptText("Name");

		watchPriceTF = new TextField();
		watchPriceTF.setPromptText("Price");

		watchStockS = new Spinner<>(0, 100, 0, 1);

		watchBrandCB = new ComboBox<>();
		watchBrandCB.setItems(Brand.brandList());
		watchBrandCB.setPromptText("Choose One");

		insertB = new Button("Insert Watch");
		updateB = new Button("Update Watch");
		deleteB = new Button("Delete Watch");
		
		gp.setAlignment(Pos.CENTER);
		gp2.setAlignment(Pos.CENTER);
		gp3.setAlignment(Pos.CENTER);
		
		gp.add(tableProduct, 0, 1);
		gp.add(gp2, 0, 2);
		gp.add(gp3, 0, 3);

		gp2.add(watchNameL, 0, 0);
		gp2.add(watchNameTF, 1, 0);
		
		gp2.add(watchPriceL, 2, 0);
		gp2.add(watchPriceTF, 3, 0);
		
		gp2.add(watchStockL, 0, 1);
		gp2.add(watchStockS, 1, 1);
		
		gp2.add(watchBrandL, 2, 1);
		gp2.add(watchBrandCB, 3, 1);

		gp3.add(insertB, 0, 0);
		gp3.add(updateB, 1, 0);
		gp3.add(deleteB, 2, 0);


		gp.setVgap(20);
		gp2.setVgap(20);
		gp2.setHgap(20);
		gp3.setHgap(20);
		
		bp.setPadding(new Insets(15));

	}

	public void setTable() {
		TableColumn<Product, Integer> watchID = new TableColumn<Product, Integer>("Watch ID");
		TableColumn<Product, String> watchName = new TableColumn<Product, String>("Watch Name");
		TableColumn<Product, String> watchBrand = new TableColumn<Product, String>("Watch Brand");
		TableColumn<Product, Integer> watchPrice = new TableColumn<Product, Integer>("Watch Price");
		TableColumn<Product, Integer> watchStock = new TableColumn<Product, Integer>("Watch Stock");
		
		watchID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("watchID"));
		watchName.setCellValueFactory(new PropertyValueFactory<Product, String>("watchName"));
		watchBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("brandName"));
		watchPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("watchPrice"));
		watchStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("watchStock"));
		
		watchID.setMinWidth(100);
		watchName.setMinWidth(400);
		watchBrand.setMinWidth(200);
		watchPrice.setMinWidth(200);
		watchStock.setMinWidth(100);

		tableProduct.getColumns().add(watchID);
		tableProduct.getColumns().add(watchName);
		tableProduct.getColumns().add(watchBrand);
		tableProduct.getColumns().add(watchPrice);
		tableProduct.getColumns().add(watchStock);
		
		tableProduct.getItems().clear();
		tableProduct.getItems().addAll(Product.getAllWatch());
	}

	public void eventHandler() {
		tableProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {

			@Override
			public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
				if (newValue.equals(null) == false) {
					Product.setSelectedProduct(newValue);
					watchNameTF.setText(Product.getSelectedProduct().getWatchName());
					watchPriceTF.setText(Integer.toString(Product.getSelectedProduct().getWatchPrice()));
					watchStockS.getValueFactory().setValue(Product.getSelectedProduct().getWatchStock());
					watchBrandCB.getSelectionModel().select(Product.getSelectedProduct().getBrand());
				}
			}
		});

		int watchPrice = 0;
		int watchStock = watchStockS.getValue();
		int watchBrand;
		
		if (watchBrandCB.getSelectionModel().getSelectedItem() != null) {
			watchBrand = watchBrandCB.getSelectionModel().getSelectedItem().getBrandID();
		} else {
			watchBrand = -1;
		}
		insertB.setOnMouseClicked(e -> {

			if (!watchNameTF.getText().endsWith(" Watch")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Invalid Format");
				alert.show();
				
			}else if (watchPriceTF.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Invalid Format");
				alert.show();
				
			}else if (watchPrice < 1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Invalid Format");
				alert.show();
				
			}else if (watchStock < 1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Invalid Format");
				alert.show();
				
			}else if (watchBrand == -1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Invalid Format");
				alert.show();
			}

			Product.insertProduct(watchBrand, watchNameTF.getText(), watchPrice, watchStock);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Messaage");
			alert.setContentText("New Watch Successfully Inserted!");
			alert.show();
			
			watchNameTF.setText("");
			watchPriceTF.setText("");
			watchBrandCB.getSelectionModel().select(null);
			watchStockS.getValueFactory().setValue(0);
			Product.removeSelectedProduct();
			tableProduct.getSelectionModel().clearSelection();
			tableProduct.getItems().clear();
			tableProduct.getItems().addAll(Product.getAllWatch());
		});

		updateB.setOnMouseClicked(e -> {
			if (Product.selectedProduct() == false) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("You must select a watch from the table first!");
				alert.show();
			}

			if (!watchNameTF.getText().endsWith(" Watch")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Invalid Format");
				alert.show();
				
			}else if (watchPriceTF.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Invalid Format");
				alert.show();
				
			}else if (watchPrice < 1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Invalid Format");
				alert.show();
				
			}else if (watchStock < 1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Invalid Format");
				alert.show();
				
			}else if (watchBrand == -1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Invalid Format");
				alert.show();
			}
			
			Product.updateProduct(Product.getSelectedProduct().getWatchID(), watchBrand, watchNameTF.getText(), watchPrice, watchStock);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Messaage");
			alert.setContentText("Watch Successfully Updated!");
			alert.show();
			
			watchNameTF.setText("");
			watchPriceTF.setText("");
			watchBrandCB.getSelectionModel().select(null);
			watchStockS.getValueFactory().setValue(0);
			Product.removeSelectedProduct();
			tableProduct.getSelectionModel().clearSelection();
			tableProduct.getItems().clear();
			tableProduct.getItems().addAll(Product.getAllWatch());
		});

		deleteB.setOnMouseClicked(e -> {
			Product.deleteProduct(Product.getSelectedProduct().getWatchID());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Message");
			alert.setContentText("Watch Successfully Deleted!");
			alert.show();
			
			watchNameTF.setText("");
			watchPriceTF.setText("");
			watchBrandCB.getSelectionModel().select(null);
			watchStockS.getValueFactory().setValue(0);
			Product.removeSelectedProduct();
			tableProduct.getSelectionModel().clearSelection();
			tableProduct.getItems().clear();
			tableProduct.getItems().addAll(Product.getAllWatch());
		});
	}

	public static ManageProduct getData() {
		ManageProduct manageProduct = new ManageProduct();
		return manageProduct;
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
		primaryStage.setTitle("Manage Product");
		primaryStage.show();
		
	}

}
