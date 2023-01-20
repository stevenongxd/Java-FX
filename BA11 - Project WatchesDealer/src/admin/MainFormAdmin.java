package admin;

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
import user.User;

public class MainFormAdmin extends Application{

	Scene scene;
	BorderPane bp;
	Menu userM, managementM;
	MenuBar menuBar;
	MenuItem logoutMI, mpMI, mbMI;
	
	public void init() {
		menuBar = new MenuBar();
		
		userM = new Menu("Admin");
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
			ManageProduct manageProduct = ManageProduct.getData();
		});

		mbMI.setOnAction(e -> {
			ManageBrand manageBrand = ManageBrand.getData();

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
	
	public static MainFormAdmin getData() {
		MainFormAdmin MainFormAdmin = new MainFormAdmin();
		return MainFormAdmin;
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		eventHandler();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Main Page Admin");
		primaryStage.show();
		
	}
	
}
