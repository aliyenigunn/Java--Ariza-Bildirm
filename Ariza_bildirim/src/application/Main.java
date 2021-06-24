package application;
	
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		
		try {
			Parent root=FXMLLoader.load(getClass().getResource("giris.fxml"));
			Scene scene = new Scene(root,950,530);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image img = new Image("/images/login.png");
			primaryStage.getIcons().add(img);
			primaryStage.setTitle("Giriþ Ekraný");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
}
