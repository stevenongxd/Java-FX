package user;

import java.sql.ResultSet;

import admin.MainFormAdmin;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.Database;
import main.Login;

public class User {
	private Integer userID;
	private String userName, userEmail, userPassword, userGender, userRole;
	
	private static User loggedInUser;
	
	public User(Integer userID, String userName, String userEmail, String userPassword, String userGender,
			String userRole) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userGender = userGender;
		this.userRole = userRole;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public static User getloggedInUser() {
		return loggedInUser;
	}

	public static void setloggedInUser(User currentUser) {
		User.loggedInUser = currentUser;
	}

	private static Database connect = Database.getConnection();

	public static void login(String email, String password) {
		String query = String.format("SELECT * FROM user WHERE UserEmail = '%s' AND UserPassword = '%s'", email, password);
		ResultSet rs = connect.executeQuery(query);
		try {
			if (rs.next()) {
				Integer userID = rs.getInt("UserID");
				String userName = rs.getString("UserName");
				String userEmail = rs.getString("UserEmail");
				String userPassword = rs.getString("UserPassword");
				String userGender = rs.getString("UserGender");
				String userRole = rs.getString("UserRole");

				User user = new User(userID, userName, userEmail, userPassword, userGender, userRole);
				
				setloggedInUser(user);
				
				if (user.getUserRole().equals("Customer")) {
					MainFormUser mainFormUser = MainFormUser.getData();
					
				} else {
					MainFormAdmin mainFormAdmin = MainFormAdmin.getData();
				}
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Message");
				alert.setContentText("Login success!");
				alert.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Alert alert = new Alert(AlertType.ERROR); 
		alert.setHeaderText("Error");
		alert.setContentText("Credential invalid!");
		alert.show();
	}

	public static void insertUser(String name, String email, String password, String gender) {
		Database connect = Database.getConnection();
		String query = String.format("INSERT INTO user (`UserID`, `UserName`, `UserEmail`, `UserPassword`, `UserGender`, `UserRole`) VALUES ('0','%s','%s','%s','%s','Customer')", name, email, password, gender);
		connect.executeUpdate(query);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Message");
		alert.setContentText("Login success!");
		alert.show();
		
		Login l = Login.getData();
	}

	public static void logout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Message");
		alert.setContentText("Logout success!");
		alert.show();
		User.setloggedInUser(null);
		Login l = Login.getData();
	}

}
