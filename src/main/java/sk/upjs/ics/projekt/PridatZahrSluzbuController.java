package sk.upjs.ics.projekt;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.jdbc.core.JdbcTemplate;

public class PridatZahrSluzbuController {

    private JdbcTemplate jdbcTemplate;

    @FXML
    private Button homeButton;

    @FXML
    private TextField nazovSluzbyTextField;

    @FXML
    private TextField rocneObdobieTextField;

    @FXML
    private TextField popisSluzbyTextField;

    @FXML
    private TextField cenaTextField;

    @FXML
    private Button pridatSluzbuButton;

    @FXML
    void initialize() {
        homeButton.setOnAction(eh -> {
            DruhSluzbyAdminSceneController controller = new DruhSluzbyAdminSceneController();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("DruhSluzbyAdminScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("AGRO Metlife - Reklamné a záhradné služby");
                homeButton.getScene().getWindow().hide();
                stage.show();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        pridatSluzbuButton.setOnAction(eh -> {
            if (jdbcTemplate == null) {
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("projekt_user");
                dataSource.setPassword("paz1cisgreat");
                dataSource.setDatabaseName("projekt");
                jdbcTemplate = new JdbcTemplate(dataSource);
            }
            String sql = "INSERT INTO zahradne_sluzby(rocne_obdobie,nazov,popis,cena) VALUES(?,?,?,?)";
            try {
                Double.parseDouble(cenaTextField.getText());
            } catch (Exception exception) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("AGRO Metlife - Chybové hlásenie");
                alert.setHeaderText("Nepodarilo sa vyvoriť novú záhradnú službu.");
                alert.setContentText("Príčinou je, že v poli pre cenu nie je reálne číslo s bodkou.");
                alert.showAndWait();
            }

            jdbcTemplate.update(sql, rocneObdobieTextField.getText(),
                    nazovSluzbyTextField.getText(),
                    popisSluzbyTextField.getText(),
                    Double.parseDouble(cenaTextField.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AGRO Metlife - Informácia");
            alert.setHeaderText(null);
            alert.setContentText("Podarilo sa vytvoriť novú záhradnú službu.");
            pridatSluzbuButton.getScene().getWindow().hide();
            alert.showAndWait();

            ZoznamZahrSluziebAdminController controller
                    = new ZoznamZahrSluziebAdminController();
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
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

    }
}
