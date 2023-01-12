package user;

import java.util.Optional;

import cart.Cart;
import cart.CheckCart;
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
import transaction.CheckTransactionHeader;
import watch.CheckWatch;
import watch.Product;

public class BuyWatch extends Application {
    VBox mainVB;
    Label selectedWatchLbl, qtyLbl;
    Spinner<Integer> qtySpinner;
    Button addWatchBtn, clearCartBtn, checkoutBtn;
    Scene scene;
    TableView<Product> tableProduct;
    TableView<Cart> tableCart;
    HBox HB1, HB2;

    public void init() {

    mainVB = new VBox(10);
    
    addWatchBtn = new Button("Add Watch To Cart");
    clearCartBtn = new Button("Clear Cart");
    checkoutBtn = new Button("Checkout");
    
    selectedWatchLbl = new Label("Selected Watch: None");
    qtyLbl = new Label("Quantity: ");
    
    qtySpinner = new Spinner<>(0, 100, 0, 1);
    
    HB1 = new HBox();
    HB1.getChildren().addAll(qtyLbl, qtySpinner, addWatchBtn);
    HB2 = new HBox();
    HB2.getChildren().addAll(clearCartBtn, checkoutBtn);
    
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
		TableColumn<Product, Integer> kolom1 = new TableColumn<Product, Integer>("Watch ID"); 
		TableColumn<Product, String> kolom2 = new TableColumn<Product, String>("Watch Name"); 
		TableColumn<Product, String> kolom3 = new TableColumn<Product, String>("Watch Brand"); 
		TableColumn<Product, String> kolom4 = new TableColumn<Product, String>("Watch Price");
		TableColumn<Product, Integer> kolom5 = new TableColumn<Product, Integer>("Watch Stock");
		
		kolom1.setCellValueFactory(new PropertyValueFactory<Product, Integer>("watchID"));
		kolom2.setCellValueFactory(new PropertyValueFactory<Product, String>("watchName"));
		kolom3.setCellValueFactory(new PropertyValueFactory<Product, String>("watchBrand"));
		kolom4.setCellValueFactory(new PropertyValueFactory<Product, String>("watchPrice"));
		kolom5.setCellValueFactory(new PropertyValueFactory<Product, Integer>("watchStock"));
		
		kolom1.setMinWidth(92);
		kolom2.setMinWidth(200);
		kolom3.setMinWidth(200);
		kolom4.setMinWidth(200);
		kolom4.setMinWidth(100);
		
		tableProduct.getColumns().addAll(kolom1, kolom2, kolom3, kolom4, kolom5);
		
		TableColumn<Cart, Integer> userIdColumn = new TableColumn<>("User ID"); 
        TableColumn<Cart, Integer> watchIDColumn = new TableColumn<Cart, Integer>("Watch ID"); 
        TableColumn<Cart, Integer> qtyColumn = new TableColumn<Cart, Integer>("Quantity"); 

        watchIDColumn.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("watchID"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("qty"));

        userIdColumn.setMinWidth(266);
        watchIDColumn.setMinWidth(267);
        qtyColumn.setMinWidth(266);

        tableCart.getColumns().addAll(userIdColumn, watchIDColumn, qtyColumn);
		
	}
	
	public void eventHandler() {
		tableProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {

			@Override
			public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
				if (newValue != null) {
					CheckWatch.setSelectedWatchInTable(newValue);
					selectedWatchLbl.setText("Selected Watch: " + CheckWatch.getSelectedWatchInTable().getWatchName());
				}
			}
		});

		addWatchBtn.setOnMouseClicked(e -> {
			int buyingQuantity = qtySpinner.getValue();
			if (!CheckWatch.isSelectedWatchInTable()) {
				Alert alert = new Alert(AlertType.ERROR, "You must select a watch!");
				alert.show();
				return;
			}

			if (buyingQuantity > CheckWatch.getSelectedWatchInTable().getWatchStock() || buyingQuantity < 1) {
				Alert alert = new Alert(AlertType.ERROR, "Quantity must be bigger than the stock!");
				alert.show();
				return;
			}

			if (buyingQuantity < 1) {
				Alert alert = new Alert(AlertType.ERROR, "Quantity must be higher than 0");
				alert.show();
				return;
			}

			CheckCart.addCart(CheckWatch.getSelectedWatchInTable().getWatchID(), buyingQuantity);
			Alert alert = new Alert(AlertType.INFORMATION, "Watch added to cart!");
			alert.show();
			refreshCartData();
			resetLayout();
		});

		clearCartBtn.setOnMouseClicked(e -> {

			if (CheckCart.getAllCart().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR, "Cart cannot be empty!");
				alert.show();
				return;
			}

			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to clear cart?");
			alert.getButtonTypes().clear();
			alert.getButtonTypes().add(ButtonType.YES);
			alert.getButtonTypes().add(ButtonType.NO);

			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.YES) {
				CheckCart.deleteCart();
				Alert alert2 = new Alert(AlertType.INFORMATION, "Cart cleared!");
				alert2.show();
				refreshCartData();
				resetLayout();
				return;
			}
		});

		checkoutBtn.setOnMouseClicked(e -> {
			if (CheckCart.getAllCart().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR, "Cart cannot be empty!");
				alert.show();
				return;
			}

			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure want to checkout?");
			alert.getButtonTypes().clear();
			alert.getButtonTypes().add(ButtonType.YES);
			alert.getButtonTypes().add(ButtonType.NO);

			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.YES) {
				CheckTransactionHeader.addHeaderTransaction();
				Alert alert2 = new Alert(AlertType.INFORMATION, "Checkout success!");
				alert2.show();
				refreshCartData();
				resetLayout();
				return;
			}
		});
	}

	public void refreshWatchData() {
		tableProduct.getItems().clear();
		tableProduct.getItems().addAll(CheckWatch.getAllWatch());
	}

	public void refreshCartData() {
		tableCart.setItems(CheckCart.getAllCart());
	}

	public void resetLayout() {
		selectedWatchLbl.setText("Selected Watch: None");
		qtySpinner.getValueFactory().setValue(0);
		CheckWatch.clearSelectedWatchInTable();
		tableProduct.getSelectionModel().clearSelection();
	}

	public static void main(String[] args) {
	launch(args);

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