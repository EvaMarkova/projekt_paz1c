package sk.upjs.ics.projekt;

import java.util.List;
import sk.upjs.ics.projekt.DaoException;
import sk.upjs.ics.projekt.Zakaznik;

public interface ZakaznikDao {
    
    void save(Zakaznik zakaznik) throws DaoException;
    
    List<Zakaznik> getAll();
    
    boolean deleteById(Long id);
}