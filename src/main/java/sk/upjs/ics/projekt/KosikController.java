package sk.upjs.ics.projekt;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.jdbc.core.JdbcTemplate;

public class KosikController {

    private JdbcTemplate jdbcTemplate;

    private final RiadokKosikFxModel riadkyKosikModel = new RiadokKosikFxModel();

    public KosikController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public KosikController() {

    }

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
    void initialize() {

        potvrditNakupButton.setOnAction(eh -> {
            try {

                RegistracnyFormularController controller = new RegistracnyFormularController();
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("RegistracnyFormularScene.fxml"));
                loader.setController(controller);

                Parent parentPane = loader.load();
                Scene scene = new Scene(parentPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("AGRO Metlife - formulár");
                stage.initModality(Modality.APPLICATION_MODAL);
                potvrditNakupButton.getScene().getWindow().hide();
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(DruhSluzbySceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

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
            sql = "DELETE FROM kosik WHERE nazov = '" + riadokKosik.getNazov() + "'";
            jdbcTemplate.update(sql);            
            int selectedIndex = kosikTableView.getSelectionModel().getSelectedIndex();            
            kosikTableView.getItems().remove(selectedIndex);    
            sql = "SELECT SUM(cena) FROM kosik"; 
            double vyslednaCena = jdbcTemplate.queryForObject(sql, Double.class);
            vyslednaCenaTextField.setText("Výsledna cena je " + vyslednaCena + "€");
        });
        
            if (jdbcTemplate == null) {
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("projekt_user");
                dataSource.setPassword("paz1cisgreat");
                dataSource.setDatabaseName("projekt");
                jdbcTemplate = new JdbcTemplate(dataSource);
            }
            String sql = "SELECT SUM(cena) FROM kosik"; 
            double vyslednaCena = jdbcTemplate.queryForObject(sql, Double.class);
            vyslednaCenaTextField.setText("Výsledna cena je " + vyslednaCena + "€");
            
        
        
        
        
        
    }

}
