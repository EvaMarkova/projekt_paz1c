package sk.upjs.ics.projekt;

import sk.upjs.ics.projekt.DruhSluzbyAdminSceneController;
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

class UpravitZahrSluzbuController {

    private JdbcTemplate jdbcTemplate;
    private Long id;

    public UpravitZahrSluzbuController(Long id) {
        this.id = id;
    }

    @FXML
    private Button homeButton;

    @FXML
    private TextField nazovSluzbyTextField;
    
    @FXML
    private TextField rocneObdobieTextField;

    @FXML
    private TextField popisSluzbyTextField;

    @FXML
    private Button upravitSluzbuButton;

    @FXML
    private TextField cenaTextField;

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

        if (jdbcTemplate == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("projekt_user");
            dataSource.setPassword("paz1cisgreat");
            dataSource.setDatabaseName("projekt");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }

        String sql = "SELECT nazov FROM zahradne_sluzby WHERE id = " + id;
        String nazov = jdbcTemplate.queryForObject(sql, String.class);
        sql = "SELECT rocne_obdobie FROM zahradne_sluzby WHERE id = " + id;
        String rocneObdobie = jdbcTemplate.queryForObject(sql, String.class);
        sql = "SELECT popis FROM zahradne_sluzby WHERE id = " + id;
        String popis = jdbcTemplate.queryForObject(sql, String.class);
        sql = "SELECT cena FROM zahradne_sluzby WHERE id = " + id;
        String cena = jdbcTemplate.queryForObject(sql, String.class);

        nazovSluzbyTextField.setText(nazov);
        rocneObdobieTextField.setText(rocneObdobie);
        popisSluzbyTextField.setText(popis);
        cenaTextField.setText(cena);

        upravitSluzbuButton.setOnAction(eh -> {

            String novyNazov = nazovSluzbyTextField.getText();
            String noveRocneObdobie = rocneObdobieTextField.getText();
            String novyPopis = popisSluzbyTextField.getText();
            String novaCena = cenaTextField.getText();
            String novySql = "UPDATE zahradne_sluzby SET nazov = ?, rocne_obdobie = ?, popis = ?, cena = ?  WHERE id = " + id;
            jdbcTemplate.update(novySql, novyNazov, noveRocneObdobie, novyPopis, Double.parseDouble(novaCena));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AGRO Metlife - Informácia");
            alert.setHeaderText(null);
            alert.setContentText("Podarilo sa upraviť vybratú záhradnú službu.");
            upravitSluzbuButton.getScene().getWindow().hide();
            alert.showAndWait();

            ZoznamZahrSluziebAdminController controller
                    = new ZoznamZahrSluziebAdminController();
            //pridatSluzbuButton.getScene().getWindow().hide();
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
    }

}
