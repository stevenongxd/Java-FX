package user;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Login;


public class MainFormUser extends Application {
	
	Scene scene;
	BorderPane bp;
	Menu userM, transactionM;
	MenuBar menuBar;
	MenuItem logoutMI, buyMI, historyMI;
	
	public void init() {
		
		menuBar = new MenuBar();
		userM = new Menu("User");
		transactionM = new Menu("Transaction");
		logoutMI = new MenuItem("Logout");
		buyMI = new MenuItem("Buy Watch");
		historyMI = new MenuItem("My Transaction History");
		
		menuBar.getMenus().addAll(userM, transactionM);
		userM.getItems().addAll(logoutMI);
		transactionM.getItems().addAll(buyMI, historyMI);
		
		bp = new BorderPane();
		
		scene = new Scene(bp, 800, 800);
		
		bp.setStyle("-fx-background-color: #808080;");
		
		bp.setTop(menuBar);
	}
	
	public void eventHandler() {
		buyMI.setOnAction(e -> {
			BuyWatch buyWatch = BuyWatch.getData();
		});

		historyMI.setOnAction(e -> {
			TransactionHistory transactionHistory = TransactionHistory.getData();

		});

		logoutMI.setOnAction(e -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Error");
			alert.setContentText("Logout success!");
			alert.show();
			User.setloggedInUser(null);
			Login l = Login.getData();
		});
	}
	
	
	public static MainFormUser getData() {
		MainFormUser mainFormUser = new MainFormUser();
		return mainFormUser;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(scene);
		primaryStage.setTitle("Main Page Customer");
		primaryStage.show();
		
	}
}
