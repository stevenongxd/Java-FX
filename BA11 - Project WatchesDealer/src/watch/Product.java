package watch;

import java.sql.ResultSet;

import brand.Brand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Database;

public class Product {
	private Integer watchID, brandID, watchPrice, watchStock;
	private String watchName;
	
	private static Product selectedProduct;

	public Product(Integer watchID, Integer brandID, String watchName, Integer watchPrice, Integer watchStock) {
		super();
		this.watchID = watchID;
		this.brandID = brandID;
		this.watchName = watchName;
		this.watchPrice = watchPrice;
		this.watchStock = watchStock;
	}

	public Integer getWatchID() {
		return watchID;
	}

	public void setWatchID(Integer watchID) {
		this.watchID = watchID;
	}

	public Integer getBrandID() {
		return brandID;
	}

	public void setBrandID(Integer brandID) {
		this.brandID = brandID;
	}

	public String getWatchName() {
		return watchName;
	}

	public void setWatchName(String watchName) {
		this.watchName = watchName;
	}

	public Integer getWatchPrice() {
		return watchPrice;
	}

	public void setWatchPrice(Integer watchPrice) {
		this.watchPrice = watchPrice;
	}

	public Integer getWatchStock() {
		return watchStock;
	}

	public void setWatchStock(Integer watchStock) {
		this.watchStock = watchStock;
	}
	
	public Brand getBrand() {
		return Brand.getBrand(brandID);
	}

	public String getBrandName() {
		return Brand.getBrand(brandID).getBrandName();
	}
	
	public static Product getSelectedProduct() {
		return selectedProduct;
	}

	public static void setSelectedProduct(Product newValue) {
		Product.selectedProduct = newValue;
	}

	public static boolean selectedProduct() {
		if (selectedProduct == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void removeSelectedProduct() {
		Product.selectedProduct = null;
	}
	
	public static void insertProduct(int brandID, String watchName, int watchPrice, int watchStock) {
		String query = String.format("INSERT INTO `watch`(`WatchID`, `BrandID`, `WatchName`, `WatchPrice`, `WatchStock`) " + "VALUES ('0','%d','%s','%d','%d')", brandID, watchName, watchPrice, watchStock);
		connect.executeUpdate(query);
	}
	
	public static void updateProduct(int watchID, int brandID, String watchName, int watchPrice, int watchStock) {
		Database connect = Database.getConnection();
		String query = String.format("UPDATE `watch` SET `BrandID`='%d',`WatchName`='%s',`WatchPrice`='%d',`WatchStock`='%d' WHERE WatchID = '%d'", brandID, watchName, watchPrice, watchStock, watchID);
		connect.executeUpdate(query);
	}

	public static void deleteProduct(int watchID) {
		Database connect = Database.getConnection();
		String query = String.format("DELETE FROM watch WHERE WatchID = '%d'", watchID);
		connect.executeUpdate(query);
	}

	private static Database connect = Database.getConnection();

	public static ObservableList<Product> getAllWatch() {
		
		ObservableList<Product> listWatch = FXCollections.observableArrayList();
		String query = "SELECT * FROM watch";
		ResultSet rs = connect.executeQuery(query);

		try {
			while (rs.next()) {
				int watchID = rs.getInt("WatchID");
				int brandID = rs.getInt("BrandID");
				String watchName = rs.getString("WatchName");
				int watchPrice = rs.getInt("WatchPrice");
				int watchStock = rs.getInt("WatchStock");

				Product book = new Product(watchID, brandID, watchName, watchPrice, watchStock);
				listWatch.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listWatch;
	}

	public static Product selectWatch(int productID) {
		Database connect = Database.getConnection();
		String query = String.format("SELECT * FROM watch WHERE WatchID = '%d'", productID);
		ResultSet rs = connect.executeQuery(query);

		try {
			if (rs.next()) {
				int watchID = rs.getInt("WatchID");
				int brandID = rs.getInt("BrandID");
				String watchName = rs.getString("WatchName");
				int watchPrice = rs.getInt("WatchPrice");
				int watchStock = rs.getInt("WatchStock");

				Product product = new Product(watchID, brandID, watchName, watchPrice, watchStock);
				return product;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}