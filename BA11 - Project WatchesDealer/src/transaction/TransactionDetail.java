package transaction;

import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Database;
import watch.Product;

public class TransactionDetail {
	private Integer transactionID, watchID, quantity;
	
	public TransactionDetail(Integer transactionID, Integer watchID, Integer quantity) {
		super();
		this.transactionID = transactionID;
		this.watchID = watchID;
		this.quantity = quantity;
	}

	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public Integer getWatchID() {
		return watchID;
	}

	public void setWatchID(Integer watchID) {
		this.watchID = watchID;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getWatchName() {
		return Product.selectWatch(watchID).getWatchName();
	}

	public String getWatchBrand() {
		return Product.selectWatch(watchID).getBrandName();
	}
	
	public Integer getWatchPrice() {
		return Product.selectWatch(watchID).getWatchPrice();
	}
	
	public Integer getTotal() {
		return Product.selectWatch(watchID).getWatchPrice() * quantity;
	}
	
		private static Database connect = Database.getConnection();
		
		public void insertDetailTransaction(Integer transactionID, Integer watchID, Integer quantity) {
			String query = String.format("INSERT INTO `detailtransaction`(`TransactionID`, `WatchID`, `Quantity`) VALUES ('%d','%d','%d')", transactionID, watchID, quantity);
			connect.executeUpdate(query);
		}

		public static ObservableList<TransactionDetail> tdData (Integer transactionID) {
			
			ObservableList<TransactionDetail> tdList = FXCollections.observableArrayList();
			String query = String.format("SELECT * FROM detailtransaction WHERE TransactionID = '%d'", transactionID);
			ResultSet rs = connect.executeQuery(query);

			try {
				while (rs.next()) {
					Integer watchID = rs.getInt("WatchID");
					Integer quantity = rs.getInt("Quantity");

					TransactionDetail transactionDetail = new TransactionDetail(transactionID, watchID, quantity);
					tdList.add(transactionDetail);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return tdList;
		}

}

