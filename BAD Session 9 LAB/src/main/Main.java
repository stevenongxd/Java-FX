package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application{
	
	Button regBtn, loginBtn, openFile;
	FlowPane buttonFlowPane, sliderFlowPane;
	Slider rSlider, gSlider, bSlider;
	Boolean ctrlClicked = false;
	
	FileChooser fc;
	ScrollPane sp;
	
	Image img;
	ImageView imgView;
	
	InputStream inputStream;

	public static void main(String[] args) {
		launch(args);
	}
	
	public void init() {
		regBtn = new Button("Register Button");
		loginBtn = new Button("Login Button");
		openFile = new Button("Open File");
		fc = new FileChooser();
		buttonFlowPane = new FlowPane();
		
		buttonFlowPane.getChildren().addAll(regBtn, loginBtn, openFile);
		
		rSlider = new Slider(0, 1, 0);
		gSlider = new Slider(0, 1, 0);
		bSlider = new Slider(0, 1, 0);
		sliderFlowPane = new FlowPane();
		sliderFlowPane.getChildren().addAll(rSlider, gSlider, bSlider);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init();
		BorderPane mainPane = new BorderPane();
		BorderPane loginPage = new Login();
		BorderPane regPage = new Register();
		
		mainPane.setTop(buttonFlowPane);
		mainPane.setBottom(sliderFlowPane);
		mainPane.setCenter(loginPage);
		
		regBtn.setOnAction((action) -> {
			mainPane.setCenter(regPage);
		});
		
		loginBtn.setOnAction((action) -> {
			mainPane.setCenter(loginPage);
		});
		
		mainPane.setOnKeyTyped (event -> {
			System.out.println(event.getCharacter());
		});
		
		mainPane.setOnKeyPressed((event) -> {
			if(event.getCode() == KeyCode.CONTROL){
			ctrlClicked = true;
		}
		
		if(event.getCode() == KeyCode.L && ctrlClicked == true) {
			mainPane.setCenter(loginPage);
		}else if(event.getCode() == KeyCode.R && ctrlClicked == true) {
			mainPane.setCenter(regPage);
		}
		});
		
		mainPane.setOnKeyReleased((event) -> {
			if(event.getCode() == KeyCode.CONTROL) {
				ctrlClicked = false;
			}
		});
		
		rSlider.setOnMouseDragged((event) -> {
			Color c = new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue(), 1);
			mainPane.setBackground(new Background(new BackgroundFill(c, null, null)));
		});
		
		gSlider.setOnMouseDragged((event) -> {
			Color c = new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue(), 1);
			mainPane.setBackground(new Background(new BackgroundFill(c, null, null)));
		});
		
		bSlider.setOnMouseDragged((event) -> {
			Color c = new Color(rSlider.getValue(), gSlider.getValue(), bSlider.getValue(), 1);
			mainPane.setBackground(new Background(new BackgroundFill(c, null, null)));
		});
		
		openFile.setOnAction((event) -> {
			File file = fc.showOpenDialog(primaryStage);
			
			try {
			inputStream = new FileInputStream(file);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			img = new Image(inputStream);
			imgView = new ImageView();
			imgView.setImage(img);
			
			sp = new ScrollPane();
			sp.setContent(imgView);
			mainPane.setRight(sp);
			
//			mainPane.setBackground(new Background(new BackgroundImage(img, null, null, null, null)));
		});
		
		primaryStage.setResizable(true);
		primaryStage.setScene(new Scene(mainPane, 500, 500));
		primaryStage.show();
	}

}
