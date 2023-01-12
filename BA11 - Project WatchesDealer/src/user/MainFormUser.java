package user;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import main.Main;

public class MainFormUser {
	
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
			BuyWatch bw = BuyWatch.getData();
			bp.setCenter(bw.createWindow());
		});

		historyMI.setOnAction(e -> {
			TransactionHistory th = TransactionHistory.getData();
			bp.setCenter(th.createWindow());
		});

		logoutMI.setOnAction(e -> {
			CheckUser.logout();
		});
	}
	
private static MainFormUser MainFormUser;
	
	public static MainFormUser getData() {
		if (MainFormUser.equals(null)) {
			MainFormUser = new MainFormUser();
		}
		return MainFormUser;
	}

	public void show() {
		init();
		eventHandler();
		Main.sceneChanger(scene, "Main Page Customer");
	}
}
