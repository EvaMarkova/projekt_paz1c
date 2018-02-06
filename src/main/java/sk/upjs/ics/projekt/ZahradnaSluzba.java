package sk.upjs.ics.projekt;

public class ZahradnaSluzba {

    private Long id;
    private String rocneObdobie;
    private String nazov;
    private String popis;
    private double cena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRocneObdobie() {
        return rocneObdobie;
    }

    public void setRocneObdobie(String rocneObdobie) {
        this.rocneObdobie = rocneObdobie;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return rocneObdobie + " - " + nazov;
    }

}
