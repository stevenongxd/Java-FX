package transaction;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Database;
import user.User;

public class TransactionHeader {
	private Integer transactionID;
	private Integer userID;
	private String transactionDate;
	
	private static TransactionHeader selectedTransactionHeader;

	public TransactionHeader(Integer transactionID, Integer userID, String transactionDate) {
		super();
		this.transactionID = transactionID;
		this.userID = userID;
		this.transactionDate = transactionDate;
	}

	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public static TransactionHeader getSelectedTransactionHeader() {
		return selectedTransactionHeader;
	}

	public static void setSelectedTransactionHeader(TransactionHeader selectedTransactionHeader) {
		TransactionHeader.selectedTransactionHeader = selectedTransactionHeader;
	}

	public static boolean SelectedProduct() {
		if (selectedTransactionHeader.equals(null) == true) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void removeSelectedProduct() {
		TransactionHeader.selectedTransactionHeader = null;
	}

	
	public static String getDate() {
		LocalDate ld = LocalDate.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		String date = ld.format(dateFormat);
		return date;
	}

	private static Database connect = Database.getConnection();

	public static void insertTransactionHeader() {
		String query = String.format(
				"INSERT INTO `headertransaction`(`TransactionID`, `UserID`, `TransactionDate`) VALUES ('%d','%d','%s')", 0, User.getloggedInUser().getUserID(), getDate());
		connect.executeUpdate(query);
	}

	public static ObservableList<TransactionHeader> thData() {
		
		ObservableList<TransactionHeader> thList = FXCollections.observableArrayList();
		String query = String.format("SELECT * FROM headertransaction WHERE UserID = '%d'", User.getloggedInUser().getUserID());
		ResultSet rs = connect.executeQuery(query);

		try {
			while (rs.next()) {
				Integer transactionID = rs.getInt("TransactionID");
				Integer userID = rs.getInt("UserID");
				String transactionDate = rs.getString("TransactionDate");
				TransactionHeader transactionHeader = new TransactionHeader(transactionID, userID, transactionDate);
				thList.add(transactionHeader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return thList;
	}

}
