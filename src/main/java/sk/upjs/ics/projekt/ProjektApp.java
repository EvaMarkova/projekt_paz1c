package sk.upjs.ics.projekt;

import com.mysql.cj.jdbc.MysqlDataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProjektApp extends Application {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void start(Stage stage) throws Exception {
        if (jdbcTemplate == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("projekt_user");
            dataSource.setPassword("paz1cisgreat");
            dataSource.setDatabaseName("projekt");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        String sql = "SET SQL_SAFE_UPDATES = 0";
        jdbcTemplate.update(sql);
        sql = "DELETE FROM polozky_kosika";
        jdbcTemplate.update(sql);

        MainSceneController controller = new MainSceneController();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("MainScene.fxml"));
        loader.setController(controller);

        Parent parentPane = loader.load();
        Scene scene = new Scene(parentPane);

        stage.setScene(scene);
        stage.setTitle("AGRO Metlife - Reklamné a záhradné služby");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
