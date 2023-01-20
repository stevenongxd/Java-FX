package user;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import transaction.TransactionDetail;
import transaction.TransactionHeader;

public class TransactionHistory extends Application {

	VBox mainVB;
	TableView<TransactionHeader> tableTH;
	TableView<TransactionDetail> tableTD;
	Label selectedHeaderTransactionLbl;
	Scene scene;
	
	Integer transactionID;

	public void init() {
		
		mainVB = new VBox(10);
		tableTH = new TableView<>();
		tableTD = new TableView<>();
		tableTD.setSelectionModel(null);
		selectedHeaderTransactionLbl = new Label("Selected Transaction: None");

		mainVB.getChildren().addAll(tableTH, selectedHeaderTransactionLbl, tableTD);
		
		scene = new Scene(mainVB, 1000, 800);
		
	}

	public void setTable() {
		
		TableColumn<TransactionDetail, Integer> transactionID = new TableColumn<TransactionDetail, Integer>("Transaction ID");
		TableColumn<TransactionDetail, Integer> watchID = new TableColumn<TransactionDetail, Integer>("Watch ID");
		TableColumn<TransactionDetail, String> watchName = new TableColumn<TransactionDetail, String>("Watch Name");
		TableColumn<TransactionDetail, String> watchBrand = new TableColumn<TransactionDetail, String>("Watch Brand");
		TableColumn<TransactionDetail, Integer> watchPrice = new TableColumn<TransactionDetail, Integer>("Watch Price");
		TableColumn<TransactionDetail, Integer> watchQuantity = new TableColumn<TransactionDetail, Integer>("Quantity");
		TableColumn<TransactionDetail, Integer> subTotal = new TableColumn<TransactionDetail, Integer>("Sub Total");
		
		transactionID.setCellValueFactory(new PropertyValueFactory<TransactionDetail, Integer>("transactionID"));
		watchID.setCellValueFactory(new PropertyValueFactory<TransactionDetail, Integer>("watchID"));
		watchName.setCellValueFactory(new PropertyValueFactory<TransactionDetail, String>("watchName"));
		watchBrand.setCellValueFactory(new PropertyValueFactory<TransactionDetail, String>("watchBrand"));
		watchPrice.setCellValueFactory(new PropertyValueFactory<TransactionDetail, Integer>("watchPrice"));
		watchQuantity.setCellValueFactory(new PropertyValueFactory<TransactionDetail, Integer>("quantity"));
		subTotal.setCellValueFactory(new PropertyValueFactory<TransactionDetail, Integer>("subTotal"));

		transactionID.setMinWidth(100);
		watchID.setMinWidth(100);
		watchName.setMinWidth(200);
		watchBrand.setMinWidth(200);
		watchPrice.setMinWidth(150);
		watchQuantity.setMinWidth(100);
		subTotal.setMinWidth(150);
		
		tableTD.getColumns().add(transactionID);
		tableTD.getColumns().add(watchID);
		tableTD.getColumns().add(watchName);
		tableTD.getColumns().add(watchBrand);
		tableTD.getColumns().add(watchPrice);
		tableTD.getColumns().add(watchQuantity);
		tableTD.getColumns().add(subTotal);
		
		TableColumn<TransactionHeader, Integer> transactionID2 = new TableColumn<TransactionHeader, Integer>("Transaction ID");
		TableColumn<TransactionHeader, Integer> userID = new TableColumn<TransactionHeader, Integer>("User ID");
		TableColumn<TransactionHeader, String> transactionDate = new TableColumn<TransactionHeader, String>("Transaction Date");
		
		transactionID2.setCellValueFactory(new PropertyValueFactory<TransactionHeader, Integer>("transactionID"));
		userID.setCellValueFactory(new PropertyValueFactory<TransactionHeader, Integer>("userID"));
		transactionDate.setCellValueFactory(new PropertyValueFactory<TransactionHeader, String>("transactionDate"));
		
		transactionID.setMinWidth(333);
		userID.setMinWidth(333);
		transactionDate.setMinWidth(334);
		
		tableTH.getColumns().add(transactionID2);
		tableTH.getColumns().add(userID);
		tableTH.getColumns().add(transactionDate);
//		tableTH.setItems(TransactionHeader.insertTransactionHeader());
		}
	
	public void eventHandler() {
		tableTH.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TransactionHeader>() {

			@Override
			public void changed(ObservableValue<? extends TransactionHeader> observable, TransactionHeader oldValue, TransactionHeader newValue) {
				if (!newValue.equals(null)) {
					TransactionHeader.setSelectedTransactionHeader(newValue);
					TransactionHeader.getSelectedTransactionHeader().getTransactionID();
					selectedHeaderTransactionLbl.setText("Selected Transaction: " + transactionID);
					tableTD.setItems(TransactionDetail.tdData(transactionID));
				}
			}
		});
	}
	
	public static TransactionHistory getData() {		
		TransactionHistory transactionHistory = new TransactionHistory();
		return transactionHistory;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		setTable();
		eventHandler();
		primaryStage.setTitle("View Transaction History");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
