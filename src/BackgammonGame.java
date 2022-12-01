/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * Represents a backgammon game
 * @version 1 2022-21-11
 * @author Aness Al-Qawlaq
 */

import java.util.AbstractMap;
import java.util.ArrayList;

/**
 * {@code BackgammonGame} orchestrator class which represents the main activities of a single backgammon game.
 * Is used to update the scores of the players in the normal execution of a match
 */

public class BackgammonGame {
    private Board board;
    private Die die1;
    private Die die2;
    private EventLog eventLog;
    private ArrayList<Player> players;
    private int matchLength;
    private boolean supressRollPrint = false;
    
    public BackgammonGame(int matchLength){
        this.players = new ArrayList<Player>();
        this.board = new Board();
        this.die1 = new Die();
        this.die2 = new Die();
        this.eventLog = new EventLog();
        this.matchLength = matchLength;
    }

    /** 
     * @return boolean - flag which is only true when the die are rolled to find the first player so that the "You rolled N-N" line is not printed
     */
    public boolean getSupressRollPrint(){
        return this.supressRollPrint;
    }
    
    /** 
     * @return Player - the current active player which can take actions
     */
    public Player getCurrentPlayer(){
        return this.players.get(0);
    }

    
    /** 
     * @return int - returns the entered length of the match being played
     */
    public int getMatchLength(){
        return this.matchLength;
    }

    
    /** 
     * @return ArrayList<Player> - returns the list of players in the game
     */
    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    
    /** 
     * @return Die - the first die
     */
    public Die getDie1(){
        return this.die1;
    }

    
    /** 
     * @return Die - the second die
     */
    public Die getDie2(){
        return this.die2;
    }

    
    /** 
     * @return Board - returns the board instance used in the game 
     */
    public Board getBoard(){
        return this.board;
    }

    
    /** 
     * @return EventLog - returns the log of game events
     */
    public EventLog getEventLog(){
        return this.eventLog;
    }

    
    /** 
     * @return boolean - whether the game is over or not
     */
    public boolean checkGameOver() {
        boolean gameOver = false;

        // check if pip scores are 0 for either player 
        if(this.board.getPipScore(players.get(0).getColor()) == 0){
            BackgammonGameView.declareWinner(players.get(0));
            
            Match.updatePlayersMatchScore(players.get(0), this);
            gameOver = true;
        }
        else if(this.board.getPipScore(players.get(1).getColor()) == 0){
            BackgammonGameView.declareWinner(players.get(1));
            Match.updatePlayersMatchScore(players.get(1), this);
            gameOver = true;
        }

        // if the game is indeed over, declare if the win was a single, gammon or backgammon
        if (gameOver)
        {
            String endgameString = "";
            switch (this.board.getEndgameMultiplier())
            {
                case 1: endgameString = "Single";break;
                case 2: endgameString = "Gammon";break;
                case 3: endgameString = "Backgammon";break;
            }
            BackgammonGameView.declareGameEndingType(endgameString);
        }
        
        return(gameOver);
    }

    
    /** 
     * @return boolean - returns false if the players cannot be swapped, and true if the swap is complete
     */
    public boolean swapPlayers()
    {
        if (players.size() < 2)
        {
            return false;
        }
        Player temp = players.get(0);
        players.set(0, players.get(1));
        players.set(1, temp);
        return true;
    }

    
    /** 
     * Doubling cube method
     * @param in - input scanner
     * @return boolean - returns true if the game should end, or false if it should continue
     */
    public boolean doubleStakes(MultiScanner in)
    {
        boolean concealed = true;
        if (board.getCube().canDoubleStakes(this.getCurrentPlayer().getColor()))
        {
            if (BackgammonGameView.promptForDouble(in, this.getPlayers().get(1)))
            {
                board.getCube().doubleStakes(this.getCurrentPlayer().getColor());
                this.eventLog.logEvent(this.getCurrentPlayer().getName() + ": doubles the stakes to "+ this.board.getCube().getCurrentStake() );
                concealed = false;
            }
            else //player has rejected the double offer
            {
                this.board.autoWin(this.getCurrentPlayer().getColor());
                this.eventLog.logEvent(this.getCurrentPlayer().getName() + ": autowins");
            }
        }
        else  //the player cannot double the stakes
        {
            System.out.println(Checker.Color.RED+"Error: cannot double the stakes"+Checker.Color.BLACK);
            concealed = false;
        }
        return concealed;
    }

    
    /** 
     * Method invoked to act upon user commands in-game
     * @param userInput - input string from user 
     * @param in - input scanner
     * @return boolean - returns true if the turn of the current player has ended and vice versa
     */
    public boolean takeAction(String userInput, MultiScanner in){
        boolean endTurn = false;
        userInput = userInput.toLowerCase();

        if(userInput.equals("roll") || userInput.matches("dice [1-6] [1-6]")){
            int madeMove;

            if (userInput.equals("roll"))
            {
                this.players.get(0).roll(this.die1, this.die2);
            }
            else if(userInput.matches("dice [1-6] [1-6]"))   // a roll was forced (cheat)
            {
                this.die1.setLastRoll((int) userInput.charAt(5) - '0');
                this.die2.setLastRoll((int) userInput.charAt(7) - '0');
                this.players.get(0).forceRoll(die1, die2);
            }

            this.eventLog.logEvent(this.getCurrentPlayer().getName() + ": " + DieView.display(die1) + " - " + DieView.display(die2) + " rolled " + this.die1.getLastRoll() + " and " + this.die2.getLastRoll());
            
            //based on the roll, get all possible moves 
            ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> possibleMoves = board.getAllPossibleMovesWrapper(this.getCurrentPlayer().getColor(), this.getCurrentPlayer().getMoves());
            
            if(possibleMoves.size() > 0){
                madeMove = BackgammonGameView.promptForMove(in, possibleMoves, this);
                for(int i=0; i<possibleMoves.get(madeMove).size(); i++){
                    this.board.makeMove(possibleMoves.get(madeMove).get(i), this.getCurrentPlayer().getColor());
                }
                this.eventLog.logEvent(this.getCurrentPlayer().getName() + " chose move: " + BackgammonGameView.genKeyCode(madeMove));
            }
            else{  // the player has no available moves
                this.eventLog.logEvent(this.getCurrentPlayer().getName() + " cannot make any moves. Switching turns. ");
                BackgammonGameView.noMoves();
            }
            endTurn = true; //switch turns
        }
        else if(userInput.toLowerCase().equals("pip")){
            //do not switch turns
            BackgammonGameView.showPipScores(this);
        }
        else if(userInput.toLowerCase().equals("hint")){
            //do not switch turns
            BackgammonGameView.showHint();
        }
        else if(userInput.toLowerCase().equals("double"))
        {
            // use doubling cube method to offer double
            return this.doubleStakes(in);
        }
        
        return endTurn; //do not switch turns
    }

     /** 
     * procedure used in the beginning of a game to roll for each player to determine who goes first. no return.
     */
    public void rollOff(){
        // if th edie rolls result in the same value, roll until they are different
        while(this.die1.getRollValue() == this.die2.getRollValue());

        this.eventLog.logEvent(this.players.get(0).getName() + " rolled: " + this.die1.getLastRoll());
        this.eventLog.logEvent(this.players.get(1).getName() + " rolled: " + this.die2.getLastRoll());
        if(this.die1.getLastRoll() > this.die2.getLastRoll()){
            this.eventLog.logEvent(this.players.get(0).getName() + " goes first!");
        }
        else{
            this.eventLog.logEvent(this.players.get(1).getName() + " goes first!");
            this.swapPlayers();
        }
        this.supressRollPrint = true; // prevent the "you rolled N-N" line from printing
    }
    
    
    /** 
     * Procedure to set up the game when it is first started
     * @param in - input scanner
     * @param matchPlayers - players passed from match object
     */
    public void setUpSequence(MultiScanner in, ArrayList<Player> matchPlayers){
        this.players = matchPlayers;
        this.eventLog.logEvent("Player 1: " + players.get(0).getName() + ", is BLACK checkers");
        this.eventLog.logEvent("Player 2: " + players.get(1).getName() + ", is RED checkers");
        this.rollOff();
        BackgammonGameView.display(this, true, true);
        this.supressRollPrint = false;  //print the "you rolled N-N" line from this point onwards 
    }

    
    /** 
     * Method used to check user move input
     * @param userInput - string of user input
     * @param numPossibleMoves - the number of possible unique moves, each mapped to a key code
     * @return boolean - true if input is valid
     */
    public boolean validateMoveInput(String userInput, int numPossibleMoves){
        // user moves are of the type "AA", "AB" to "ZZ"
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
        else if(BackgammonGameView.reverseKeyCode(userInput.toUpperCase()) >= numPossibleMoves){
            System.out.println("Please select a key code from the ones shown.");
            return false;
        }
        return true;
    }

    
    /** 
     * Method used to check user command validity
     * @param userInput - string user input
     * @return boolean - returns true if valid
     */
    public boolean validateInput(String userInput){
        userInput = userInput.toLowerCase();
        if(!userInput.equals("roll") && !userInput.equals("pip") && !userInput.equals("hint") && !userInput.equals("double") &&!userInput.matches("dice [1-6] [1-6]")  && !userInput.toLowerCase().matches("[t][e][s][t]\\s[a-z\\d]+.txt")){
            System.out.println("Please ensure you enter a valid command, try again.");
            return false;
        }
        return true;
    }

    
    /** 
     * Main orchestrator method where the game logic is called (facade-like pattern for backgammon game logic)
     * @param in - input scanner
     * @param matchPlayers - players from match object
     * @param game - game instance
     * @return ArrayList<Player> - returns match players with updated scores
     */
    public ArrayList<Player> gameRoutine(MultiScanner in, ArrayList<Player> matchPlayers, BackgammonGame game)
    {
        game.setUpSequence(in, matchPlayers);

        while((!game.checkGameOver())){
            while(!game.takeAction(BackgammonGameView.readNewInput(in, game.getCurrentPlayer(), game), in));
            game.swapPlayers();
            BackgammonGameView.display(game, true, true);
        }

        return this.players;
    }
}
