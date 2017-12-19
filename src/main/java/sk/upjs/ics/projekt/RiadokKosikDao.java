package sk.upjs.ics.projekt;

import java.util.List;


public interface RiadokKosikDao {
    
    void save(RiadokKosik riadokKosik) throws DaoException;
    
    List<RiadokKosik> getAll();
    
    boolean deleteById(Long id);
}