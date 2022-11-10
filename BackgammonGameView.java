import java.util.Scanner;
import java.util.ArrayList;
import java.util.AbstractMap;

public class BackgammonGameView {
    private final static String spacer = "____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____";

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
            BackgammonGame.immediateExit();
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

    
    private static String buildDiceRoll(BackgammonGame game){
        String returnStr = "";
        returnStr += String.format(" %1$-130s\n", spacer);
        returnStr += String.format("%1$52s", "");
        returnStr += String.format("%1$-70s\n", "Player Turn: " + game.getCurrentPlayer().getName() + " " + game.getCurrentPlayer().getDisplayCheckerColor());
        returnStr += String.format("%1$60s", "");
        returnStr += String.format("%1$-70s\n", DieView.display(game.getDie1()) + " - " + DieView.display(game.getDie2()));
        returnStr += String.format("%1$52s", "");
        returnStr += String.format("%1$-70s\n", "You rolled " + game.getDie1().getLastRoll() + " and " + game.getDie2().getLastRoll());
        returnStr += String.format(" %1$-130s\n", spacer);
        
        return returnStr;
    }

    private static void buildBoard(BackgammonGame game){
        System.out.println(String.format(" %1$-130s\n", spacer));
        System.out.println("\n" + BoardView.display(game.getBoard(), game.getCurrentPlayer().getColor()));
    }
    
    public static String display(BackgammonGame game, boolean showLogPanel, ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> possibleMoves){
        String returnStr = "";
        int counter = 0; 

        buildBoard(game);
        returnStr += buildDiceRoll(game);
        
        if(possibleMoves.size() > 0){
            returnStr += String.format(" %1$-130s\n", "Possible Moves: \n");
            for (AbstractMap.SimpleEntry<Integer,Integer> pair : possibleMoves)
            {
                returnStr+= String.format(" %1$-130s\n", BackgammonGameView.genKeyCode(counter)+ ": move a checker from point " + pair.getKey()+ " to point "+pair.getValue() + "\n");
                counter +=1;
            }
        }
        returnStr += String.format(" %1$-130s\n", spacer);
        returnStr += display(game, showLogPanel, false);

        System.out.println(returnStr);
        return(returnStr);
    }

    public static String display(BackgammonGame game, boolean showLogPanel, boolean showBoard){
        String returnStr = "";

        if(showBoard){
            buildBoard(game);
        }
        if(showLogPanel){
            if(showBoard){
                returnStr += buildDiceRoll(game);
            }

            returnStr += EventLogView.display(game.getEventLog());
        }
        returnStr += String.format(" %1$-130s\n", spacer);

        if(showBoard){
            System.out.println(returnStr);
        }
        return(returnStr);
    }

    public static String readNewInput(Scanner in, Player activePlayer){
        String userInput;
        System.out.println("\n" + activePlayer.getName() + ", your turn. Enter a command:");
        do{
            userInput = in.nextLine();
        }while(!validateInput(userInput, false));

        return userInput;
    }

    public static String genKeyCode(int index){
	    //AA, ... AZ, BA, ...., BZ, CA, ....
	    int secondChar = 'A' + index%26;
	    int firstChar = 'A' + (index - index%26)%25;
	    return "" + (char)firstChar + (char)secondChar;
	}
	
	public static int reverseKeyCode(String keyCode){
	    int firstChar = (int) keyCode.charAt(0); 
	    int secondChar = (int) keyCode.charAt(1); 
	    return (firstChar - 'A')*26 + (secondChar-'A');
	}

    public static boolean validateMoveInput(String userInput, int numPossibleMoves){
        if(userInput.toLowerCase().equals("quit")){
            BackgammonGame.immediateExit();
        }
        if(userInput.length() != 2){
            System.out.println("Please enter exactly two letters for the move you would like to make.");
            return false;
        }

        char firstChar = userInput.toUpperCase().charAt(0);
        char secondChar = userInput.toUpperCase().charAt(1);
        if(firstChar < 'A' || firstChar > 'Z' || secondChar < 'A' || secondChar > 'Z'){
            System.out.println("Please enter letters between A-Z");
            return false;
        }
        else if(reverseKeyCode(userInput.toUpperCase()) >= numPossibleMoves){
            System.out.println("Please select a key code from the ones shown.");
            return false;
        }
        return true;
    }

    public static int promptForMove(Scanner in, ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> possibleMoves, BackgammonGame game){
        String userInput;
        int numPossibleMoves = possibleMoves.size();

        display(game, true, possibleMoves);
        
        System.out.println("\n" + game.getCurrentPlayer().getName() + ", Choose one of the possible moves: ");
        do{
            userInput = in.nextLine();
        }
        while(!validateMoveInput(userInput, numPossibleMoves));
        
        return(reverseKeyCode(userInput.toUpperCase()));
    }
}
