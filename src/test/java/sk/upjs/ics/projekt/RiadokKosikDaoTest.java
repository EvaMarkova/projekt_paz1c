package sk.upjs.ics.projekt;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class RiadokKosikDaoTest {

    private RiadokKosikDao dao;

    public RiadokKosikDaoTest() {
        dao = DaoFactory.INSTANCE.getRiadokKosikDao();
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
        RiadokKosik riadokKosik = new RiadokKosik();
        int velkost = dao.getAll().size();
        riadokKosik.setNazov("Sluzba");
        riadokKosik.setPocet(4);
        riadokKosik.setCena(3.4);
        dao.save(riadokKosik);
        
        assertEquals(velkost + 1 , dao.getAll().size());
    }

    @Test
    public void testGetAll() {
        RiadokKosikDao riadokKosikDao = DaoFactory.INSTANCE.getRiadokKosikDao();
        List<RiadokKosik> all = riadokKosikDao.getAll();
        assertTrue(all.size() >= 0);
    }

    @Test
    public void deleteByIdTest() {
        RiadokKosik r = dao.getAll().get(0);
        boolean pravda = dao.deleteById(r.getId());       
        assertTrue(pravda);
    }

}
