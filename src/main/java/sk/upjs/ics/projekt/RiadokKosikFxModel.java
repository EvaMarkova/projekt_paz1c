package sk.upjs.ics.projekt;

import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RiadokKosikFxModel {

    private ObservableList<RiadokKosik> riadkyKosika;
    private ObjectProperty<RiadokKosik> vybratyRiadokKosika = new SimpleObjectProperty<>(null);
    private Long id;
    private StringProperty nazov = new SimpleStringProperty();
    private IntegerProperty pocet = new SimpleIntegerProperty();
    private DoubleProperty cena = new SimpleDoubleProperty();

    public RiadokKosikFxModel() {
        RiadokKosikDao riadokKosikDao = DaoFactory.INSTANCE.getRiadokKosikDao();
        List<RiadokKosik> riadkyKosika = riadokKosikDao.getAll();
        this.riadkyKosika = FXCollections.observableArrayList(riadkyKosika);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public StringProperty nazovProperty() {
        return nazov;
    }

    public String getNazov() {
        return nazov.get();
    }

    public void setNazov(String nazov) {
        this.nazov.set(nazov);
    }

    public IntegerProperty pocetProperty() {
        return pocet;
    }

    public Integer getPocet() {
        return pocet.get();
    }

    public void setPocet(Integer pocet) {
        this.pocet.set(pocet);
    }

    public DoubleProperty cenaProperty() {
        return cena;
    }

    public Double getCena() {
        return cena.get();
    }

    public void setCena(Double cena) {
        this.cena.set(cena);
    }
   
    public RiadokKosik getRiadokKosik() {
        RiadokKosik riadokKosik = new RiadokKosik();
        riadokKosik.setId(id);
        riadokKosik.setNazov(getNazov());
        riadokKosik.setPocet(getPocet());
        riadokKosik.setCena(getCena());
        return riadokKosik;
    }

    public void setRiadokKosik(RiadokKosik riadokKosik) {
        id = riadokKosik.getId();
        setNazov(riadokKosik.getNazov());
        setPocet(riadokKosik.getPocet());
        setCena(riadokKosik.getCena());
    }

    public void newRiadokKosik() {
        id = null;
        setNazov(null);
        setPocet(null);
        setCena(null);

    }

    public ObservableList<RiadokKosik> getRiadkyKosika() {
        return riadkyKosika;
    }

    public void setVybratyRiadokKosika(RiadokKosik riadokKosik) {
        vybratyRiadokKosika.set(riadokKosik);
    }

    public ObjectProperty<RiadokKosik> vybratyRiadokKosikaProperty() {
        return vybratyRiadokKosika;
    }

    public RiadokKosik getVybratyRiadokKosika() {
        return vybratyRiadokKosika.get();
    }

}