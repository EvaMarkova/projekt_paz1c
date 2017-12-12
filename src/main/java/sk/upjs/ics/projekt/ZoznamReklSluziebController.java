
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ZoznamReklSluziebController {
     private final ReklamneSluzbyFxModel reklSluzbyModel = new ReklamneSluzbyFxModel();
    
    @FXML
    private ComboBox<ReklamnaSluzba> zoznamCombobox;
        
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
    void initialize() {
        zoznamCombobox.setItems(reklSluzbyModel.getReklamneSluzby());
        zoznamCombobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ReklamnaSluzba>() {
            @Override
            public void changed(ObservableValue<? extends ReklamnaSluzba> ov, ReklamnaSluzba old, ReklamnaSluzba newValue) {
                reklSluzbyModel.setVybrataReklamnaSluzba(newValue);
            }
        });
        
        reklSluzbyModel.vybrataReklamnaSluzbaProperty().addListener(new ChangeListener<ReklamnaSluzba>() {
            @Override
            public void changed(ObservableValue<? extends ReklamnaSluzba> ov, ReklamnaSluzba old, ReklamnaSluzba newValue) {
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
        
        if (reklSluzbyModel.getReklamneSluzby().size() > 0) {
            reklSluzbyModel.setVybrataReklamnaSluzba(reklSluzbyModel.getReklamneSluzby().get(0));
            popisSluzbyLabel.setText(reklSluzbyModel.getReklamneSluzby().get(0).getPopis());
            cenaSluzbyLabel.setText("Cena je " +reklSluzbyModel.getReklamneSluzby().get(0).getCena()+"€ za 1 kus");
        }
                
        reklSluzbyModel.vybrataReklamnaSluzbaProperty().addListener(new ChangeListener<ReklamnaSluzba>() {
            @Override
            public void changed(ObservableValue<? extends ReklamnaSluzba> ov, ReklamnaSluzba old, ReklamnaSluzba newValue) {
                popisSluzbyLabel.setText(old.getPopis());
                if (newValue != null) {
                   popisSluzbyLabel.setText(newValue.getPopis());
                   cenaSluzbyLabel.setText("Cena je " +newValue.getCena()+"€ za 1 kus");
               }
            }
        });
        
        homeButton.setOnAction(eh -> { MainSceneController controller = new MainSceneController();
        try {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("MainScene.fxml"));
        loader.setController(controller);
        
        Parent parentPane = loader.load();
        Scene scene = new Scene(parentPane);
         Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Metlife - reklamné a záhradnícke služby");
        stage.show(); } catch (IOException iOException) {
            iOException.printStackTrace();
        }
            
        });
        
    }
}
