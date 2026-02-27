
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class StringGenerator {
    public static void main(String[] args) {
        Path pathIncipit = Path.of("resources", "incipit.txt");
        Path pathFrasi = Path.of("resources", "frasi.txt");
        Path pathColpiDiScena = Path.of("resources", "colpi_di_scena.txt");
        Path pathFinali = Path.of("resources", "finali.txt");
        Path pathCongiunzioni = Path.of("resources", "congiunzioni.txt");
        Path pathRomanzo = Path.of("resources", "romanzo.txt");

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
    }
}
