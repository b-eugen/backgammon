/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * Represents the view class (MVC) of the match 
 * @version 1 2022-21-11
 * @author Aness Al-Qawlaq
 */

 /**
 * {@code MenuView} is the (MVC) view of the Menu object
 */

public class MenuView {
    public static void welcomeMessage(){
        System.out.println("\nWelcome to Backgammon. Enter quit at any time to exit.");
    }

    
    /** 
     * method to ask whether the players want to start a new match in the main menu
     * @param in - input scanenr
     * @param menu - the menu object
     * @return boolean - true if the players want to start a new match
     */
    public static boolean askNewMatch(MultiScanner in, Menu menu){
        String userInput;
        
        do{
            System.out.println("\n\nNew match? Enter 'new' for new match, (test file.txt for test), or 'no' to exit.\n");
            userInput = in.nextLine();
        }
        while(!menu.validateMenuInput(userInput));
        
        return (!userInput.toLowerCase().equals("no"));
    }

    /** 
     * method to notify players that the program was terminated
     */
    public static void exitMessage(){
        System.out.println("\n\nYou have quit backgammon.\n\n");
    }
}
