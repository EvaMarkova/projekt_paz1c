package sk.upjs.ics.projekt;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum DaoFactory {

    INSTANCE;

    private ReklamnaSluzbaDao reklamnaSluzbaDao;
    private ZahradnaSluzbaDao zahradnaSluzbaDao;
    private RiadokKosikDao riadokKosikDao;
    private ZakaznikDao zakaznikDao;
    private JdbcTemplate jdbcTemplate;

    public ReklamnaSluzbaDao getReklamnaSluzbaDao() {
        return new MysqlReklamnaSluzbaDao(getJDBCTemplate());
    }
    
    public ZahradnaSluzbaDao getZahradnaSluzbaDao() {
        return new MysqlZahradnaSluzbaDao(getJDBCTemplate());
    }
    
    public RiadokKosikDao getRiadokKosikDao() {
        return new MysqlRiadokKosikDao(getJDBCTemplate());
    }
    
    public ZakaznikDao getZakaznikDao() {
        return new MysqlZakaznikDao(getJDBCTemplate());
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