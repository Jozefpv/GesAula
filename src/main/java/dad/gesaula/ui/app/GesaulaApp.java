package dad.gesaula.ui.app;

import dad.gesaula.ui.controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GesaulaApp extends Application {

	public static Stage primaryStage;
	private MainController gesaulaController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GesaulaApp.primaryStage = primaryStage;
		gesaulaController = new MainController();
		
		primaryStage.setTitle("GesAula");
		primaryStage.setScene(new Scene(gesaulaController.getView()));
		primaryStage.getIcons().add(new Image("/images/app-icon-64x64.png"));
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
