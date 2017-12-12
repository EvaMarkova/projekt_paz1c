package sk.upjs.ics.projekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TextFileReklamnaSluzbaDao implements ReklamnaSluzbaDao{

    private File suborSRiadkami;

    public TextFileReklamnaSluzbaDao(File suborSRiadkami) {
        this.suborSRiadkami = suborSRiadkami;
    }
    
    
    @Override
    public List<ReklamnaSluzba> getAll() throws TextFileReklamnaSluzbaDaoException {
        try(Scanner scanner = new Scanner(suborSRiadkami)) {
            scanner.nextLine();
            scanner.useLocale(Locale.US);// hlavičky
            List<ReklamnaSluzba> result = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ReklamnaSluzba reklamnaSluzba = new ReklamnaSluzba();
                Scanner sLine = new Scanner(line);
                sLine.useDelimiter(";");
                reklamnaSluzba.setId(sLine.nextLong());               
                reklamnaSluzba.setNazov(sLine.next());
                reklamnaSluzba.setPopis(sLine.next());
                reklamnaSluzba.setCena(sLine.next());
                result.add(reklamnaSluzba);
            }
            return result;
        } catch (FileNotFoundException e) {
            try {
                suborSRiadkami.createNewFile();
                
            } catch (IOException ioe) {
               throw new TextFileReklamnaSluzbaDaoException(
                       "súbor " + suborSRiadkami.getPath() + 
                       " som nenašiel a neviem ho ani vyrobiť."
                       , ioe);
            }
        } 
        return null;
    }
    
}
