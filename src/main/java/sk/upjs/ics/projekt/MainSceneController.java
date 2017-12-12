package sk.upjs.ics.projekt;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainSceneController {
    
    @FXML
    private Button adminButton;

    @FXML
    private Button zakaznikButton;
    
    @FXML
    void initialize() {
         adminButton.setOnAction(eh -> {
            DruhSluzbySceneController controller = 
                    new DruhSluzbySceneController();
             showDruhSluzbyWindow(controller);
        });
         zakaznikButton.setOnAction(eh -> {
             
             DruhSluzbySceneController controller = 
                     new DruhSluzbySceneController();             
             showDruhSluzbyWindow(controller);
         });
    }
         
         
    private void showDruhSluzbyWindow(DruhSluzbySceneController controller) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("DruhSluzbyScene.fxml"));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Metlife - druhy služieb");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // toto sa vykona az po zatvoreni okna
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
    
}
