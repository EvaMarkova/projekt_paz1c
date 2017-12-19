package sk.upjs.ics.projekt;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class AdminSceneController {

    @FXML
    private PasswordField hesloPasswordField;

    @FXML
    private TextField loginTextField;

    @FXML
    private Button prihlasitSaButton;

    private String hash(String heslo, String sol) {
        return BCrypt.hashpw(heslo,sol);
    }

    @FXML
    void initialize() {

        prihlasitSaButton.setOnAction(eh -> {
            String sol = BCrypt.gensalt();
            if ((loginTextField.getText().equals(adminLogin)) &&
                    hash(hesloPasswordField.getText(),sol).equals(hash(adminHeslo,sol))) {
                DruhSluzbyAdminSceneController controller
                        = new DruhSluzbyAdminSceneController();
                prihlasitSaButton.getScene().getWindow().hide();
                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("DruhSluzbyAdminScene.fxml"));
                    loader.setController(controller);

                    Parent parentPane = loader.load();
                    Scene scene = new Scene(parentPane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("AGRO Metlife - Výber");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException iOException) {
                    iOException.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("AGRO Metlife - ERROR");
                alert.setHeaderText("Chyba");
                alert.setContentText("Nesprávny login alebo heslo.");
                alert.showAndWait();
            }
        });

    }
    
    
    private final String adminLogin = "admin";
    private final String adminHeslo = "macka";

}
