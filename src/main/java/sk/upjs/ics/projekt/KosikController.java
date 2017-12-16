package sk.upjs.ics.projekt;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.jdbc.core.JdbcTemplate;

public class KosikController {
   
    private JdbcTemplate jdbcTemplate;
    
    public KosikController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    
    public KosikController() {
        
    }
    
    @FXML
    private TableView<?> kosikTableView;
    
    @FXML
    private Label vyslednaCenaTextField;

    @FXML
    private Button zmazatButton;

    @FXML
    private Button potvrditNakupButton;
    

    @FXML
    void initialize() {        
        
        
        
        potvrditNakupButton.setOnAction(eh -> {
          try {
              
              
              RegistracnyFormularController controller = new RegistracnyFormularController();
              FXMLLoader loader = new FXMLLoader(
                      getClass().getResource("RegistracnyFormularScene.fxml"));
              loader.setController(controller);
              
              Parent parentPane = loader.load();
              Scene scene = new Scene(parentPane);
              Stage stage = new Stage();
              stage.setScene(scene);
              stage.setTitle("AGRO Metlife - formulár");
              stage.initModality(Modality.APPLICATION_MODAL);
              potvrditNakupButton.getScene().getWindow().hide();
              stage.show(); 
              
          } catch (IOException ex) {
              Logger.getLogger(DruhSluzbySceneController.class.getName()).log(Level.SEVERE, null, ex);
          }
        });
    }
    
//    public List<RiadokKosikFxModel> obsahKosika() {
//            String sql = "SELECT nazov_sluzby, pocet, cena FROM kosik";
//              List<RiadokKosikFxModel> riadkyKosika = new ArrayList<>();
//              List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
//              for (Map row : rows) {
//                  RiadokKosik riadokKosik = new RiadokKosik();
//                  riadokKosik.setNazovSluzby((String)row.get("nazov_sluzby"));
//                  riadokKosik.setPocet((Integer)row.get("pocet"));
//                  riadokKosik.setCena((Double)row.get("cena"));
//              }
//          return riadkyKosika;
//        }
//        
   
}
