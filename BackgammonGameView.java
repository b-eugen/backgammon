import java.util.Scanner;
import java.util.ArrayList;
import java.util.AbstractMap;

public class BackgammonGameView {
    private final static String spacer = "____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____";    

    public static String[] getNames(Scanner in, BackgammonGame game){
        String names[] = new String[2];
        String userInput;

        System.out.println("\nWelcome To Backgammon.");
        System.out.println("\nUsage Instructions: 'roll' to roll, 'quit' to quit.");
        System.out.println("\n\nPlayer 1, enter your name: ");

        do{
            userInput = in.nextLine();
        }
        while(!game.validateInput(userInput, true));

        names[0] = userInput;
        System.out.println("\n\nPlayer 2, enter your name: ");
        
        do{
            userInput = in.nextLine();
        }
        while(!game.validateInput(userInput, true));

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
    
    public static String display(BackgammonGame game, boolean showLogPanel, ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> possibleMoves){
        String returnStr = "";
        String optionStr = "";
        int counter = 0; 

        buildBoard(game);
        returnStr += buildDiceRoll(game);
        
        if(possibleMoves.size() > 0){
            returnStr += String.format(" %1$-130s\n", "Possible Moves: \n");
            for (ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> moveCombination : possibleMoves){
                optionStr += "| " + BackgammonGameView.genKeyCode(counter)+ ": ";
                for (AbstractMap.SimpleEntry<Integer,Integer> pair : moveCombination){
                    optionStr+= "Pip " + pair.getKey()+ " to Pip "+pair.getValue() + ", ";
                }
                returnStr += String.format(" %1$-65s", optionStr);

                optionStr = "";
                if(counter%2 == 1){
                    returnStr+="\n";
                }
                counter +=1;
            }
            returnStr+="\n";
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

    public static String readNewInput(Scanner in, Player activePlayer, BackgammonGame game){
        String userInput;
        System.out.println("\n" + activePlayer.getName() + ", your turn. Enter a command:");
        do{
            userInput = in.nextLine();
        }while(!game.validateInput(userInput, false));

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


    public static int promptForMove(Scanner in, ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> possibleMoves, BackgammonGame game){
        String userInput;
        int numPossibleMoves = possibleMoves.size();

        display(game, true, possibleMoves);
        
        System.out.println("\n" + game.getCurrentPlayer().getName() + ", Choose one of the possible moves: ");
        do{
            userInput = in.nextLine();
        }
        while(!game.validateMoveInput(userInput, numPossibleMoves));
        
        return(reverseKeyCode(userInput.toUpperCase()));
    }

    public static void showPipScores(BackgammonGame game){
        Player p1, p2;
        p1 = game.getPlayers().get(0);
        p2 = game.getPlayers().get(1);
        System.out.println(String.format(" %1$-130s\n", spacer));
        System.out.println(String.format(" %1$-130s", p1.getName() + " Pip Score: " + game.getBoard().getPipScore(p1.getColor())));
        System.out.println(String.format(" %1$-130s", p2.getName() + " Pip Score: " + game.getBoard().getPipScore(p2.getColor())));
        System.out.println(String.format(" %1$-130s\n", spacer));
    }

    public static void showHint(){
        System.out.println(String.format(" %1$-130s\n", spacer));
        System.out.println(String.format(" %1$-130s", "Usable commands:"));
        System.out.println(String.format(" %1$-130s", "pip: display pip scores for both players"));
        System.out.println(String.format(" %1$-130s", "hint: shows all commands"));
        System.out.println(String.format(" %1$-130s", "roll: roll the dice"));
        System.out.println(String.format(" %1$-130s\n", spacer));
    }

    public static void declareWinner(Player p){
        System.out.println(String.format(" %1$-130s", spacer));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", " _    _ _                       _ "));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", "| |  | (_)                     | |"));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", "| |  | |_ _ __  _ __   ___ _ __| |"));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", "| |/\\| | | '_ \\| '_ \\ / _ \\ '__| |"));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", "\\  /\\  / | | | | | | |  __/ |  |_|"));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", " \\/  \\/|_|_| |_|_| |_|\\___|_|  (_)"));
        System.out.print(String.format("\n%1$57s", ""));
        System.out.println(String.format(" %1$-70s\n", p.getName() + " Wins!"));
        System.out.println(String.format(" %1$-130s\n\n", spacer));
    }
}
