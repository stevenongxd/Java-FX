package cart;

import java.sql.ResultSet;

import main.Database;
import user.CheckUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CheckCart {
	
	private static Database connect = Database.getConnection();
	
	public static ObservableList<Cart> getAllCart() {
		ObservableList<Cart> listCart = FXCollections.observableArrayList();
		
		String query = String.format("SELECT * FROM cart WHERE UserID = '%d'", CheckUser.getCurrentUser().getUserID());
		ResultSet rs = connect.executeQuery(query);

		try {
			while (rs.next()) {
				Integer userID = rs.getInt("UserID");
				Integer watchID = rs.getInt("WatchID");
				Integer quantity = rs.getInt("Quantity");

				Cart c = new Cart(userID, watchID, quantity);
				listCart.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCart;
	}

	public static void addCart(Integer watchID, Integer quantity) {
		String query = String.format("INSERT INTO `cart`(`UserID`, `WatchID`, `Quantity`) VALUES ('%d','%d','%d')",
				CheckUser.getCurrentUser().getUserID(), watchID, quantity);
		connect.executeUpdate(query);
	}

	public static void deleteCart() {
		String query = String.format("DELETE FROM `cart` WHERE UserID = '%d'", CheckUser.getCurrentUser().getUserID());
		connect.executeUpdate(query);
	}
}
