package sk.upjs.ics.projekt;

import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReklamneSluzbyFxModel {
     private ObservableList<ReklamnaSluzba> reklamneSluzby;
     private ObjectProperty<ReklamnaSluzba> vybrataReklamnaSluzba = new SimpleObjectProperty<>(null);      
     
     public ReklamneSluzbyFxModel() {
        ReklamnaSluzbaDao reklamnaSluzbaDao = DaoFactory.INSTANCE.getReklamnaSluzbaDao();
        List<ReklamnaSluzba> reklamneSluzby = reklamnaSluzbaDao.getAll();
        this.reklamneSluzby = FXCollections.observableArrayList(reklamneSluzby);
     }
     
     public ObservableList<ReklamnaSluzba> getReklamneSluzby() {
        return reklamneSluzby;
    }

     public void setVybrataReklamnaSluzba(ReklamnaSluzba reklamnaSluzba) {
        vybrataReklamnaSluzba.set(reklamnaSluzba);
    }
    
    public ObjectProperty<ReklamnaSluzba> vybrataReklamnaSluzbaProperty() {
        return vybrataReklamnaSluzba;
    }

    public ReklamnaSluzba getVybrataReklamnaSluzba() {
        return vybrataReklamnaSluzba.get();
    }

}

