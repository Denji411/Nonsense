
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class StringGenerator {
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

        try (BufferedWriter bw = Files.newBufferedWriter(pathRomanzo)) {
            bw.write("Titolo: Indagine confusa nei pressi di San Severo");
            bw.newLine();
            
            Random random = new Random();
            int counterRighe = 0;
            int counterCDS = 0;
            int capitolo = 1;

            for (int i = 0; i < NUMFrasi; i++) {
                if (counterRighe == 0) {
                    bw.newLine();
                    bw.write("Capitolo " + capitolo);
                    bw.newLine();
                }

                bw.write(stringIncipit.get(random.nextInt(30)));
                bw.write(stringCongiunzioni.get(random.nextInt(30)));
                bw.write(stringFrasi.get(random.nextInt(170)));
                bw.write(stringCongiunzioni.get(random.nextInt(30)));

                if (counterCDS != 10) {
                    bw.write(stringFinali.get(random.nextInt(30)));
                    counterCDS++;
                } else {
                    bw.write(stringColpiDiScena.get(random.nextInt(30)));
                    counterCDS = 0;
                }

                bw.newLine();
                counterRighe++;

                if (counterRighe == 1667) { 
                    counterRighe = 0; 
                    capitolo++;
                }
            }
        } catch (Exception e) {
            System.err.println("Problema nella lettura del file: " + e.getMessage());
        }
    }
}
