package sk.upjs.ics.projekt;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ZahradnaSluzbaDaoTest {

    private ZahradnaSluzbaDao dao;

    public ZahradnaSluzbaDaoTest() {
        dao = DaoFactory.INSTANCE.getZahradnaSluzbaDao();
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

   
   
    public void saveTest() throws DaoException {
        ZahradnaSluzba zahradnaSluzba = new ZahradnaSluzba();
        int velkost = dao.getAll().size();
        zahradnaSluzba.setNazov("Kopanie");
        zahradnaSluzba.setRocneObdobie("Leto");
        zahradnaSluzba.setPopis("Kopeme.");
        zahradnaSluzba.setCena(5.3);
        dao.save(zahradnaSluzba);        
        assertEquals(velkost + 1 , dao.getAll().size());
        dao.deleteById(zahradnaSluzba.getId());
    }

    
    public void testGetAll() {
        ZahradnaSluzbaDao zahradnaSluzbaDao = DaoFactory.INSTANCE.getZahradnaSluzbaDao();
        List<ZahradnaSluzba> all = zahradnaSluzbaDao.getAll();
        assertTrue(all.size() > 0);
    }
    
    
    public void deleteByIdTest() {
        ZahradnaSluzba r = dao.getAll().get(0);
        boolean pravda = dao.deleteById(r.getId());       
        assertTrue(pravda);
       
    }

}
