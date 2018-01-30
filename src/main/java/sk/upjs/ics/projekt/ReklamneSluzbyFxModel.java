package sk.upjs.ics.projekt;

import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReklamneSluzbyFxModel {
    
    private ObservableList<ReklamnaSluzba> reklamneSluzby;
    private ObjectProperty<ReklamnaSluzba> vybrataReklamnaSluzba = new SimpleObjectProperty<>(null);
    private Long id;
    private StringProperty nazovReklamnejSluzby = new SimpleStringProperty();
    private StringProperty popisReklamnejSluzby = new SimpleStringProperty();
    private double cenaReklamnejSluzby;

    public ReklamneSluzbyFxModel() {
        ReklamnaSluzbaDao reklamnaSluzbaDao = DaoFactory.INSTANCE.getReklamnaSluzbaDao();
        List<ReklamnaSluzba> reklamneSluzby = reklamnaSluzbaDao.getAll();
        this.reklamneSluzby = FXCollections.observableArrayList(reklamneSluzby);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCenaReklamnejSluzby() {
        return cenaReklamnejSluzby;
    }

    public void setCenaReklamnejSluzby(double cenaReklamnejSluzby) {
        this.cenaReklamnejSluzby = cenaReklamnejSluzby;
    }    

    public String getNazovReklamnejSluzby() {
        return nazovReklamnejSluzby.get();
    }

    public void setNazovReklamnejSluzby(String nazovReklamnejSluzby) {
        this.nazovReklamnejSluzby.set(nazovReklamnejSluzby);
    }

    public StringProperty nazovReklamnejSluzbyProperty() {
        return nazovReklamnejSluzby;
    }

    public String getPopisReklamnejSluzby() {
        return popisReklamnejSluzby.get();
    }

    public void setPopisReklamnejSluzby(String popisReklamnejSluzby) {
        this.popisReklamnejSluzby.set(popisReklamnejSluzby);
    }

    public StringProperty popisReklamnejSluzbyProperty() {
        return popisReklamnejSluzby;
    }

    public ReklamnaSluzba getReklamnaSluzba() {
        ReklamnaSluzba reklamnaSluzba = new ReklamnaSluzba();
        reklamnaSluzba.setId(id);
        reklamnaSluzba.setNazov(getNazovReklamnejSluzby());
        reklamnaSluzba.setPopis(getPopisReklamnejSluzby());
        reklamnaSluzba.setCena(getCenaReklamnejSluzby());
        return reklamnaSluzba;
    }

    public void setReklamnaSluzba(ReklamnaSluzba reklamnaSluzba) {
        id = reklamnaSluzba.getId();
        setNazovReklamnejSluzby(reklamnaSluzba.getNazov());
        setPopisReklamnejSluzby(reklamnaSluzba.getPopis());
        cenaReklamnejSluzby = reklamnaSluzba.getCena();
    }

    public void newReklamnaSluzba() {
        id = null;
        setNazovReklamnejSluzby(null);
        setPopisReklamnejSluzby(null);
        cenaReklamnejSluzby = 0;

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
