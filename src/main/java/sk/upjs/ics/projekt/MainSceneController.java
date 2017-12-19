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
             
                AdminSceneController controller = new AdminSceneController();
                adminButton.getScene().getWindow().hide();
                
                               try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("AdminScene.fxml"));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("AGRO Metlife - Prihlásiť sa ako admin");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            

            // toto sa vykona az po zatvoreni okna
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
//            DruhSluzbyAdminSceneController controller = 
//                    new DruhSluzbyAdminSceneController();
//            adminButton.getScene().getWindow().hide();
//               try {
//            FXMLLoader loader = new FXMLLoader(
//                    getClass().getResource("DruhSluzbyAdminScene.fxml"));
//            loader.setController(controller);
//
//            Parent parentPane = loader.load();
//            Scene scene = new Scene(parentPane);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.setTitle("AGRO Metlife - Druhy služieb");
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.showAndWait();
//            
//
//            // toto sa vykona az po zatvoreni okna
//        } catch (IOException iOException) {
//            iOException.printStackTrace();
//        }
        });
        
         zakaznikButton.setOnAction(eh -> {
             
             DruhSluzbySceneController controller = 
                     new DruhSluzbySceneController();  
             zakaznikButton.getScene().getWindow().hide();
               try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("DruhSluzbyScene.fxml"));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("AGRO Metlife - Druhy služieb");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            

            // toto sa vykona az po zatvoreni okna
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
            
         });    
         
    }
            
}
