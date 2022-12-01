/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * Represents a backgammon game view (MVC)
 * @version 1 2022-21-11
 * @author Aness Al-Qawlaq
 */

import java.util.ArrayList;
import java.util.AbstractMap;

/**
 * {@code BackgammonGameView} is the view class of the BackgammonGame class, which handles its inputs and outputs as per the MVC model
 */

public class BackgammonGameView {
    public final static String spacer = "____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ____ ";    
    
    
    /** 
     * procedure to build the output section pertaining to the dice rolls
     * @param game - the backgammon game
     * @return String - returns the output string
     */
    private static String buildDiceRoll(BackgammonGame game){
        String returnStr = "";
        returnStr += String.format(" %1$-190s\n", spacer);
        returnStr += String.format("%1$47s", "");
        returnStr += String.format("%1$-65s\n", "Match Score: " + game.getPlayers().get(0).getName() + " " + game.getPlayers().get(0).getScore() + " - " + game.getPlayers().get(1).getScore() + " " + game.getPlayers().get(1).getName());
        returnStr += String.format("%1$54s", "");
        returnStr += String.format("%1$-72s\n", "Match Length: " + game.getMatchLength());
        returnStr += String.format("%1$52s", "");
        returnStr += String.format("%1$-70s\n", "Player Turn: " + game.getCurrentPlayer().getName() + " " + game.getCurrentPlayer().getDisplayCheckerColor());
        if(!game.getSupressRollPrint()){
            returnStr += String.format("%1$60s", "");
            returnStr += String.format("%1$-70s\n", DieView.display(game.getDie1()) + " - " + DieView.display(game.getDie2()));
            returnStr += String.format("%1$52s", "");
            returnStr += String.format("%1$-70s\n", "You rolled " + game.getDie1().getLastRoll() + " and " + game.getDie2().getLastRoll());
        }
        returnStr += String.format(" %1$-190s\n", spacer);
        
        return returnStr;
    }

    
    /**
     * procedure to build game board output string within game 
     * @param game - the backgammon game
     */
    private static void buildBoard(BackgammonGame game){
        System.out.println(String.format(" %1$-190s\n", spacer));
        System.out.println("\n" + BoardView.display(game.getBoard(), game.getCurrentPlayer().getColor()));
    }
    
    
    /** 
     * method responsible for displaying possible moves to the player so that one is selected
     * @param possibleMoves - the moves that are possible to be selected from
     * @return String - returns built output string
     */
    public static String display(BackgammonGame game, boolean showLogPanel, ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> possibleMoves){
        String returnStr = "";
        String optionStr = "";
        int counter = 0; 

        buildBoard(game);
        returnStr += buildDiceRoll(game);
        
        if(possibleMoves.size() > 0){
            returnStr += String.format(" %1$-190s\n", "Possible Moves: \n");
            for (ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> moveCombination : possibleMoves){
                optionStr += "| " + BackgammonGameView.genKeyCode(counter)+ ": ";
                for (AbstractMap.SimpleEntry<Integer,Integer> pair : moveCombination){
                    if(pair.getKey() == 25){
                        optionStr+= "Bar to Pip "+pair.getValue() + ", ";
                    }
                    else if(pair.getValue() == 0){
                        optionStr+= "Bear off Pip " + pair.getKey() + ", ";
                    }
                    else{
                        optionStr+= String.format("Pip %2d", pair.getKey()) + String.format(" to Pip %2d ", pair.getValue()) + ", ";
                    }
                }
                returnStr += String.format(" %1$-85s", optionStr);

                optionStr = "";
                if(counter%2 == 1){
                    returnStr+="\n";
                }
                counter +=1;
            }
            returnStr+="\n";
        }
        returnStr += String.format(" %1$-190s\n", spacer);
        returnStr += display(game, showLogPanel, false);

        System.out.println(returnStr);
        return(returnStr);
    }

    
    /** 
     * wrapper method responsible for entire display of the game depending on current state 
     * @param game - backgammon game
     * @param showLogPanel - logical value determines whether or not log panel should be shown
     * @param showBoard - logical value determines whether or not game board should be shown
     * @return String - returns built string
     */
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
        returnStr += String.format(" %1$-190s\n", spacer);

        if(showBoard){
            System.out.println(returnStr);
        }
        return(returnStr);
    }

    
    /** 
     * method to read new input from player
     * @param in - input scanner
     * @param activePlayer - current player that can take game actions
     * @param game - the backgammon game
     * @return String - returns built string
     */
    public static String readNewInput(MultiScanner in, Player activePlayer, BackgammonGame game){
        String userInput;
        System.out.println("\n" + activePlayer.getName() + ", your turn. Enter a command:");
        do{
            userInput = in.nextLine();
        }while(!game.validateInput(userInput));

        return userInput;
    }

    
    /** 
     * method to generate letter code from integer in the form (AA, AB, AC ... ZZ) for (0, 1, ...)
     * @param index - input integer
     * @return String - corresponding letter code
     */
    public static String genKeyCode(int index){
	    int secondChar = 'A' + index%26;
	    int firstChar = 'A' + (index - index%26)%25;
	    return "" + (char)firstChar + (char)secondChar;
	}
	
	
    /** 
     * method to reverse-engineer letter code back to an integer 
     * @param keyCode - input key code
     * @return int - corresponding integer
     */
    public static int reverseKeyCode(String keyCode){
	    int firstChar = (int) keyCode.charAt(0); 
	    int secondChar = (int) keyCode.charAt(1); 
	    return (firstChar - 'A')*26 + (secondChar-'A');
	}


    
    /** 
     * method used to collect user input regarding which move to make
     * @param possibleMoves - all the possible moves
     * @param game - the backgammon game
     * @return int - returns the index of the move to be made in possibleMoves
     */
    public static int promptForMove(MultiScanner in, ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> possibleMoves, BackgammonGame game){
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

    
    /** 
     * method shows the pip scores
     * @param game - backgammon game
     */
    public static void showPipScores(BackgammonGame game){
        Player p1, p2;
        p1 = game.getPlayers().get(0);
        p2 = game.getPlayers().get(1);
        System.out.println(String.format(" %1$-190s\n", spacer));
        System.out.println(String.format(" %1$-190s", p1.getName() + " Pip Score: " + game.getBoard().getPipScore(p1.getColor())));
        System.out.println(String.format(" %1$-190s", p2.getName() + " Pip Score: " + game.getBoard().getPipScore(p2.getColor())));
        System.out.println(String.format(" %1$-190s\n", spacer));
    }

    /** 
     * procedure shows hint
     */
    public static void showHint(){
        System.out.println(String.format(" %1$-190s\n", spacer));
        System.out.println(String.format(" %1$-190s", "Usable commands:"));
        System.out.println(String.format(" %1$-190s", "pip: display pip scores for both players"));
        System.out.println(String.format(" %1$-190s", "hint: shows all commands"));
        System.out.println(String.format(" %1$-190s", "dice <int> <int>: forces the roll of the dice to be as specified by integers"));
        System.out.println(String.format(" %1$-190s", "test <filename>: executes the commands from the file, specified by filename, instead of standard input"));
        System.out.println(String.format(" %1$-190s", "roll: roll the dice"));
        System.out.println(String.format(" %1$-190s", "double: offer your opponent to double the stakes or lose!"));
        System.out.println(String.format(" %1$-190s\n", spacer));
    }

    
    /** 
     * method to declare winner of game
     * @param p - the winning player
     */
    public static void declareWinner(Player p){
        System.out.println(String.format(" %1$-190s", spacer));
        System.out.println(String.format(" %1$-70s\n", p.getName() + " Wins this game!"));
        System.out.println(String.format(" %1$-190s\n\n", spacer));
    }

    
    /** 
     * method to declare type of victory (single, gammon, backgammon)
     * @param type - string input of victory type
     */
    public static void declareGameEndingType(String type){
        System.out.println(String.format(" %1$-190s", spacer));
        System.out.println(String.format(" %1$-70s\n", " The game ends as "+type));
        System.out.println(String.format(" %1$-190s\n\n", spacer));
    }

    /** 
     * method to show that the player has no available moves
     */
    public static void noMoves(){
        System.out.print(String.format("\n%1$57s", ""));
        System.out.println(String.format(" %1$-70s", "You have no moves possible. Switching turns."));
    }

    
    /** 
     * method to prompt target player to accept/refuse a double offer
     * @param in -input scanner
     * @param player - the player who should accept/refuse the doubling offer
     * @return boolean - true if the player accepts, false if the player rejects
     */
    public static boolean promptForDouble(MultiScanner in, Player player)
    {
        String userInput;
        boolean result = false;
        do
        {
            System.out.println(player +"Would you like to accept the double (accept/refuse)?");
            userInput = in.nextLine();
            userInput = userInput.toLowerCase();
        }
        while(!userInput.equals("accept") && !userInput.equals("refuse"));

        if (userInput.equals("accept"))
        {
            result= true;
        }
        return result;
    }
}
