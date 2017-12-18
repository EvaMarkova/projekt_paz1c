
package sk.upjs.ics.projekt;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DruhSluzbyAdminSceneController {
    
    @FXML
    private Button zahradnickeSluzbyButton;

    @FXML
    private Button reklamneSluzbyButton;

    @FXML
    void initialize() {
        zahradnickeSluzbyButton.setOnAction(eh -> {
            ZoznamZahrSluziebAdminController controller = 
                    new ZoznamZahrSluziebAdminController();
            zahradnickeSluzbyButton.getScene().getWindow().hide();
               try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("ZoznamZahrSluziebAdmin.fxml"));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("AGRO Metlife - Záhradné služby");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
                // toto sa vykona az po zatvoreni okna
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
        });
        
        reklamneSluzbyButton.setOnAction(eh -> {
            ZoznamReklSluziebAdminController controller = 
                    new ZoznamReklSluziebAdminController();
            reklamneSluzbyButton.getScene().getWindow().hide();
               try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("ZoznamReklSluziebAdmin.fxml"));
            loader.setController(controller);
            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("AGRO Metlife - reklamné služby");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
                // toto sa vykona az po zatvoreni okna
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
        });
           
        
    }
}