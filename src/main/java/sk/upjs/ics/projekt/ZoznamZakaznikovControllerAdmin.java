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
import javafx.stage.Stage;
import org.springframework.jdbc.core.JdbcTemplate;


public class ZoznamZakaznikovControllerAdmin {

    private JdbcTemplate jdbcTemplate;
    private ZakaznikFxModel zakaznikModel = new ZakaznikFxModel();
    private Long id;
   

    @FXML
    private Button vymazatZakaznikaButton;
    
    @FXML 
    private Button pozrietZakaznikaButton;

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
    private TableColumn<Zakaznik, String> vydanyCol;

    @FXML
    private TableColumn<Zakaznik, String> zaplatenyCol;
    
    @FXML
    private Button homeButton;
    
    @FXML
    private Button refreshButton;
    
    @FXML
    void initialize() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        menoCol.setCellValueFactory(new PropertyValueFactory<>("meno"));
        priezviskoCol.setCellValueFactory(new PropertyValueFactory<>("priezvisko"));
        adresaCol.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        cisloCol.setCellValueFactory(new PropertyValueFactory<>("cislo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));      
        vydanyCol.setCellValueFactory(new PropertyValueFactory<>("vydanyKosik"));
        zaplatenyCol.setCellValueFactory(new PropertyValueFactory<>("zaplatenyKosik"));
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
                stage.setTitle("AGRO Metlife - Zákazníci");
                homeButton.getScene().getWindow().hide();
                stage.show();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

        vymazatZakaznikaButton.setOnAction(eh -> {
            if (jdbcTemplate == null) {
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("projekt_user");
                dataSource.setPassword("paz1cisgreat");
                dataSource.setDatabaseName("projekt");
                jdbcTemplate = new JdbcTemplate(dataSource);
            }

            Zakaznik zakaznik = zoznamTableView.getSelectionModel().getSelectedItem();
            String sql = "SET SQL_SAFE_UPDATES = 0";
            jdbcTemplate.update(sql);
            sql = "DELETE FROM zakaznici WHERE id = " + zakaznik.getId();
            jdbcTemplate.update(sql);
            int selectedIndex = zoznamTableView.getSelectionModel().getSelectedIndex();
            zoznamTableView.getItems().remove(selectedIndex);
        });
      
        
        pozrietZakaznikaButton.setOnAction(eh -> {
            id = zoznamTableView.getSelectionModel().getSelectedItem().getId();
            PozrietZakaznikaController controller = new PozrietZakaznikaController(id);
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PozrietZakaznikaScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("AGRO Metlife - Nákupný košík zákazníka");
                stage.show();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });
        
        refreshButton.setOnAction(eh -> {
            refreshButton.getScene().getWindow().hide();
            ZoznamZakaznikovControllerAdmin controller = new ZoznamZakaznikovControllerAdmin();
             try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("ZoznamZakaznikovScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("AGRO Metlife - Zákazníci");
                // pozrietZakaznikaButton.getScene().getWindow().hide();
                stage.show();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        });

    }

}
