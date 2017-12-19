package sk.upjs.ics.projekt;

import java.util.List;
import sk.upjs.ics.projekt.DaoException;
import sk.upjs.ics.projekt.ZahradnaSluzba;

public interface ZahradnaSluzbaDao {
    
    void save(ZahradnaSluzba zahradnaSluzba) throws DaoException;
    
    List<ZahradnaSluzba> getAll();
    
    boolean deleteById(Long id);
}