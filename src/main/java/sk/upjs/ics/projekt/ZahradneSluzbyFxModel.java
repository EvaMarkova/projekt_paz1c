package sk.upjs.ics.projekt;

import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ZahradneSluzbyFxModel {

    private ObservableList<ZahradnaSluzba> zahradneSluzby;
    private ObjectProperty<ZahradnaSluzba> vybrataZahradnaSluzba = new SimpleObjectProperty<>(null);
    private Long id;
    private StringProperty nazovZahradnejSluzby = new SimpleStringProperty();
    private StringProperty popisZahradnejSluzby = new SimpleStringProperty();
    private StringProperty cenaZahradnejSluzby = new SimpleStringProperty();

    public ZahradneSluzbyFxModel() {
        ZahradnaSluzbaDao zahradnaSluzbaDao = DaoFactory.INSTANCE.getZahradnaSluzbaDao();
        List<ZahradnaSluzba> zahradneSluzby = zahradnaSluzbaDao.getAll();
        this.zahradneSluzby = FXCollections.observableArrayList(zahradneSluzby);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazovZahradnejSluzby() {
        return nazovZahradnejSluzby.get();
    }

    public void setNazovZahradnejSluzby(String nazovZahradnejSluzby) {
        this.nazovZahradnejSluzby.set(nazovZahradnejSluzby);
    }

    public StringProperty nazovZahradnejSluzbyProperty() {
        return nazovZahradnejSluzby;
    }

    public String getPopisZahradnejSluzby() {
        return popisZahradnejSluzby.get();
    }

    public void setPopisZahradnejSluzby(String popisZahradnejSluzby) {
        this.popisZahradnejSluzby.set(popisZahradnejSluzby);
    }

    public StringProperty popisZahradnejSluzbyProperty() {
        return popisZahradnejSluzby;
    }

    public String getCenaZahradnejSluzby() {
        return cenaZahradnejSluzby.get();
    }

    public void setCenaZahradnejSluzby(String cenaZahradnejSluzby) {
        this.cenaZahradnejSluzby.set(cenaZahradnejSluzby);
    }

    public StringProperty cenaZahradnejSluzbyProperty() {
        return cenaZahradnejSluzby;
    }

    public ZahradnaSluzba getZahradnaSluzba() {
        ZahradnaSluzba zahradnaSluzba = new ZahradnaSluzba();
        zahradnaSluzba.setId(id);
        zahradnaSluzba.setNazov(getNazovZahradnejSluzby());
        zahradnaSluzba.setPopis(getPopisZahradnejSluzby());
        zahradnaSluzba.setCena(getCenaZahradnejSluzby());
        return zahradnaSluzba;
    }

    public void setZahradnaSluzba(ZahradnaSluzba zahradnaSluzba) {
        id = zahradnaSluzba.getId();
        setNazovZahradnejSluzby(zahradnaSluzba.getNazov());
        setPopisZahradnejSluzby(zahradnaSluzba.getPopis());
        setCenaZahradnejSluzby(zahradnaSluzba.getCena());
    }

    public void newZahradnaSluzba() {
        id = null;
        setNazovZahradnejSluzby(null);
        setPopisZahradnejSluzby(null);
        setCenaZahradnejSluzby(null);

    }

    public ObservableList<ZahradnaSluzba> getZahradneSluzby() {
        return zahradneSluzby;
    }
    // ____________________________________________________

    public void setVybrataZahradnaSluzba(ZahradnaSluzba zahradnaSluzba) {
        vybrataZahradnaSluzba.set(zahradnaSluzba);
    }

    public ObjectProperty<ZahradnaSluzba> vybrataZahradnaSluzbaProperty() {
        return vybrataZahradnaSluzba;
    }

    public ZahradnaSluzba getVybrataZahradnaSluzba() {
        return vybrataZahradnaSluzba.get();
    }

}