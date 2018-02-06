package sk.upjs.ics.projekt;

import java.util.List;

public interface ReklamnaSluzbaDao {

    //CREATE
    void save(ReklamnaSluzba reklamnaSluzba) throws DaoException;

    //READ
    List<ReklamnaSluzba> getAll();

    //DELETE
    boolean deleteById(Long id);
}
