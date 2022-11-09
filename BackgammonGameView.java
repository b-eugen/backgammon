import java.util.Scanner;

public class BackgammonGameView {
    
    public static boolean validateInput(String userInput, boolean takeName){
        if(takeName){
            if(userInput.length() > 15){
                System.out.println("Please ensure you name includes no more than 15 characters");
                return false;
            }
            else if(userInput.length() < 1){
                System.out.println("Please ensure you name includes no less than 1 character");
                return false;
            }
        }
        else if(userInput.toLowerCase().equals("quit")){
            return true;
        }
        else{
            userInput = userInput.toLowerCase();
            if(!userInput.equals("roll")){
                System.out.println("Please ensure you enter a valid command, try again.");
                return false;
            }
        }
        return true;
    }

    public static void immediateExit(){
        System.out.println("\n\nYou have quit backgammon.");
    }

    public static String[] getNames(Scanner in){
        String names[] = new String[2];
        String userInput;

        System.out.println("\nWelcome To Backgammon.");
        System.out.println("\nUsage Instructions: 'roll' to roll, 'quit' to quit.");
        System.out.println("\n\nPlayer 1, enter your name: ");

        do{
            userInput = in.nextLine();
        }
        while(!validateInput(userInput, true));

        names[0] = userInput;
        System.out.println("\n\nPlayer 2, enter your name: ");
        
        do{
            userInput = in.nextLine();
        }
        while(!validateInput(userInput, true));

        names[1] = userInput;
        return names;
    }

    public static void gameToString(BackgammonGame game){
        String spacer = "____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____";
        String returnStr = "";

        // returnStr += game. PRINT BOARD HERE

        returnStr += String.format(" %1$-130s\n", spacer);
        returnStr += String.format("%1$60s", "");
        returnStr += String.format("%1$-70s\n", DieView.toString(game.getDie1()) + " - " + DieView.toString(game.getDie2()));
        returnStr += String.format(" %1$-130s\n", spacer);
        // System.out.println("1-----------\n" + returnStr);

        returnStr += EventLogView.logPanelToString(game.getEventLog());
        returnStr += String.format(" %1$-130s\n", spacer);

        System.out.println(returnStr);
    }

    public static String readNewInput(Scanner in, Player activePlayer){
        String userInput;
        System.out.println("\n" + activePlayer.getName() + ", your turn. Enter a command:");
        do{
            userInput = in.nextLine();
        }while(!validateInput(userInput, false));

        return userInput;
    }
}
