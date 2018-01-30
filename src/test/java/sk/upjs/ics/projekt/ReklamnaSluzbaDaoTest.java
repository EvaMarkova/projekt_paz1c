package sk.upjs.ics.projekt;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ReklamnaSluzbaDaoTest {

    private ReklamnaSluzbaDao dao;

    public ReklamnaSluzbaDaoTest() {
        dao = DaoFactory.INSTANCE.getReklamnaSluzbaDao();
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

   
    @Test
    public void saveTest() throws DaoException {
        ReklamnaSluzba reklamnaSluzba = new ReklamnaSluzba();
        int velkost = dao.getAll().size();
        reklamnaSluzba.setNazov("Tlač");
        reklamnaSluzba.setPopis("Tlačíme");
        reklamnaSluzba.setCena(0.5);
        dao.save(reklamnaSluzba);        
        assertEquals(velkost + 1 , dao.getAll().size());
        dao.deleteById(reklamnaSluzba.getId());
    }

   @Test
    public void testGetAll() {
        ReklamnaSluzbaDao reklamnaSluzbaDao = DaoFactory.INSTANCE.getReklamnaSluzbaDao();
        List<ReklamnaSluzba> all = reklamnaSluzbaDao.getAll();
        assertTrue(all.size() > 0);
    }

    @Test
    public void deleteByIdTest() {
        ReklamnaSluzba r = dao.getAll().get(0);
        boolean pravda = dao.deleteById(r.getId());       
        assertTrue(pravda);
    }

}
