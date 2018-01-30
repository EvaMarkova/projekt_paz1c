package sk.upjs.ics.projekt;

import sk.upjs.ics.projekt.UpravitZahrSluzbuController;
import sk.upjs.ics.projekt.PridatZahrSluzbuController;
import sk.upjs.ics.projekt.DruhSluzbyAdminSceneController;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.projekt.ZahradnaSluzba;
import sk.upjs.ics.projekt.fxmodel.ZahradneSluzbyFxModel;

public class ZoznamZahrSluziebAdminController {

    private JdbcTemplate jdbcTemplate;
    private final ZahradneSluzbyFxModel sluzbyModel = new ZahradneSluzbyFxModel();
    private Long id;

    @FXML
    private TableView<ZahradnaSluzba> zoznamTableView;

    @FXML
    private Button pridatSluzbuButton;

    @FXML
    private Button upravitSluzbuButton;

    @FXML
    private Button vymazatSluzbuButton;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<ZahradnaSluzba, String> nazovCol;

    @FXML
    void initialize() {

        nazovCol.setCellValueFactory(new PropertyValueFactory<>("nazov"));
        zoznamTableView.setItems(sluzbyModel.getZahradneSluzby());

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

            PridatZahrSluzbuController controller
                    = new PridatZahrSluzbuController();
            pridatSluzbuButton.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PridatZahrSluzbu.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("AGRO Metlife - Pridať záhradnú službu");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        upravitSluzbuButton.setOnAction(eh -> {
            id = zoznamTableView.getSelectionModel().getSelectedItem().getId();

            UpravitZahrSluzbuController controller
                    = new UpravitZahrSluzbuController(id);
            upravitSluzbuButton.getScene().getWindow().hide();
            try {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("UpravitZahrSluzbu.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("AGRO Metlife - Upraviť záhradnú službu");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }

        });

        vymazatSluzbuButton.setOnAction(eh -> {
            if (jdbcTemplate == null) {
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("projekt_user");
                dataSource.setPassword("paz1cisgreat");
                dataSource.setDatabaseName("projekt");
                jdbcTemplate = new JdbcTemplate(dataSource);
            }
            ZahradnaSluzba zahradnaSluzba = zoznamTableView.getSelectionModel().getSelectedItem();
            String sql = "SET SQL_SAFE_UPDATES = 0";
            jdbcTemplate.update(sql);
            sql = "DELETE FROM zahradne_sluzby WHERE id = " + zahradnaSluzba.getId();
            jdbcTemplate.update(sql);
            int selectedIndex = zoznamTableView.getSelectionModel().getSelectedIndex();
            zoznamTableView.getItems().remove(selectedIndex);
        });

    }
}
