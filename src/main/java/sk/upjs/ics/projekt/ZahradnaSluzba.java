package sk.upjs.ics.projekt;

public class ZahradnaSluzba extends Sluzba{

  
    private String rocneObdobie;
    private String nazov;
    

    

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
    
    @Override
    public String toString() {
        return rocneObdobie + " - " + nazov;
    }
    
}