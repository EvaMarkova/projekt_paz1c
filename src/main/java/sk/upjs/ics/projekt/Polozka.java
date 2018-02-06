package sk.upjs.ics.projekt;

public class Polozka {

    private Long id;
    private String nazov;
    private int pocet;
    private double cena;
    private Long zakazniciId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return nazov;
    }

    public Long getZakazniciId() {
        return zakazniciId;
    }

    public void setZakazniciId(Long zakazniciId) {
        this.zakazniciId = zakazniciId;
    }

}
