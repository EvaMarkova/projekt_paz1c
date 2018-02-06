package sk.upjs.ics.projekt;

public class Zakaznik {

    private Long id;
    private String meno;
    private String priezvisko;
    private String adresa;
    private String cislo;
    private String email;
    private String vydanyKosik;
    private String zaplatenyKosik;

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

    public String getVydanyKosik() {
        return vydanyKosik;
    }

    public void setVydanyKosik(String vydanyKosik) {
        this.vydanyKosik = vydanyKosik;
    }

    public String getZaplatenyKosik() {
        return zaplatenyKosik;
    }

    public void setZaplatenyKosik(String zaplatenyKosik) {
        this.zaplatenyKosik = zaplatenyKosik;
    }

    @Override
    public String toString() {
        return meno + " " + priezvisko;
    }

}
