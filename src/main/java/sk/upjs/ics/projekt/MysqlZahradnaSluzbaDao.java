package sk.upjs.ics.projekt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.ics.projekt.DaoException;
import sk.upjs.ics.projekt.ZahradnaSluzba;

public class MysqlZahradnaSluzbaDao implements ZahradnaSluzbaDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlZahradnaSluzbaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ZahradnaSluzba> getAll() {
        String sql = "SELECT id, rocne_obdobie, nazov, popis, cena FROM zahradne_sluzby";
        List<ZahradnaSluzba> zahradneSluzby = jdbcTemplate.query(sql, new RowMapper<ZahradnaSluzba>() {
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

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM zahradne_sluzby WHERE id = " + id;
        try {
            int zmazanych = jdbcTemplate.update(sql);
            return zmazanych == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void save(ZahradnaSluzba zahradnaSluzba) throws DaoException {
        if (zahradnaSluzba == null) {
            return;
        }
        if (zahradnaSluzba.getId() == null) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("zahradne_sluzby");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("nazov", "rocne_obdobie", "popis", "cena");
            String sql = "INSERT zahradne_sluzby(nazov,rocne_obdobie,popis,cena) VALUES (?,?,?,?)";
            jdbcTemplate.update(sql,zahradnaSluzba.getNazov(),zahradnaSluzba.getRocneObdobie(),zahradnaSluzba.getPopis(),zahradnaSluzba.getCena());
        } else {
            // UPDATE
            String sql = "UPDATE zahradne_sluzby SET nazov = ?,rocne_obdobie=?, popis = ?, cena = ?  WHERE id = " + zahradnaSluzba.getId();
            jdbcTemplate.update(sql, zahradnaSluzba.getNazov(), zahradnaSluzba.getRocneObdobie(), zahradnaSluzba.getPopis(), zahradnaSluzba.getCena());
        }
    }

}
