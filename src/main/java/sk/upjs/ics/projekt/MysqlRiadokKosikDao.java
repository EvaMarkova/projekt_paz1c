package sk.upjs.ics.projekt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlRiadokKosikDao implements RiadokKosikDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlRiadokKosikDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RiadokKosik> getAll() {
        String sql = "SELECT id, nazov, pocet, cena FROM kosik";
        List<RiadokKosik> riadkyKosika = jdbcTemplate.query(sql, new RowMapper<RiadokKosik>() {
            @Override
            public RiadokKosik mapRow(ResultSet rs, int i) throws SQLException {
                RiadokKosik riadokKosik = new RiadokKosik();
                riadokKosik.setId(rs.getLong("id"));
                riadokKosik.setNazov(rs.getString("nazov"));
                riadokKosik.setPocet(rs.getInt("pocet"));
                riadokKosik.setCena(rs.getDouble("cena"));
                return riadokKosik;
            }            
        });
        return riadkyKosika;
    }

    @Override
    public boolean deleteById(Long id) {
         String sql = "DELETE FROM kosik WHERE id = " + id;
        try {
            int zmazanych = jdbcTemplate.update(sql);
            return zmazanych == 1;
        } catch (Exception e) {
            return false;
        }
    
    }

    @Override
    public void save(RiadokKosik riadokKosik) throws DaoException {
        if (riadokKosik == null) {
            return;
        }       
        if (riadokKosik.getId() == null) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("kosik");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("nazov","pocet","cena");           
                       
        } else {
             // UPDATE
            String sql = "UPDATE kosik SET nazov = ?,pocet= ?, cena = ?  WHERE id = " + riadokKosik.getId();
            jdbcTemplate.update(sql, riadokKosik.getNazov(),riadokKosik.getPocet(),riadokKosik.getCena());
        }    }

}
