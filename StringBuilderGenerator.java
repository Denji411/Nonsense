
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class StringBuilderGenerator {
    private static String antiRipetizioni (List<String> lista, List<Integer> indici, int[] index) {
        if (index[0] >= indici.size()) {
            Collections.shuffle(indici);
            index[0] = 0;
        }
        
        return lista.get(indici.get(index[0]++));
    }

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
            
            int counterRighe = 0;
            int counterCDS = 0;
            int capitolo = 1;

            List<Integer> antiRipetizioneIncipit = new ArrayList<>();
            int[] indexIncipit = {0};

            for (int i = 0; i < stringIncipit.size(); i++) {
                antiRipetizioneIncipit.add(i);
            }
            Collections.shuffle(antiRipetizioneIncipit);

            List<Integer> antiRipetizioneFrasi = new ArrayList<>();
            int[] indexFrasi = {0};

            for (int i = 0; i < stringFrasi.size(); i++) {
                antiRipetizioneFrasi.add(i);
            }
            Collections.shuffle(antiRipetizioneFrasi);

            List<Integer> antiRipetizioneColpiDiScena = new ArrayList<>();
            int[] indexColpiDiScena = {0};

            for (int i = 0; i < stringColpiDiScena.size(); i++) {
                antiRipetizioneColpiDiScena.add(i);
            }
            Collections.shuffle(antiRipetizioneColpiDiScena);
            
            List<Integer> antiRipetizioneFinali = new ArrayList<>();
            int[] indexFinali = {0};

            for (int i = 0; i < stringFinali.size(); i++) {
                antiRipetizioneFinali.add(i);
            }
            Collections.shuffle(antiRipetizioneFinali);

            List<Integer> antiRipetizioneCongiunzioni = new ArrayList<>();
            int[] indexCongiunzioni = {0};

            for (int i = 0; i < stringCongiunzioni.size(); i++) {
                antiRipetizioneCongiunzioni.add(i);
            }
            Collections.shuffle(antiRipetizioneCongiunzioni);

            for (int i = 0; i < NUMFrasi; i++) {
                sb.append(System.lineSeparator());
                if (counterRighe == 0) {
                    sb.append(System.lineSeparator());
                    sb.append("Capitolo ").append(capitolo);
                    sb.append(System.lineSeparator());
                    sb.append(antiRipetizioni(stringIncipit, antiRipetizioneIncipit, indexIncipit));
                } else {

                    sb.append(antiRipetizioni(stringCongiunzioni, antiRipetizioneCongiunzioni, indexCongiunzioni));

                    if (counterCDS != 10) {
                        sb.append(" ").append(antiRipetizioni(stringFrasi, antiRipetizioneFrasi, indexFrasi));
                        counterCDS++;
                    } else {
                        sb.append(" ").append(antiRipetizioni(stringColpiDiScena, antiRipetizioneColpiDiScena, indexColpiDiScena));
                        counterCDS = 0;
                    }
                }
                counterRighe++;
                
                if (counterRighe == 1667) { 
                    sb.append(System.lineSeparator());
                    sb.append(antiRipetizioni(stringFinali, antiRipetizioneFinali, indexFinali));
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
