package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Button btn1 = new Button("Top");
		Button btn2 = new Button("Bottom");
		Button btn3 = new Button("Left");
		Button btn4 = new Button("Right");
		Button btn5 = new Button("Center");
		Button btn6 = new Button("Top2");
		
		HBox hb = new HBox();
		hb.getChildren().add(btn1);
		hb.getChildren().add(btn6);
//		hb.getChildren().addAll(btn1, btn6);
		hb.setPadding(new Insets(10, 10, 10, 10));
		
		Label lbl1 = new Label("Username");
		Label lbl2 = new Label("Password");
		
		TextField TF = new TextField();
		PasswordField PF = new PasswordField();
		
		GridPane grid = new GridPane();
		grid.add(lbl1, 0, 0);
		grid.add(lbl2, 0, 1);
		grid.add(TF, 1, 0);
		grid.add(PF, 1, 1);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10));
		
		RadioButton rb1 = new RadioButton("SD");
		RadioButton rb2 = new RadioButton("SMP");
		RadioButton rb3 = new RadioButton("SMA");
		
		ToggleGroup rbg = new ToggleGroup();
		rb1.setToggleGroup(rbg);
		rb2.setToggleGroup(rbg);
		rb3.setToggleGroup(rbg);
		
		VBox vb = new VBox(5);
		vb.getChildren().addAll(rb1, rb2, rb3);
		
		ComboBox<String> cb = new ComboBox<String>();
		
		//cara GI
		cb.getItems().addAll("1", "2", "3", "4", "5");
		cb.getSelectionModel().selectFirst();
		
		//cara OL
		ObservableList<String> ol = FXCollections.observableArrayList("1", "2", "3", "4", "6");
		cb.setItems(ol);
		
		Slider slide = new Slider();
		slide.setShowTickLabels(true);
		slide.setShowTickMarks(true);
		slide.setMajorTickUnit(20);
		slide.setMinorTickCount(10);
		slide.setMin(-100);
		slide.setMax(100);
		slide.setPadding(new Insets(0, 20, 0, 20));
		
		BorderPane pane = new BorderPane();
		pane.setTop(hb);
		pane.setBottom(slide);
		pane.setLeft(vb);
		pane.setRight(cb);
		pane.setCenter(grid);
		pane.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Scene scene = new Scene(pane, 800, 800);
		
		primaryStage.setTitle("Kelas Responsi");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
