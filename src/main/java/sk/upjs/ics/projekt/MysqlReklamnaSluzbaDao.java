package sk.upjs.ics.projekt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.ics.projekt.DaoException;
import sk.upjs.ics.projekt.ReklamnaSluzba;

public class MysqlReklamnaSluzbaDao implements ReklamnaSluzbaDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public MysqlReklamnaSluzbaDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void save(ReklamnaSluzba reklamnaSluzba) throws DaoException {
        if (reklamnaSluzba == null) {
            return;
        }       
        if (reklamnaSluzba.getId() == null) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("reklamne_sluzby");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("nazov","popis","cena");           
            String sql = "INSERT reklamne_sluzby(nazov,popis,cena) VALUES (?,?,?)";
            jdbcTemplate.update(sql,reklamnaSluzba.getNazov(),reklamnaSluzba.getPopis(),reklamnaSluzba.getCena());
        } else {
             // UPDATE
            String sql = "UPDATE reklamne_sluzby SET nazov = ?, popis = ?, cena = ?  WHERE id = " + reklamnaSluzba.getId();
            jdbcTemplate.update(sql, reklamnaSluzba.getNazov(), reklamnaSluzba.getPopis(),reklamnaSluzba.getCena());
        }
        
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
                reklamnaSluzba.setCena(rs.getDouble("cena"));
                return reklamnaSluzba;                
            }
        });
               return reklamneSluzby;
    }

    @Override
    public boolean deleteById(Long id) {
     String sql = "DELETE FROM reklamne_sluzby WHERE id = " + id;
        try {
            int zmazanych = jdbcTemplate.update(sql);
            return zmazanych == 1;
        } catch (Exception e) {
            return false;
        }
    }

    
}
