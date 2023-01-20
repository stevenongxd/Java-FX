package user;

import java.util.Optional;

import cart.Cart;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import transaction.TransactionHeader;
import watch.Product;

public class BuyWatch extends Application {
	
    VBox mainVB;
    Label selectedWatchLbl, qtyLbl;
    Spinner<Integer> qtySpinner;
    Button addWatchB, clearB, checkoutB;
    Scene scene;
    TableView<Product> tableProduct;
    TableView<Cart> tableCart;
    HBox HB1, HB2;

    public void init() {

    mainVB = new VBox(10);
    
    addWatchB = new Button("Add Watch To Cart");
    clearB = new Button("Clear Cart");
    checkoutB = new Button("Checkout");
    
    selectedWatchLbl = new Label("Selected Watch: None");
    qtyLbl = new Label("Quantity: ");
    
    qtySpinner = new Spinner<>(0, 100, 0, 1);
    
    HB1 = new HBox();
    HB1.getChildren().addAll(qtyLbl, qtySpinner, addWatchB);
    HB2 = new HBox();
    HB2.getChildren().addAll(clearB, checkoutB);
    
    tableProduct = new TableView<Product>();
    tableCart = new TableView<Cart>();

    scene = new Scene(mainVB, 800, 520);
    }

    public void setLayout() {
        mainVB.getChildren().addAll(tableProduct, selectedWatchLbl, HB1, tableCart, HB2);

        HB1.setSpacing(10);
        HB1.setPadding(new Insets(0, 0, 0, 210));

        HB2.setSpacing(15);
        HB2.setPadding(new Insets(0, 0, 7, 300));
    }
	
	public void setTable() {
		TableColumn<Product, Integer> watchID = new TableColumn<Product, Integer>("Watch ID"); 
		TableColumn<Product, String> watchName = new TableColumn<Product, String>("Watch Name"); 
		TableColumn<Product, String> watchBrand = new TableColumn<Product, String>("Watch Brand"); 
		TableColumn<Product, String> watchPrice = new TableColumn<Product, String>("Watch Price");
		TableColumn<Product, Integer> watchStock = new TableColumn<Product, Integer>("Watch Stock");
		
		watchID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("watchID"));
		watchName.setCellValueFactory(new PropertyValueFactory<Product, String>("watchName"));
		watchBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("watchBrand"));
		watchPrice.setCellValueFactory(new PropertyValueFactory<Product, String>("watchPrice"));
		watchStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("watchStock"));
		
		watchID.setMinWidth(92);
		watchName.setMinWidth(200);
		watchBrand.setMinWidth(200);
		watchPrice.setMinWidth(200);
		watchStock.setMinWidth(100);
		
		tableProduct.getColumns().addAll(watchID, watchName, watchBrand, watchPrice, watchStock);
		
		TableColumn<Cart, Integer> userID = new TableColumn<Cart, Integer>("User ID"); 
        TableColumn<Cart, Integer> watchID2 = new TableColumn<Cart, Integer>("Watch ID"); 
        TableColumn<Cart, Integer> qty = new TableColumn<Cart, Integer>("Quantity"); 
        
        userID.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("userID"));
        watchID2.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("watchID"));
        qty.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("qty"));

        userID.setMinWidth(266);
        watchID2.setMinWidth(267);
        qty.setMinWidth(266);

        tableCart.getColumns().addAll(userID, watchID2, qty);
		
	}
	
	public void eventHandler() {
		tableProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {

			@Override
			public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
				if (newValue.equals(null) == false) {
					Product.setSelectedProduct(newValue);
					selectedWatchLbl.setText("Selected Watch: " + Product.getSelectedProduct().getWatchName());
				}
			}
		});

		addWatchB.setOnMouseClicked(e -> {
			int quantity = qtySpinner.getValue();
			
			if (Product.selectedProduct() == false) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("You must select a watch to add first!");
				alert.show();
			}

			if (quantity > Product.getSelectedProduct().getWatchStock() || quantity < 1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Empty Space!");
				alert.show();
				
			}else if (quantity < 1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("Empty Space!");
				alert.show();
			}

			Cart.addProduct(Product.getSelectedProduct().getWatchID(), quantity);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Message");
			alert.setContentText("Watch added to cart!");
			alert.show();
			
			tableCart.setItems(Cart.cartData());
			selectedWatchLbl.setText("Selected Watch: None");
			qtySpinner.getValueFactory().setValue(0);
			Product.removeSelectedProduct();
			tableProduct.getSelectionModel().clearSelection();
		});

		clearB.setOnMouseClicked(e -> {

			if (Cart.cartData().isEmpty() == true) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("You must select a watch to add first!");
				alert.show();
			}

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Confirmation");
			alert.setContentText("Are you sure to clear cart?");
			alert.getButtonTypes().clear();
			alert.getButtonTypes().add(ButtonType.YES);
			alert.getButtonTypes().add(ButtonType.NO);

			Optional<ButtonType> choice = alert.showAndWait();

			if (choice.get() == ButtonType.YES) {
				Cart.deleteCartData();
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setHeaderText("Message");
				alert2.setContentText("Cart cleared Successfully!");
				alert2.show();
				
				tableCart.setItems(Cart.cartData());
				selectedWatchLbl.setText("Selected Watch: None");
				qtySpinner.getValueFactory().setValue(0);
				Product.removeSelectedProduct();
				tableProduct.getSelectionModel().clearSelection();
			}
		});

		checkoutB.setOnMouseClicked(e -> {
			if (Cart.cartData().isEmpty() == true) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error");
				alert.setContentText("You must select a watch to add first!");
				alert.show();
			}

			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure want to checkout?");
			alert.getButtonTypes().clear();
			alert.getButtonTypes().add(ButtonType.YES);
			alert.getButtonTypes().add(ButtonType.NO);

			Optional<ButtonType> choice = alert.showAndWait();

			if (choice.get() == ButtonType.YES) {
				TransactionHeader.insertTransactionHeader();
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setHeaderText("Message");
				alert2.setContentText("Checkout Successful!");
				alert2.show();
				
				tableCart.setItems(Cart.cartData());
				selectedWatchLbl.setText("Selected Watch: None");
				qtySpinner.getValueFactory().setValue(0);
				Product.removeSelectedProduct();
				tableProduct.getSelectionModel().clearSelection();
			}
		});
	}

	public static void main(String[] args) {
		launch(args);

	}
	
	public static BuyWatch getData() {
		BuyWatch buyWatch = new BuyWatch();
		return buyWatch;
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		init();
		setLayout();
		setTable();
		arg0.setTitle("Buy Watch");
		arg0.setScene(scene);
		arg0.show();
		
	}

}