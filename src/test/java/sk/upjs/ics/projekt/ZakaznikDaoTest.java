package sk.upjs.ics.projekt;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ZakaznikDaoTest {

    private ZakaznikDao dao;

    public ZakaznikDaoTest() {
        dao = DaoFactory.INSTANCE.getZakaznikDao();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    //@Test
    public void saveTest() throws DaoException {
        Zakaznik zakaznik = new Zakaznik();
        int velkost = dao.getAll().size();
        zakaznik.setMeno("Johny");
        zakaznik.setPriezvisko("English");
        zakaznik.setAdresa("London 23.");
        zakaznik.setCislo("7898");
        zakaznik.setEmail("johnyE@jmail.com");
        zakaznik.setVybraneSluzby("[sluzba1, sluzba2]");
        zakaznik.setPoctySluzieb("[3,2]");
        zakaznik.setVyslednaCena(4.9);
        dao.save(zakaznik);
        assertEquals(velkost + 1, dao.getAll().size());
    }

    //@Test
    public void testGetAll() {
        ZakaznikDao zakaznikDao = DaoFactory.INSTANCE.getZakaznikDao();
        List<Zakaznik> all = zakaznikDao.getAll();
        assertTrue(all.size() >= 0);
    }

    @Test
    public void deleteByIdTest() {
        if (dao.getAll().size() > 0) {
            Zakaznik zakaznik = dao.getAll().get(0);
            boolean pravda = dao.deleteById(zakaznik.getId());
            assertTrue(pravda);
        } else {
            assertTrue(true);
        }
    }

}
