
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class StringBuilderGenerator {
    public static void main(String[] args) {
        final int NUMFrasi = 5000;

        Path pathIncipit = Path.of("resources", "incipit.txt");
        Path pathFrasi = Path.of("resources", "frasi.txt");
        Path pathColpiDiScena = Path.of("resources", "colpi_di_scena.txt");
        Path pathFinali = Path.of("resources", "finali.txt");
        Path pathCongiunzioni = Path.of("resources", "congiunzioni.txt");
        Path pathRomanzo = Path.of("output", "romanzo.txt");

        List<String> stringIncipit = new ArrayList<>();
        List<String> stringFrasi = new ArrayList<>();
        List<String> stringColpiDiScena = new ArrayList<>();
        List<String> stringFinali = new ArrayList<>();
        List<String> stringCongiunzioni = new ArrayList<>();

        try {
            stringIncipit = Files.readAllLines(pathIncipit);
            stringFrasi = Files.readAllLines(pathFrasi);
            stringColpiDiScena = Files.readAllLines(pathColpiDiScena);
            stringFinali = Files.readAllLines(pathFinali);
            stringCongiunzioni = Files.readAllLines(pathCongiunzioni);
        } catch (IOException e) {
            System.err.println("Problema nella lettura dei file: " + e.getMessage());
        }

        long start = System.currentTimeMillis();

        try {
            StringBuilder sb = new StringBuilder();

            sb.append("Titolo: Indagine confusa nei pressi di San Severo");
            
            Random random = new Random();
            int counterRighe = 0;
            int counterCDS = 0;
            int capitolo = 1;

            for (int i = 0; i < NUMFrasi; i++) {
                sb.append(System.lineSeparator());
                if (counterRighe == 0) {
                    sb.append(System.lineSeparator());
                    sb.append("Capitolo ").append(capitolo);
                    sb.append(System.lineSeparator());
                    sb.append(stringIncipit.get(random.nextInt(stringIncipit.size())));
                } else {
                    sb.append(stringCongiunzioni.get(random.nextInt(stringCongiunzioni.size())));
                    if (counterCDS != 10) {
                        sb.append(" ").append(stringFrasi.get(random.nextInt(stringFrasi.size())));
                        counterCDS++;
                    } else {
                        sb.append(" ").append(stringColpiDiScena.get(random.nextInt(stringColpiDiScena.size())));
                        counterCDS = 0;
                    }
                }
                counterRighe++;
                
                if (counterRighe == 1667) { 
                    sb.append(System.lineSeparator());
                    sb.append(stringFinali.get(random.nextInt(stringFinali.size())));
                    counterRighe = 0; 
                    capitolo++;
                }
            }

            Files.writeString(pathRomanzo, sb.toString());

        } catch (IOException e) {
            System.err.println("Problema nella lettura del file: " + e.getMessage());
        }

        long end = System.currentTimeMillis();
        long durata = end - start;

        System.out.println("Durata in millisecondi: " + durata);
    }
}
