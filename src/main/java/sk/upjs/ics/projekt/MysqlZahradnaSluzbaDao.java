package sk.upjs.ics.projekt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlZahradnaSluzbaDao implements ZahradnaSluzbaDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public MysqlZahradnaSluzbaDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<ZahradnaSluzba> getAll() {
        String sql = "SELECT id, rocne_obdobie, nazov, popis, cena FROM zahradne_sluzby";
        List<ZahradnaSluzba> zahradneSluzby = jdbcTemplate.query(sql,new RowMapper<ZahradnaSluzba>() {
            @Override
            public ZahradnaSluzba mapRow(ResultSet rs, int i) throws SQLException {
                ZahradnaSluzba zahradnaSluzba = new ZahradnaSluzba();
                zahradnaSluzba.setId(rs.getLong("id"));
                zahradnaSluzba.setRocneObdobie(rs.getString("rocne_obdobie"));
                zahradnaSluzba.setNazov(rs.getString("nazov"));
                zahradnaSluzba.setPopis(rs.getString("popis"));
                zahradnaSluzba.setCena(rs.getDouble("cena"));
                return zahradnaSluzba;                
            }
        });
        return zahradneSluzby;
    }
    
}