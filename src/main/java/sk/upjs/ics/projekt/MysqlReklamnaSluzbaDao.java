package sk.upjs.ics.projekt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlReklamnaSluzbaDao implements ReklamnaSluzbaDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public MysqlReklamnaSluzbaDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<ReklamnaSluzba> getAll() {
        String sql = "SELECT id, nazov, popis, cena FROM reklamne_sluzby";
        List<ReklamnaSluzba> reklamneSluzby = jdbcTemplate.query(sql,new RowMapper<ReklamnaSluzba>() {
            @Override
            public ReklamnaSluzba mapRow(ResultSet rs, int i) throws SQLException {
                ReklamnaSluzba reklamnaSluzba = new ReklamnaSluzba();
                reklamnaSluzba.setId(rs.getLong("id"));
                reklamnaSluzba.setNazov(rs.getString("nazov"));
                reklamnaSluzba.setPopis(rs.getString("popis"));
                reklamnaSluzba.setCena(rs.getString("cena"));
                return reklamnaSluzba;                
            }
        });
               return reklamneSluzby;
    }
    
}
