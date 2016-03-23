package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
// http://code.makery.ch/library/javafx-8-tutorial/part1/ → dobry przyklad 

public class Main extends Application {
	static Stage stage; 
	private static GridPane root = new GridPane(); 
	public static GridPane getRoot (){
		return root;}
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
			stage = primaryStage;
			 root = (GridPane) FXMLLoader.load(Main.class.getResource("SetSize.fxml"));
			 Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
	}

	
	
	public static void main(String[] args) {
		launch(args);
	}
}
