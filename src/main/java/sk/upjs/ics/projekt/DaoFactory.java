package sk.upjs.ics.projekt;

import java.io.File;

public enum DaoFactory {
    
    INSTANCE;
    
    public ReklamnaSluzbaDao getReklamnaSluzbaDao() {
        return getTextFileReklamnaSluzbaDao();
    }
    
    private TextFileReklamnaSluzbaDao getTextFileReklamnaSluzbaDao() {
        File suborSRiadkami = new File("reklamneSluzby.txt");
        return new TextFileReklamnaSluzbaDao(suborSRiadkami);
    }
}
