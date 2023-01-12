package admin;


import javafx.application.Application;
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
import brand.Brand;
import brand.CheckBrand;
import controller.BrandController;

public class ManageBrand extends Application{
	
	BorderPane bPane;
	GridPane gPane, gPane2;
	Label brandNameL;
	Button insertB, updateB, deleteB;
	TextField brandNameTF;
	TableView<Brand> tableBrand;
	Scene scene;
	
	public void init() {
		bPane = new BorderPane();
		
		gPane = new GridPane();
		gPane2 = new GridPane();
		
		brandNameL = new Label("Brand Name: ");
		brandNameL.setFont((Font.font("Arial", FontWeight.BOLD, 14)));
		
		brandNameTF = new TextField();
		brandNameTF.setPromptText("Brand Name");
		
		insertB = new Button("Insert Brand");
		updateB = new Button("Update Brand");
		deleteB = new Button("Delete Brand");
		
		scene = new Scene(bPane, 400, 500);
		
		bPane.setCenter(gPane);
		bPane.setBottom(gPane2);
		
		gPane.setAlignment(Pos.CENTER);
		gPane2.setAlignment(Pos.CENTER);
		
		gPane.add(brandNameL, 0, 0);
		gPane.add(brandNameTF, 1, 0);
		gPane2.add(insertB, 0, 0);
		gPane2.add(updateB, 1, 0);
		gPane2.add(deleteB, 2, 0);
		
		bPane.setPadding(new Insets(10, 10, 10, 10));
		gPane2.setHgap(20);
		gPane.setVgap(20);
	}
	
	public void setTable() {
		tableBrand = new TableView<Brand>();
		
		TableColumn<Brand, Integer> kolom1 = new TableColumn<Brand, Integer>("Brand ID"); 
		TableColumn<Brand, String> kolom2 = new TableColumn<Brand, String>("Brand Name");
		
		kolom1.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("brandID"));
		kolom2.setCellValueFactory(new PropertyValueFactory<Brand, String>("brandName"));
		
		tableBrand.getColumns().addAll(kolom1, kolom2);
		
		bPane.setTop(tableBrand);
		
		insertB.setOnAction(event ->{
			if (CheckBrand.isSelectedBrandInTable() == false) {
				Alert alert = new Alert(AlertType.ERROR, "Select a brand!");
				alert.show();
			}

			String brandName = brandNameTF.getText();

			if (brandName.isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR, "Brand name cannot be empty!");
				alert.show();
			}
			CheckBrand.updateBrand(CheckBrand.getSelectedBrandInTable().getBrandID(), brandName);
			Alert alert = new Alert(AlertType.INFORMATION, "Brand updation success!");
		});
		
		updateB.setOnAction((event2) ->{
			if(brandNameTF.getText().equals("")) {
				Alert alert1 = new Alert(AlertType.ERROR);
				
				alert1.setHeaderText("Error");
				alert1.setContentText("You must select a brand first!");
				alert1.show();
				
			}else {
//				tableBrand.getItems().set(brandID, brandNameTF.getText());
				Alert alert2 = new Alert(AlertType.INFORMATION);
				
				alert2.setHeaderText("Message");
				alert2.setContentText("Watch Successfully Updated!");
			}
		});
		
		deleteB.setOnAction((event3) ->{
			tableBrand.getItems().removeAll(tableBrand.getSelectionModel().getSelectedItems());
			Alert alert3 = new Alert(AlertType.INFORMATION);
			
			alert3.setHeaderText("Message");
			alert3.setContentText("Watch Successfully Deleted!");
			alert3.show();
		});
	}
	
	public static void main(String[] args) {
		launch(args);

		}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		setTable();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}


