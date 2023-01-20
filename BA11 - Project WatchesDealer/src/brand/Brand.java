package brand;

import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Database;

public class Brand {
	private Integer brandID;
	private String brandName;

	private static Brand selectedBrand;
	
	public Brand(Integer brandID, String brandName) {
		super();
		this.brandID = brandID;
		this.brandName = brandName;
	}

	public Integer getBrandID() {
		return brandID;
	}

	public void setBrandID(Integer brandID) {
		this.brandID = brandID;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	

	public static Brand getSelectedBrand() {
		return selectedBrand;
	}

	public static void setSelectedBrand(Brand selectedBrand) {
		Brand.selectedBrand = selectedBrand;
	}
	
	public static boolean SelectedBrand() {
		if (selectedBrand.equals(null)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void removeSelectedBrand() {
		Brand.selectedBrand = null;
	}

	
	public static void insertBrand(String brandName) {
		String query = String.format("INSERT INTO `brand`(`BrandID`, `BrandName`) VALUES ('0','%s')", brandName);
		connect.executeUpdate(query);
	}
	
	public static void updateBrand(Integer brandID, String brandName) {
		String query = String.format("UPDATE `brand` SET `BrandName`='%s' WHERE BrandID = '%d'", brandName, brandID);
		connect.executeUpdate(query);
	}

	public static void deleteBrand(Integer brandID) {
		String query = String.format("DELETE FROM brand WHERE BrandID = '%d'", brandID);
		connect.executeUpdate(query);
	}
	
	private static Database connect = Database.getConnection();

	public static Brand getBrand(Integer brandID) {
		String query = String.format("SELECT * FROM brand WHERE BrandID = '%d'", brandID);
		ResultSet rs = connect.executeQuery(query);

		try {
			if (rs.next()) {
				String brandName = rs.getString("BrandName");

				Brand brand = new Brand(brandID, brandName);
				return brand;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static ObservableList<Brand> brandList() {
		ObservableList<Brand> brandList = FXCollections.observableArrayList();

		String query = "SELECT * FROM brand";
		ResultSet rs = connect.executeQuery(query);

		try {
			while (rs.next()) {
				Integer brandID = rs.getInt("BrandID");
				String brandName = rs.getString("BrandName");

				Brand brand = new Brand(brandID, brandName);
				brandList.add(brand);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return brandList;
	}

}