package sk.upjs.ics.projekt;

import com.mysql.cj.jdbc.MysqlDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.jdbc.core.JdbcTemplate;

public class RegistracnyFormularController {

    private JdbcTemplate jdbcTemplate;
    private Double cena;
    private String poctySluzieb;

    public RegistracnyFormularController(Double cena, String poctySluzieb) {
        this.cena = cena;
        this.poctySluzieb = poctySluzieb;
    }

    @FXML
    private TextField menoTextField;

    @FXML
    private TextField priezviskoTextField;

    @FXML
    private TextField adresaTextField;

    @FXML
    private TextField cisloTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button objednatButton;

    @FXML
    void initialize() {
        objednatButton.setOnAction(eh -> {
            String meno = menoTextField.getText();
            String priezvisko = priezviskoTextField.getText();
            String adresa = adresaTextField.getText();
            String cislo = cisloTextField.getText();
            String email = emailTextField.getText();

            if (jdbcTemplate == null) {
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("projekt_user");
                dataSource.setPassword("paz1cisgreat");
                dataSource.setDatabaseName("projekt");
                jdbcTemplate = new JdbcTemplate(dataSource);
            }
            RiadokKosikFxModel model = new RiadokKosikFxModel();
            String sql = "INSERT INTO zakaznici(meno,priezvisko,adresa,cislo,email) VALUES(?,?,?,?,?)";
            jdbcTemplate.update(sql, meno, priezvisko, adresa, cislo, email);
            sql = "INSERT INTO kosik(nazov, pocet, cena, zakaznici_id) SELECT nazov, pocet,"
                    + " cena,(SELECT id FROM zakaznici ORDER BY id DESC LIMIT 1) FROM polozky_kosika;";
            jdbcTemplate.update(sql);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AGRO Metlife - Objednávka");
            alert.setHeaderText(null);
            alert.setContentText("Vaša objednávka sa spracuváva.");
            objednatButton.getScene().getWindow().hide();
            alert.showAndWait();
            sql = "SET SQL_SAFE_UPDATES = 0";
            jdbcTemplate.update(sql);
            sql = "DELETE FROM polozky_kosika";
            jdbcTemplate.update(sql);
        });
    }
}
