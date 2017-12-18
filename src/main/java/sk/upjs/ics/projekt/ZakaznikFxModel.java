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

public class ZakaznikFxModel {

    private ObservableList<Zakaznik> zakaznici;
    private ObjectProperty<Zakaznik> vybratyZakaznik = new SimpleObjectProperty<>(null);
    private Long id;
    private StringProperty meno = new SimpleStringProperty();
    private StringProperty priezvisko = new SimpleStringProperty();
    private StringProperty adresa = new SimpleStringProperty();
    private StringProperty cislo = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty vybraneSluzby = new SimpleStringProperty();

    public ZakaznikFxModel() {
        ZakaznikDao zakaznikDao = DaoFactory.INSTANCE.getZakaznikDao();
        List<Zakaznik> zakaznici = zakaznikDao.getAll();
        this.zakaznici = FXCollections.observableArrayList(zakaznici);
    }

    public StringProperty menoProperty() {
        return meno;
    }

    public String getMeno() {
        return meno.get();
    }

    public void setMeno(String meno) {
        this.meno.set(meno);
    }

    public StringProperty priezviskoProperty() {
        return priezvisko;
    }

    public String getPriezvisko() {
        return priezvisko.get();
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko.set(priezvisko);
    }

    public StringProperty adresaProperty() {
        return adresa;
    }

    public String getAdresa() {
        return adresa.get();
    }

    public void setAdresa(String adresa) {
        this.adresa.set(adresa);
    }

    public StringProperty cisloProperty() {
        return cislo;
    }

    public String getCislo() {
        return cislo.get();
    }

    public void setCislo(String cislo) {
        this.cislo.set(cislo);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty vybraneSluzbyProperty() {
        return vybraneSluzby;
    }

    public String getVybraneSluzby() {
        return vybraneSluzby.get();
    }

    public void setVybraneSluzby(String vybraneSluzby) {
        this.vybraneSluzby.set(vybraneSluzby);
    }

    public Zakaznik getZakaznik() {
        Zakaznik zakaznik = new Zakaznik();
        zakaznik.setId(id);
        zakaznik.setMeno(getMeno());
        zakaznik.setPriezvisko(getPriezvisko());
        zakaznik.setAdresa(getAdresa());
        zakaznik.setCislo(getCislo());
        zakaznik.setEmail(getEmail());
        zakaznik.setVybraneSluzby(getVybraneSluzby());
        return zakaznik;
    }

    public void setZakaznik(Zakaznik zakaznik) {
        id = zakaznik.getId();
        setMeno(zakaznik.getMeno());
        setPriezvisko(zakaznik.getPriezvisko());
        setAdresa(zakaznik.getAdresa());
        setCislo(zakaznik.getCislo());
        setEmail(zakaznik.getEmail());
        setVybraneSluzby(zakaznik.getVybraneSluzby());
    }

    public void newRiadokKosik() {
        id = null;
        setMeno(null);
        setPriezvisko(null);
        setAdresa(null);
        setCislo(null);
        setEmail(null);
        setVybraneSluzby(null);
    }

    public ObservableList<Zakaznik> getZakaznici() {
        return zakaznici;
    }

    public void setVybratyZakaznik(Zakaznik zakaznik) {
        vybratyZakaznik.set(zakaznik);
    }

    public ObjectProperty<Zakaznik> vybratyZakaznikProperty() {
        return vybratyZakaznik;
    }

    public Zakaznik getVybratyZakaznik() {
        return vybratyZakaznik.get();
    }

}