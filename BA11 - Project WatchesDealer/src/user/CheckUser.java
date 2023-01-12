package user;

import java.sql.ResultSet;

import main.Database;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import registration.Login;
import page.MainPageAdmin;
import user.MainFormUser;

public class CheckUser {

	private static User currentUser;

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		CheckUser.currentUser = currentUser;
	}

	private static Database connect = Database.getConnection();

	public static void login(String emailInput, String passwordInput) {
		String query = String.format("SELECT * FROM user WHERE UserEmail = '%s' AND UserPassword = '%s'", emailInput,
				passwordInput);
		ResultSet rs = connect.executeQuery(query);
		try {
			if (rs.next()) {
				Integer userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userEmail = rs.getString("UserEmail");
				String userPassword = rs.getString("UserPassword");
				String userGender = rs.getString("UserGender");
				String userRole = rs.getString("UserRole");

				User u = new User(userID, userName, userEmail, userPassword, userGender, userRole);
				setCurrentUser(u);
				if (u.getUserRole().equals("Customer")) {
					MainFormUser mpu = MainFormUser.getInstance();
					mpu.show();
				} else {
					MainPageAdmin mpa = MainPageAdmin.getInstance();
					mpa.show();
				}
				Alert alert = new Alert(AlertType.INFORMATION, "Login success!");
				alert.showAndWait();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(AlertType.ERROR, "Credential invalid!");
		alert.showAndWait();
	}

	public static void registerUser(String name, String email, String password, String gender) {
		Database connect = Database.getConnection();
		String query = String.format("INSERT INTO user (`UserID`, `UserName`, `UserEmail`, `UserPassword`, `UserGender`, `UserRole`) VALUES ('0','%s','%s','%s','%s','Customer')", name, email, password, gender);
		connect.executeUpdate(query);
		Alert alert = new Alert(AlertType.INFORMATION, "Register success!");
		alert.showAndWait();
		Login l = Login.getInstance();
		l.show();
	}

	public static void logout() {
		Alert alert = new Alert(AlertType.INFORMATION, "Logout success!");
		alert.showAndWait();
		CheckUser.setCurrUser(null);
		Login l = Login.getInstance();
		l.show();
	}
}
