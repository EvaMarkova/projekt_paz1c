package sk.upjs.ics.projekt;

public class ReklamnaSluzba {
  // "id2" ?
    private Long id;
    private String nazov;
    private String popis;
  // ??? List cennikov abo co ???
    private String cena;

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

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }
    
    @Override
    public String toString() {
        return nazov;
    }
    
}
