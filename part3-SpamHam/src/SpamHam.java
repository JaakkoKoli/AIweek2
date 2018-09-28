import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SpamHam {
    private final static String SPAM_PATH = "spamcount.txt";
    private final static String HAM_PATH  = "hamcount.txt";
    private final static String MESSAGE_PATH = "hamesim.txt";

    private static List<String> readMessage(String file) throws IOException {
        String teksti = new String(Files.readAllBytes(Paths.get(file)));
        return Arrays.asList(teksti.split("\\s+"));
    }

    private static Map<String, Integer> readOccurences(String file)
            throws FileNotFoundException {
        Map<String, Integer> counts;
        try (Scanner lukija = new Scanner(new File(file))) {
            counts = new HashMap<>();
            while (lukija.hasNext()) {
                int occurences = lukija.nextInt();
                String word = lukija.next();
                counts.put(word, occurences);
            }
        }

        return counts;
    }

    private static double Pspam(String word, Map<String, Integer> spam, Map<String, Integer> ham){
        if(spam.containsKey(word)){
            return spam.get(word)/75268.0;
        }
        return 0.0000000001;
    }
    private static double Pham(String word, Map<String, Integer> spam, Map<String, Integer> ham){
        if(ham.containsKey(word)){
            return ham.get(word)/290673.0;
        }
        return 0.0000000001;
    }
    
    private static double spamicity(String file, Map<String, Integer> spam, Map<String, Integer> ham) throws IOException{
        List<String> words = readMessage(file);
        double r = (75268.0/(290673.0+75268.0))/(290673.0/(290673.0+75268.0));
        for(String s:words){
            r = r*Pspam(s,spam,ham)/Pham(s,spam,ham);
        }
        return r/(r+1);
    } 
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Map<String, Integer> spam = readOccurences(SPAM_PATH);
        Map<String, Integer> ham = readOccurences(HAM_PATH);
        System.out.println(spamicity("spamesim.txt",spam,ham));
        System.out.println(spamicity("hamesim.txt",spam,ham));
    }
}
