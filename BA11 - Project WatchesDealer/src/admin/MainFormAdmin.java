package admin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.Main;
import user.BuyWatch;
import user.CheckUser;
import user.MainFormUser;
import user.TransactionHistory;

public class MainFormAdmin {

	Scene scene;
	BorderPane bp;
	Menu userM, managementM;
	MenuBar menuBar;
	MenuItem logoutMI, mpMI, mbMI;
	
	public void init() {
		menuBar = new MenuBar();
		userM = new Menu("User");
		managementM = new Menu("Management");
		logoutMI = new MenuItem("Logout");
		mpMI = new MenuItem("Manage Product");
		mbMI = new MenuItem("Manage Brand");
		
		menuBar.getMenus().addAll(userM, managementM);
		userM.getItems().addAll(logoutMI);
		managementM.getItems().addAll(mpMI, mbMI);
		
		bp = new BorderPane();
		
		scene = new Scene(bp, 800, 800);
		
		bp.setStyle("-fx-background-color: #808080;");
		
		bp.setTop(menuBar);
	}
	public void eventHandler() {
		mpMI.setOnAction(e -> {
			ManageProduct mp = ManageProduct.getData();
			bp.setCenter(mp.createWindow());
		});

		mbMI.setOnAction(e -> {
			ManageBrand mb = ManageBrand.getData();
			bp.setCenter(mb.createWindow());
		});

		logoutMI.setOnAction(e -> {
			CheckUser.logout();
		});
	}
	
private static MainFormUser MainFormAdmin;
	
	public static MainFormUser getData() {
		if (MainFormAdmin.equals(null)) {
			MainFormAdmin = new MainFormUser();
		}
		return MainFormAdmin;
	}

	public void show() {
		init();
		eventHandler();
		Main.sceneChanger(scene, "Main Page Admin");
	}
	
}
