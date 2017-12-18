package sk.upjs.ics.projekt;

import java.util.List;

public interface ZahradnaSluzbaDao {
    
    void save(ZahradnaSluzba zahradnaSluzba) throws DaoException;
    
    List<ZahradnaSluzba> getAll();
    
    boolean deleteById(Long id);
}