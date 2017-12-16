package sk.upjs.ics.projekt;

public class ReklamnaSluzba extends Sluzba{
 
    private String nazov;   

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }
    
    @Override
    public String toString() {
        return nazov;
    }
    
}
