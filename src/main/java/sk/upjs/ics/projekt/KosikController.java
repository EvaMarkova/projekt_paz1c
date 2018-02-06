package sk.upjs.ics.projekt;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.jdbc.core.JdbcTemplate;

public class KosikController {

    private JdbcTemplate jdbcTemplate;
    private Double vyslednaCena;
    private final RiadokKosikFxModel riadkyKosikModel = new RiadokKosikFxModel();

    @FXML
    private TableView<RiadokKosik> kosikTableView;

    @FXML
    private Label vyslednaCenaTextField;

    @FXML
    private Button zmazatButton;

    @FXML
    private Button potvrditNakupButton;

    @FXML
    private TableColumn<RiadokKosik, String> nazovCol;

    @FXML
    private TableColumn<RiadokKosik, Integer> pocetCol;

    @FXML
    private TableColumn<RiadokKosik, Double> cenaCol;

    @FXML
    private TableColumn<RiadokKosik, Long> idCol;

    @FXML
    void initialize() {

        potvrditNakupButton.setOnAction(eh -> {
            try {

                if (kosikTableView.getItems().size() > 0) {
                    List<Integer> pocty = new ArrayList<>();
                    for (RiadokKosik riadok : kosikTableView.getItems()) {
                        pocty.add(pocetCol.getCellObservableValue(riadok).getValue());
                    }
                    RegistracnyFormularController controller
                            = new RegistracnyFormularController(vyslednaCena, pocty.toString());
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("RegistracnyFormularScene.fxml"));
                    loader.setController(controller);
                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("AGRO Metlife - Formulár");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    potvrditNakupButton.getScene().getWindow().hide();
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("AGRO Metlife - Chybové hlásenie");
                    alert.setHeaderText(null);
                    alert.setContentText("V košíku sa nenachádzajú žiadne služby.");
                    alert.showAndWait();
                }

            } catch (IOException ex) {
                Logger.getLogger(DruhSluzbySceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nazovCol.setCellValueFactory(new PropertyValueFactory<>("nazov"));
        pocetCol.setCellValueFactory(new PropertyValueFactory<>("pocet"));
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));
        kosikTableView.setItems(riadkyKosikModel.getRiadkyKosika());

        zmazatButton.setOnAction(eh -> {
            if (jdbcTemplate == null) {
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("projekt_user");
                dataSource.setPassword("paz1cisgreat");
                dataSource.setDatabaseName("projekt");
                jdbcTemplate = new JdbcTemplate(dataSource);
            }
            RiadokKosik riadokKosik = kosikTableView.getSelectionModel().getSelectedItem();
            String sql = "SET SQL_SAFE_UPDATES = 0";
            jdbcTemplate.update(sql);
            sql = "DELETE FROM polozky_kosika WHERE id = " + riadokKosik.getId();
            jdbcTemplate.update(sql);
            int selectedIndex = kosikTableView.getSelectionModel().getSelectedIndex();
            kosikTableView.getItems().remove(selectedIndex);
            if (kosikTableView.getItems().size() > 0) {
                sql = "SELECT SUM(cena) FROM polozky_kosika";
                vyslednaCena = jdbcTemplate.queryForObject(sql, Double.class);
                vyslednaCenaTextField.setText("Výsledna cena je " + vyslednaCena + "€");
            } else {
                vyslednaCenaTextField.setText("Výsledna cena je 0€");
            }
        });

        if (jdbcTemplate == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("projekt_user");
            dataSource.setPassword("paz1cisgreat");
            dataSource.setDatabaseName("projekt");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        if (kosikTableView.getItems().size() > 0) {
            String sql = "SELECT SUM(cena) FROM polozky_kosika";
            vyslednaCena = jdbcTemplate.queryForObject(sql, Double.class);
            vyslednaCenaTextField.setText("Výsledna cena je " + vyslednaCena + "€");
        } else {
            vyslednaCenaTextField.setText("Výsledna cena je 0€");
        }
    }

}
