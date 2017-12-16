package sk.upjs.ics.projekt;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.File;
import org.springframework.jdbc.core.JdbcTemplate;

public enum DaoFactory {

    INSTANCE;

    private ReklamnaSluzbaDao reklamnaSluzbaDao;
    private ZahradnaSluzbaDao zahradnaSluzbaDao;
    private JdbcTemplate jdbcTemplate;

    public ReklamnaSluzbaDao getReklamnaSluzbaDao() {
        return new MysqlReklamnaSluzbaDao(getJDBCTemplate());
    }
    
    public ZahradnaSluzbaDao getZahradnaSluzbaDao() {
        return new MysqlZahradnaSluzbaDao(getJDBCTemplate());
    }

    
    
    private JdbcTemplate getJDBCTemplate() {
        if (jdbcTemplate == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("projekt_user");
            dataSource.setPassword("paz1cisgreat");
            dataSource.setDatabaseName("projekt");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }
    
    

   
    
}