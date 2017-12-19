package sk.upjs.ics.projekt;


import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.projekt.ZahradnaSluzba;
import sk.upjs.ics.projekt.fxmodel.ZahradneSluzbyFxModel;


public class ZoznamZahrSluziebController {
     private final ZahradneSluzbyFxModel zahrSluzbyModel = new ZahradneSluzbyFxModel();
    
    @FXML
    private ComboBox<ZahradnaSluzba> zoznamCombobox;
        
    @FXML
    private TextField popisSluzbyTextField;

    @FXML
    private Button kosikButton;
    
    @FXML
    private Button pridatDoKosikaButton;

    @FXML
    private Button homeButton;
    
    @FXML
    private Label popisSluzbyLabel;
    
    @FXML
    private Label cenaSluzbyLabel;
    
    @FXML
    private Spinner<Integer> pocetSpinner;
    
    @FXML
    private Label nazovSluzbyLabel;

    
    @FXML
    void initialize() {
        zoznamCombobox.setItems(zahrSluzbyModel.getZahradneSluzby());
        zoznamCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ZahradnaSluzba>() {
            @Override
            public void changed(ObservableValue<? extends ZahradnaSluzba> ov, ZahradnaSluzba old, ZahradnaSluzba newValue) {
                zahrSluzbyModel.setVybrataZahradnaSluzba(newValue);
            }
        });
        
        zahrSluzbyModel.vybrataZahradnaSluzbaProperty().addListener(new ChangeListener<ZahradnaSluzba>() {
            @Override
            public void changed(ObservableValue<? extends ZahradnaSluzba> ov, ZahradnaSluzba old, ZahradnaSluzba newValue) {
               if (newValue == null) {
                    zoznamCombobox.getSelectionModel().clearSelection();
                } else {
                    zoznamCombobox.getSelectionModel().select(newValue);
                }
            }
        });
        // ak text presiahne label, bude sa vypisovat na dalsom riadku
        popisSluzbyLabel.wrapTextProperty().setValue(true);
        popisSluzbyLabel.setAlignment(Pos.TOP_LEFT);
        cenaSluzbyLabel.setAlignment(Pos.CENTER);
        cenaSluzbyLabel.setStyle("-fx-border-color: black;");       
        
        if (zahrSluzbyModel.getZahradneSluzby().size() > 0) {
            zahrSluzbyModel.setVybrataZahradnaSluzba(zahrSluzbyModel.getZahradneSluzby().get(0));
            nazovSluzbyLabel.setText(zahrSluzbyModel.getZahradneSluzby().get(0).getNazov());
            popisSluzbyLabel.setText(zahrSluzbyModel.getZahradneSluzby().get(0).getPopis());
            cenaSluzbyLabel.setText("Cena je " +zahrSluzbyModel.getZahradneSluzby().get(0).getCena()+"€ za 1 hod.");
        }
                
        zahrSluzbyModel.vybrataZahradnaSluzbaProperty().addListener(new ChangeListener<ZahradnaSluzba>() {
            @Override
            public void changed(ObservableValue<? extends ZahradnaSluzba> ov, ZahradnaSluzba old, ZahradnaSluzba newValue) {
                popisSluzbyLabel.setText(old.getPopis());
                if (newValue != null) {
                   nazovSluzbyLabel.setText(newValue.getNazov());
                   popisSluzbyLabel.setText(newValue.getPopis());                   
                   cenaSluzbyLabel.setText("Cena je " +newValue.getCena()+"€ za 1 hod.");
               }
            }
        });
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(1,Integer.MAX_VALUE,1);
        pocetSpinner.setValueFactory(spinnerValueFactory);
      
        
        homeButton.setOnAction(eh -> { 
            DruhSluzbySceneController controller = new DruhSluzbySceneController();
        try {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("DruhSluzbyScene.fxml"));
        loader.setController(controller);
        
        Parent parentPane = loader.load();
        Scene scene = new Scene(parentPane);
         Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("AGRO Metlife - reklamné a záhradné služby");
        homeButton.getScene().getWindow().hide();
        stage.show(); 
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
        
        
            
        }); 
        
        
        kosikButton.setOnAction(eh -> {
          try {
              KosikController controller = new KosikController();
              FXMLLoader loader = new FXMLLoader(
                      getClass().getResource("KosikScene.fxml"));
              loader.setController(controller);
              
              Parent parentPane = loader.load();
              Scene scene = new Scene(parentPane);
              Stage stage = new Stage();
              stage.setScene(scene);
              stage.setTitle("AGRO Metlife - Košík");
              stage.initModality(Modality.APPLICATION_MODAL);
              stage.show(); 
              
              
          } catch (IOException ex) {
              Logger.getLogger(DruhSluzbySceneController.class.getName()).log(Level.SEVERE, null, ex);
          }
      });
        
        pridatDoKosikaButton.setOnAction(eh -> {
            JdbcTemplate jdbcTemplate;          
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("projekt_user");
            dataSource.setPassword("paz1cisgreat");
            dataSource.setDatabaseName("projekt");
            jdbcTemplate = new JdbcTemplate(dataSource);        
            String sql = "INSERT INTO kosik(nazov,pocet,cena) VALUES(?,?,?)";                
            jdbcTemplate.update(sql, zahrSluzbyModel.getVybrataZahradnaSluzba().getNazov(),pocetSpinner.getValue(), 
                      pocetSpinner.getValue()*zahrSluzbyModel.getVybrataZahradnaSluzba().getCena());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informácia");
            alert.setHeaderText(null);
            alert.setContentText("Záhradná služba bola pridaná do košíka");
            alert.showAndWait();
            
        });
        
        
       
        
    }
}