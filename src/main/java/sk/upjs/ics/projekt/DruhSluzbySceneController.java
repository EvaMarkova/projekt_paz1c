package sk.upjs.ics.projekt;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
 
public class DruhSluzbySceneController {
    
    @FXML
    private Button zahradnickeSluzbyButton;

    @FXML
    private Button reklamneSluzbyButton;
    
    @FXML
    void initialize() {
      zahradnickeSluzbyButton.setOnAction(eh -> {
          try {
              ZoznamZahrSluziebController controller = new ZoznamZahrSluziebController();
              FXMLLoader loader = new FXMLLoader(
                      getClass().getResource("ZoznamZahrSluzieb.fxml"));
              loader.setController(controller);
              
              Parent parentPane = loader.load();
              Scene scene = new Scene(parentPane);
              Stage stage = new Stage();
              stage.setScene(scene);
              stage.setTitle("AGRO Metlife - Služby");
              stage.initModality(Modality.APPLICATION_MODAL);
              zahradnickeSluzbyButton.getScene().getWindow().hide();
              stage.show(); 
              
          } catch (IOException ex) {
              Logger.getLogger(DruhSluzbySceneController.class.getName()).log(Level.SEVERE, null, ex);
          }
      });
      
     reklamneSluzbyButton.setOnAction(eh -> {
          try {
              ZoznamReklSluziebController controller = new ZoznamReklSluziebController();
              FXMLLoader loader = new FXMLLoader(
                      getClass().getResource("ZoznamReklSluzieb.fxml"));
              loader.setController(controller);
              
              Parent parentPane = loader.load();
              Scene scene = new Scene(parentPane);
              
              Stage stage = new Stage();
              stage.setScene(scene);
              stage.setTitle("AGRO Metlife - Služby");
              stage.initModality(Modality.APPLICATION_MODAL);
              reklamneSluzbyButton.getScene().getWindow().hide();
              stage.show();
          } catch (IOException ex) {
              Logger.getLogger(DruhSluzbySceneController.class.getName()).log(Level.SEVERE, null, ex);
          }
      });
    }
    
   
}
