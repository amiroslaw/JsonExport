package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public  class CreateController extends StartController  implements Initializable{
//	Main main = new Main();
	private Button btnViewStart = new Button("wróc"); 
//	public void viewStart(){
//		try {
//			System.out.println("view start");
//			GridPane grid = (GridPane)FXMLLoader.load(Main.class.getResource("SetSize.fxml"));
//			Scene scene = new Scene(grid);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			Main.stage.setScene(scene);
//			Main.stage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 
//		Height.setText(Integer.toString(height));
		btnViewStart.setOnAction(e-> viewStart());
	}
	

}
