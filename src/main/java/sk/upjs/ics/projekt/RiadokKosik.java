package sk.upjs.ics.projekt;

public class RiadokKosik {

    private String nazov;
    private Integer pocet;
    private Double cena;
    
   

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public Integer getPocet() {
        return pocet;
    }

    public void setPocet(Integer pocet) {
        this.pocet = pocet;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return nazov; 
    }

    
}
