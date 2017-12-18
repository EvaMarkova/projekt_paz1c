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

public class ZoznamReklSluziebAdminController {

  
    private JdbcTemplate jdbcTemplate;
    private ReklamneSluzbyFxModel sluzbyModel = new ReklamneSluzbyFxModel();
    private Long id;

    @FXML
    private TableView<ReklamnaSluzba> zoznamTableView;

    @FXML
    private Button pridatSluzbuButton;

    @FXML
    private Button upravitSluzbuButton;

    @FXML
    private Button vymazatSluzbuButton;

    @FXML
    private Button homeButton;

    @FXML
    private TableColumn<ReklamnaSluzba, String> nazovCol;

    @FXML
    void initialize() {

        nazovCol.setCellValueFactory(new PropertyValueFactory<>("nazov"));
        zoznamTableView.setItems(sluzbyModel.getReklamneSluzby());

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

            PridatReklSluzbuController controller
                    = new PridatReklSluzbuController();
            pridatSluzbuButton.getScene().getWindow().hide();
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PridatReklSluzbu.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("AGRO Metlife - Pridať reklamnú službu");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

                // toto sa vykona az po zatvoreni okna
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
        
       

        upravitSluzbuButton.setOnAction(eh -> {
            id = zoznamTableView.getSelectionModel().getSelectedItem().getId();
            System.out.println(id);
            
            UpravitReklSluzbuController controller
                    = new UpravitReklSluzbuController(id);
            upravitSluzbuButton.getScene().getWindow().hide();
            try {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("UpravitReklSluzbu.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("AGRO Metlife - Upraviť reklamnú službu");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                // toto sa vykona az po zatvoreni okna
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
