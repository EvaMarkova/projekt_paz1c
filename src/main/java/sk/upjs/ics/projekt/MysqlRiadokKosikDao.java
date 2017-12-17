package sk.upjs.ics.projekt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlRiadokKosikDao implements RiadokKosikDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlRiadokKosikDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RiadokKosik> getAll() {
        String sql = "SELECT nazov, pocet, cena FROM kosik";
        List<RiadokKosik> riadkyKosika = jdbcTemplate.query(sql, new RowMapper<RiadokKosik>() {
            @Override
            public RiadokKosik mapRow(ResultSet rs, int i) throws SQLException {
                RiadokKosik riadokKosik = new RiadokKosik();
                riadokKosik.setNazov(rs.getString("nazov"));
                riadokKosik.setPocet(rs.getInt("pocet"));
                riadokKosik.setCena(rs.getDouble("cena"));
                return riadokKosik;
            }            
        });
        return riadkyKosika;
    }

}
