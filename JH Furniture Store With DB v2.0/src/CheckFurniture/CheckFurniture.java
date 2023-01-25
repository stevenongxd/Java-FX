package CheckFurniture;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Furniture;

public class CheckFurniture {
	private static Furniture selectedFurniture;

	public static Furniture getSelectedFurniture() {
		return selectedFurniture;
	}

	public static void setSelectedFurniture(Furniture selectedFurniture) {
		CheckFurniture.selectedFurniture = selectedFurniture;
	}

	public static void removeSelectedFurniture() {
		CheckFurniture.selectedFurniture = null;
	}

	public static boolean isSelectedFurniture() {
		if (selectedFurniture == null) {
		    return false;
		} else {
		    return true;
		}
	}

	private static Database connect = Database.getConnection();

	public static ObservableList<Furniture> getFurnitureData() {
		ObservableList<Furniture> fList = FXCollections.observableArrayList();

		String query = "SELECT * FROM `furniture`";
		
		ResultSet rs = connect.executeQuery(query);
		
		try {
			while(rs.next()) {
				String furnitureID = rs.getString("furID");
				String furnitureName = rs.getString("furName");
				String furnitureType = rs.getString("furType");
				Integer furniturePrice = rs.getInt("furPrice");
				
				Furniture f = new Furniture(furnitureID, furnitureName, furnitureType, furniturePrice);
				fList.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fList;
	}

	public static Furniture getFurniture(String idFur) {
		Database connect = Database.getConnection();
		String query = String.format( "SELECT * FROM `furniture` WHERE furID = '%s'", idFur);
		
		ResultSet rs = connect.executeQuery(query);
		
		try {
			while(rs.next()) {
				String furnitureID = rs.getString("furID");
				String furnitureName = rs.getString("furName");
				String furnitureType = rs.getString("furType");
				Integer furniturePrice = rs.getInt("furPrice");
				
				Furniture f = new Furniture(furnitureID, furnitureName, furnitureType, furniturePrice);
				return f;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
