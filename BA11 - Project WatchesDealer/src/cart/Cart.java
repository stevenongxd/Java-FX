package cart;

import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Database;
import user.User;

public class Cart {
	private Integer userID, watchID, quantity;

	public Cart(Integer userID, Integer watchID, Integer quantity) {
		super();
		this.userID = userID;
		this.watchID = watchID;
		this.quantity = quantity;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
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
	
	public static void addProduct(Integer watchID, Integer quantity) {
		String query = String.format("INSERT INTO `cart`(`UserID`, `WatchID`, `Quantity`) VALUES ('%d','%d','%d')", User.getloggedInUser().getUserID(), watchID, quantity);
		connect.executeUpdate(query);
	}

	public static void deleteCartData() {
		String query = String.format("DELETE FROM `cart` WHERE UserID = '%d'", User.getloggedInUser().getUserID());
		connect.executeUpdate(query);
	}
	
	private static Database connect = Database.getConnection();
	
	public static ObservableList<Cart> cartData() {
		ObservableList<Cart> cartList = FXCollections.observableArrayList();
		
		String query = String.format("SELECT * FROM cart WHERE UserID = '%d'", User.getloggedInUser().getUserID());
		ResultSet rs = connect.executeQuery(query);

		try {
			while (rs.next()) {
				Integer userID = rs.getInt("UserID");
				Integer watchID = rs.getInt("WatchID");
				Integer quantity = rs.getInt("Quantity");

				Cart cart = new Cart(userID, watchID, quantity);
				cartList.add(cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

}
