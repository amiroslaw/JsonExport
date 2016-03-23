package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class StartController implements Initializable {
	public Main main = new Main();
	@FXML
	private Button btnViewCreate;
	@FXML
	private TextField txtWidth;
	@FXML
	private TextField txtHeight;
	static int height = 0;
	static int width = 0;
	private Button btnCreateJson = new Button("Generuj JSON ;)");
	private Button btnKatalog = new Button("Katalog");
	private Button btnCofnij = new Button("Cofnij");
	private TextField txtNazwaPliku = new TextField("PlikJson");
	TextField[][] tabDate;
			ScrollBar sb = new ScrollBar(); 

	@FXML
	public void viewCreator() {
		System.out.println("height" + txtHeight.getText());
		height = Integer.parseInt(txtHeight.getText());
		width = Integer.parseInt(txtWidth.getText());
		try {
			tabDate = new TextField[width][height];
			GridPane gp = new GridPane();
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					// GridPane.setConstraints(tabDate[i][j], height,width);
					tabDate[i][j] = new TextField();
					tabDate[i][j].setPrefHeight(50);
					tabDate[i][j].setPrefWidth(120);
					tabDate[i][j].setPromptText("X: " + i + " Y: " + j);
					gp.add(tabDate[i][j], i, j);
				}
			}
			gp.add(btnCreateJson, 2, height + 1);
			gp.add(btnKatalog, 1, height + 1);
			txtNazwaPliku.setPromptText("nazwa pliku");
			gp.add(txtNazwaPliku, 0, height + 1);
			gp.add(btnCofnij, 3, height + 1);
			// scroll bar gdy przewijami gp sie zmienia
			sb.setOrientation(Orientation.VERTICAL);
			sb.valueProperty().addListener(new ChangeListener<Number>() {
			    public void changed(ObservableValue<? extends Number> ov,
			        Number old_val, Number new_val) {
			            gp.setLayoutY(-new_val.doubleValue()*3);
			        }
			});
			gp.add(sb,width+1,0,1,height/2);
			gp.setPadding(new Insets(10));
			gp.setVgap(10);
			gp.setHgap(10);
			gp.setPrefWidth(600);
			Scene scene = new Scene(gp);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String pathHome = System.getProperty("user.home");
	// static File folder = new File(pathHome + "/euro");
//	static File folder = new File(pathHome);
//	String fileName; 
//		fileName=txtNazwaPliku.getText();

	public void writeJson() throws FileNotFoundException {
		String sciezkaPliku = pathHome + "/"+txtNazwaPliku.getText()+".json";
		System.out.println(sciezkaPliku);
		PrintWriter zapis = new PrintWriter(sciezkaPliku);
		zapis.println("{\"c2array\":true,");
		zapis.println("\"size\":[" + width + "," + height + ",1],");
		zapis.println("\"data\": [");
		for (int i = 0; i < width; i++) {
			zapis.println("[");
			for (int j = 0; j < height; j++) {
				zapis.print("[\"" + tabDate[i][j].getText());
				if (j == height - 1) {
					zapis.println("\"]");
				} else {
					zapis.println("\"],");
				}

			}
			if (i == width - 1) {
				zapis.println("]");
			} else {
				zapis.println("],");
			}
		}
		zapis.println("");
		zapis.println("");
		zapis.println("] }");
		zapis.close();
	}

	public void otworzFolder() {
		DirectoryChooser dChooser = new DirectoryChooser();
		dChooser.setTitle("wybierz obrazek");
		dChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File file;
		file = dChooser.showDialog(new Stage());
		if (file != null) {
			System.out.println(file.getAbsolutePath());
			pathHome=file.getAbsolutePath();
		}
	}
	public void viewStart(){
		try {
			System.out.println("view start");
			GridPane grid = (GridPane)FXMLLoader.load(Main.class.getResource("SetSize.fxml"));
			Scene scene = new Scene(grid);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		btnViewCreate.defaultButtonProperty().bind(btnCreateJson.focusedProperty());
		btnCreateJson.setOnAction(e -> {
			try {
				writeJson();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		});
		btnCreateJson.defaultButtonProperty().bind(btnCreateJson.focusedProperty());
		btnKatalog.setOnAction(e -> otworzFolder());
		btnCofnij.setOnAction(e->viewStart());
		btnCofnij.defaultButtonProperty().bind(btnCofnij.focusedProperty());
	}

}
