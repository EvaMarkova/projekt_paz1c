package sk.upjs.ics.projekt;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PozrietZakaznikaController {

    private Long id;
    private JdbcTemplate jdbcTemplate;

    @FXML
    private TableView<Polozka> zoznamTableView;

    @FXML
    private TableColumn<Polozka, String> nazovCol;

    @FXML
    private TableColumn<Polozka, Integer> pocetCol;

    @FXML
    private TableColumn<Polozka, Double> cenaCol;

    @FXML
    private Label zakaznikMenoLabel;

    @FXML
    private Button vydaneButton;

    @FXML
    private Button zaplateneButton;

    @FXML
    private Button resetButton;

    public PozrietZakaznikaController(Long id) {
        this.id = id;
    }

    @FXML
    void initialize() {

        if (jdbcTemplate == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("projekt_user");
            dataSource.setPassword("paz1cisgreat");
            dataSource.setDatabaseName("projekt");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        String sql = "select concat_ws(\" \",meno, priezvisko) from zakaznici where id = " + id;
        String meno = jdbcTemplate.queryForObject(sql, String.class);
        zakaznikMenoLabel.setText(meno);
        zakaznikMenoLabel.setAlignment(Pos.CENTER);

        nazovCol.setCellValueFactory(new PropertyValueFactory<>("nazov"));
        pocetCol.setCellValueFactory(new PropertyValueFactory<>("pocet"));
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));

        sql = "select k.nazov,k.pocet, k.cena from zakaznici z join "
                + "kosik k on (z.id = k.zakaznici_id) where z.id = " + id;
        List<Polozka> polozky = jdbcTemplate.query(sql, new RowMapper<Polozka>() {
            @Override
            public Polozka mapRow(ResultSet rs, int i) throws SQLException {
                Polozka polozka = new Polozka();
                polozka.setNazov(rs.getString("nazov"));
                polozka.setPocet(rs.getInt("pocet"));
                polozka.setCena(rs.getDouble("cena"));
                return polozka;
            }
        });

        ObservableList<Polozka> polozkyy = FXCollections.observableArrayList(polozky);
        zoznamTableView.setItems(polozkyy);

        vydaneButton.setOnAction(eh -> {
            String SQL = "update zakaznici set vydany_kosik = 'áno' where id =" + id;
            jdbcTemplate.update(SQL);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AGRO Metlife - Informácia");
            alert.setHeaderText(null);
            alert.setContentText("Košík pre daného zákazníka bol vydaný.");
            alert.showAndWait();

        });

        zaplateneButton.setOnAction(eh -> {
            String SQL = "update zakaznici set zaplateny_kosik = 'áno' where id =" + id;
            jdbcTemplate.update(SQL);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AGRO Metlife - Informácia");
            alert.setHeaderText(null);
            alert.setContentText("Košík pre daného zákazníka bol zaplatený.");
            alert.showAndWait();

        });

        resetButton.setOnAction(eh -> {
            String SQL = "update zakaznici set vydany_kosik = 'nie' where id =" + id;
            jdbcTemplate.update(SQL);
            SQL = "update zakaznici set zaplateny_kosik = 'nie' where id =" + id;
            jdbcTemplate.update(SQL);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("AGRO Metlife - Informácia");
            alert.setHeaderText(null);
            alert.setContentText("Hodnoty sú nastavené na defaultné.");
            alert.showAndWait();

        });

    }
}
