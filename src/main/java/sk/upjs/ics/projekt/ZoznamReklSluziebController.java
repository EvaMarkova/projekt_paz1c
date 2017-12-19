package sk.upjs.ics.projekt;

import sk.upjs.ics.projekt.KosikController;
import sk.upjs.ics.projekt.DruhSluzbySceneController;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.projekt.ReklamnaSluzba;
import sk.upjs.ics.projekt.ReklamneSluzbyFxModel;

public class ZoznamReklSluziebController {

    private JdbcTemplate jdbcTemplate;
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
    private Spinner<Integer> pocetSpinner;

    @FXML
    private Label nazovSluzbyLabel;

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
            cenaSluzbyLabel.setText("Cena je " + reklSluzbyModel.getReklamneSluzby().get(0).getCena() + "€ za 1 kus");
            nazovSluzbyLabel.setText(reklSluzbyModel.getReklamneSluzby().get(0).getNazov());
        }

        reklSluzbyModel.vybrataReklamnaSluzbaProperty().addListener(new ChangeListener<ReklamnaSluzba>() {
            @Override
            public void changed(ObservableValue<? extends ReklamnaSluzba> ov, ReklamnaSluzba old, ReklamnaSluzba newValue) {
                popisSluzbyLabel.setText(old.getPopis());
                if (newValue != null) {
                    nazovSluzbyLabel.setText(newValue.getNazov());
                    popisSluzbyLabel.setText(newValue.getPopis());
                    cenaSluzbyLabel.setText("Cena je " + newValue.getCena() + "€ za 1 kus");
                }
            }
        });

        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
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
            if (jdbcTemplate == null) {
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("projekt_user");
                dataSource.setPassword("paz1cisgreat");
                dataSource.setDatabaseName("projekt");
                jdbcTemplate = new JdbcTemplate(dataSource);
            }
            String sql = "INSERT INTO kosik(nazov,pocet,cena) VALUES(?,?,?)";
            jdbcTemplate.update(sql, reklSluzbyModel.getVybrataReklamnaSluzba().getNazov(), pocetSpinner.getValue(),
                    pocetSpinner.getValue() * reklSluzbyModel.getVybrataReklamnaSluzba().getCena());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informácia");
            alert.setHeaderText(null);
            alert.setContentText("Reklamná služba bola pridaná do košíka");
            alert.showAndWait();

        });

    }
}
