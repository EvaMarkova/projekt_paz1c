package sk.upjs.ics.projekt;

import java.util.List;

public interface ZakaznikDao {
    
    void save(Zakaznik zakaznik) throws DaoException;
    
    List<Zakaznik> getAll();
    
    boolean deleteById(Long id);
}