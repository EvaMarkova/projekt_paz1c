package sk.upjs.ics.projekt;

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

public class VymazatZakaznikaControllerAdmin {

    // ZoznamZakaznikovScene.fxml
    private JdbcTemplate jdbcTemplate;
    private ZakaznikFxModel zakaznikModel = new ZakaznikFxModel();

    @FXML
    private Button vymazatZaknikaButton;

    @FXML
    private TableView<Zakaznik> zoznamTableView;

    @FXML
    private TableColumn<Zakaznik, Long> idCol;

    @FXML
    private TableColumn<Zakaznik, String> menoCol;

    @FXML
    private TableColumn<Zakaznik, String> priezviskoCol;

    @FXML
    private TableColumn<Zakaznik, String> adresaCol;

    @FXML
    private TableColumn<Zakaznik, String> cisloCol;

    @FXML
    private TableColumn<Zakaznik, String> emailCol;

    @FXML
    private TableColumn<Zakaznik, String> sluzbyCol;

    @FXML
    private Button homeButton;

    public VymazatZakaznikaControllerAdmin() {

    }
    
    void initialize() {

        menoCol.setCellValueFactory(new PropertyValueFactory<>("meno"));
        zoznamTableView.setItems(zakaznikModel.getZakaznici());

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

        vymazatSluzbuButton.setOnAction(eh -> {
            if (jdbcTemplate == null) {
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("projekt_user");
                dataSource.setPassword("paz1cisgreat");
                dataSource.setDatabaseName("projekt");
                jdbcTemplate = new JdbcTemplate(dataSource);
            }

            ReklamnaSluzba reklamnaSluzba = zoznamTableView.getSelectionModel().getSelectedItem();
            String sql = "SET SQL_SAFE_UPDATES = 0";
            jdbcTemplate.update(sql);
            sql = "DELETE FROM reklamne_sluzby WHERE id = " + reklamnaSluzba.getId();
            jdbcTemplate.update(sql);
            int selectedIndex = zoznamTableView.getSelectionModel().getSelectedIndex();
            zoznamTableView.getItems().remove(selectedIndex);
        });

    }

}
