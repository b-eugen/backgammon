import java.util.Scanner;

public class MenuView {
    public static void welcomeMessage(){
        System.out.println("\nWelcome to Backgammon. Enter quit at any time to exit.");
    }

    public static boolean askNewMatch(Scanner in, Menu menu){
        String userInput;
        
        System.out.println("\n\nNew match? Enter 'yes' or 'no'.\n");
        do{
            userInput = in.nextLine();
        }
        while(!menu.validateMenuInput(userInput));
        
        return userInput.toLowerCase().equals("yes");
    }
}
