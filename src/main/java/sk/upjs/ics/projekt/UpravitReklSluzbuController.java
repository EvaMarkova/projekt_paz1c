
package sk.upjs.ics.projekt;

import com.mysql.cj.jdbc.MysqlDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.jdbc.core.JdbcTemplate;


class UpravitReklSluzbuController {
    private JdbcTemplate jdbcTemplate;

    
     
//    public UpravitReklSluzbuController(Long id){
//        ZoznamReklSluziebAdminController tototu = new ZoznamReklSluziebAdminController();
//        cislo = tototu.getId();
//    }
    
    
    @FXML
    private Button homeButton;

    @FXML
    private TextField nazovSluzbyTextField;

    @FXML
    private TextField popisSluzbyTextField;

    @FXML
    private Button upravitSluzbuButton;

    @FXML
    private TextField cenaTextField;

    @FXML
    void initialize() {
        
        if (jdbcTemplate == null) {
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser("projekt_user");
                dataSource.setPassword("paz1cisgreat");
                dataSource.setDatabaseName("projekt");
                jdbcTemplate = new JdbcTemplate(dataSource);
            }
//           System.out.println(cislo);
 
//     System.out.println(id); 
//            String sql = "SELECT nazov FROM reklamne_sluzby WHERE id = " + id;
//            String nazov = jdbcTemplate.queryForObject(sql, String.class);
//            sql = "SELECT popis FROM reklamne_sluzby WHERE id = " + id;
//            String popis = jdbcTemplate.queryForObject(sql, String.class);
//            sql = "SELECT cena FROM reklamne_sluzby WHERE id = " + id;
//            String cena = jdbcTemplate.queryForObject(sql, String.class);
            
//            nazovSluzbyTextField.setText(nazov);
//            popisSluzbyTextField.setText(popis);
//            cenaTextField.setText(cena);

    }
    
    
}
