package sk.upjs.ics.projekt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlZakaznikDao implements ZakaznikDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlZakaznikDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Zakaznik> getAll() {

        String sql = "SELECT id, meno, priezvisko, adresa, cislo, email, vydany_kosik, zaplateny_kosik FROM zakaznici";
        List<Zakaznik> zakaznici = jdbcTemplate.query(sql, new RowMapper<Zakaznik>() {
            @Override
            public Zakaznik mapRow(ResultSet rs, int i) throws SQLException {
                Zakaznik zakaznik = new Zakaznik();
                zakaznik.setId(rs.getLong("id"));
                zakaznik.setMeno(rs.getString("meno"));
                zakaznik.setPriezvisko(rs.getString("priezvisko"));
                zakaznik.setAdresa(rs.getString("adresa"));
                zakaznik.setCislo(rs.getString("cislo"));
                zakaznik.setEmail(rs.getString("email"));
                zakaznik.setVydanyKosik(rs.getString("vydany_kosik"));
                zakaznik.setZaplatenyKosik(rs.getString("zaplateny_kosik"));
//                zakaznik.setVybraneSluzby(rs.getString("vybrate_sluzby"));
//                zakaznik.setPoctySluzieb(rs.getString("pocty_sluzieb"));
//                zakaznik.setVyslednaCena(rs.getDouble("vysledna_cena"));
                return zakaznik;
            }
        });
        return zakaznici;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM zakaznici WHERE id = " + id;
        try {
            int zmazanych = jdbcTemplate.update(sql);
            return zmazanych == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void save(Zakaznik zakaznik) throws DaoException {
        if (zakaznik == null) {
            return;
        }
        if (zakaznik.getId() == null) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("zakaznici");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("meno", "priezvisko", "adresa", "cislo", "email");
            String sql = "INSERT zakaznici(meno,priezvisko,adresa,cislo,email,vydany_kosik,zaplateny_kosik) VALUES (?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, zakaznik.getMeno(), zakaznik.getPriezvisko(), zakaznik.getCislo(), zakaznik.getEmail(),
                    zakaznik.getVydanyKosik(), zakaznik.getZaplatenyKosik());
        } else {
            // UPDATE
            String sql = "UPDATE zakaznici SET meno = ?, priezvisko = ?, adresa = ?, cislo = ?, email=? WHERE id = " + zakaznik.getId();
            jdbcTemplate.update(sql, zakaznik.getMeno(), zakaznik.getPriezvisko(), zakaznik.getAdresa(), zakaznik.getCislo(), zakaznik.getEmail());
        }

    }
}
