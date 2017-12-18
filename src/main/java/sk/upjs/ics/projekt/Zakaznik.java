import javafx.collections.ObservableList;

public class Zakaznik {

    private Long id;
    private String meno;
    private String priezvisko;
    private String adresa;
    private String cislo;
    private String email;
    private String vybraneSluzby;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getCislo() {
        return cislo;
    }

    public void setCislo(String cislo) {
        this.cislo = cislo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVybraneSluzby() {
        return vybraneSluzby;
    }

    public void setVybraneSluzby(String vybraneSluzby) {
        this.vybraneSluzby = vybraneSluzby;
    }
    
    


    @Override
    public String toString() {
        return meno + " " + priezvisko;
    }

}