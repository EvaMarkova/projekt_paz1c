
package sk.upjs.ics.projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ProjektApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainSceneController controller = new MainSceneController();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("MainScene.fxml"));
        loader.setController(controller);
        
        Parent parentPane = loader.load();
        Scene scene = new Scene(parentPane);
        
        stage.setScene(scene);
        stage.setTitle("AGRO Metlife - reklamné a záhradnícke služby");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
