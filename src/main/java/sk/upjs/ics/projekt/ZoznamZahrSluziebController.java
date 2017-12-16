package sk.upjs.ics.projekt;

import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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
        
//        SpinnerValueFactory.IntegerSpinnerValueFactory sprinnerValueFactory =  
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10,1);
        
        
        
        homeButton.setOnAction(eh -> { MainSceneController controller = new MainSceneController();
        try {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("MainScene.fxml"));
        loader.setController(controller);
        
        Parent parentPane = loader.load();
        Scene scene = new Scene(parentPane);
         Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("AGRO Metlife - reklamné a záhradnícke služby");
        stage.show(); } catch (IOException iOException) {
            iOException.printStackTrace();
        }
            
        });
        
    }
}