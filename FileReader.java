import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.ArrayList; 

public class FileReader {
    public static void applyCommandsFromFile(String filename, ArrayList<Player> matchPlayers, BackgammonGame game){
        try {
            File file = new File(filename);
            Scanner inText = new Scanner(file);
            game.gameRoutine(inText, matchPlayers, game, true);
            inText.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Cannot read from file: " + filename);
            e.printStackTrace();
        }
    }
}
