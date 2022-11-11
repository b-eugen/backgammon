import java.util.Scanner;
import java.awt.Color;
import java.util.AbstractMap;
import java.util.ArrayList;

// cd ~/Desktop/Java && javac backgammon/*.java && java backgammon/BackgammonGame && cd ~/Desktop/Java/backgammon
public class BackgammonGame {
    private Board board;
    private Die die1;
    private Die die2;
    private EventLog eventLog;
    private ArrayList<Player> players;
    private int matchLength;
    
    public BackgammonGame(int matchLength){
        this.players = new ArrayList<Player>();
        this.board = new Board();
        this.die1 = new Die();
        this.die2 = new Die();
        this.eventLog = new EventLog();
        this.matchLength = matchLength;
    }

    public Player getCurrentPlayer(){
        return this.players.get(0);
    }

    public int getMatchLength(){
        return this.matchLength;
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public Die getDie1(){
        return this.die1;
    }

    public Die getDie2(){
        return this.die2;
    }

    public Board getBoard(){
        return this.board;
    }

    public EventLog getEventLog(){
        return this.eventLog;
    }

    public boolean checkGameOver() {
        boolean gameOver = false;

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
        return(gameOver);
    }

    public void resetDice(){
        this.die1.setLastRoll(0);
        this.die2.setLastRoll(0);
    }

    public boolean swapPlayers()
    {
        if (players.size() < 2)
        {
            return false;
        }
        Player temp = players.get(0);
        players.set(0, players.get(1));
        players.set(1, temp);
        this.resetDice();
        return true;
    }

    public boolean doubleStakes(Scanner in)
    {
        boolean concealed = true;// true means game finishes, false continue
        if (board.getCube().canDoubleStakes(this.getCurrentPlayer().getColor()))
        {
            if (BackgammonGameView.promptFroDouble(in, this.getPlayers().get(1)))
            {
                board.getCube().doubleStakes(this.getCurrentPlayer().getColor());
                this.eventLog.logEvent(this.getCurrentPlayer().getName() + ": doubles the stakes to "+ this.board.getCube().getCurrentStake() );
                concealed = false;
            }
            else //conceal or autowin
            {
                this.board.autoWin(this.getCurrentPlayer().getColor());
                this.eventLog.logEvent(this.getCurrentPlayer().getName() + ": autowins");
            }
        }
        else
        {
            System.out.println(Checker.Color.RED+"Error: cannot double the stakes"+Checker.Color.BLACK);
            concealed = false;
        }
        return concealed;
    }

    public boolean takeAction(String userInput, Scanner in){
        boolean endTurn = false;
        userInput = userInput.toLowerCase();

        if(userInput.equals("roll") || userInput.matches("dice [1-6] [1-6]")){
            int madeMove;

            if (userInput.equals("roll"))
            {
                this.players.get(0).roll(this.die1, this.die2);
            }
            else if(userInput.matches("dice [1-6] [1-6]"))
            {
                this.die1.setLastRoll((int) userInput.charAt(5) - '0');
                this.die2.setLastRoll((int) userInput.charAt(7) - '0');
                this.players.get(0).forceRoll(die1, die2);
            }

            this.eventLog.logEvent(this.getCurrentPlayer().getName() + ": " + DieView.display(die1) + " - " + DieView.display(die2) + " rolled " + this.die1.getLastRoll() + " and " + this.die2.getLastRoll());
            
            ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> possibleMoves = board.getAllPossibleMovesWrapper(this.getCurrentPlayer().getColor(), this.getCurrentPlayer().getMoves());
            if(possibleMoves.size() > 0){
                madeMove = BackgammonGameView.promptForMove(in, possibleMoves, this);
                for(int i=0; i<possibleMoves.get(madeMove).size(); i++){
                    this.board.makeMove(possibleMoves.get(madeMove).get(i), this.getCurrentPlayer().getColor());
                }
                this.eventLog.logEvent(this.getCurrentPlayer().getName() + " chose move: " + BackgammonGameView.genKeyCode(madeMove));
            }
            else{
                this.eventLog.logEvent(this.getCurrentPlayer().getName() + " cannot make any moves. Switching turns. ");
            }
            endTurn = true; //switch turns
        }
        else if(userInput.toLowerCase().equals("pip")){
            BackgammonGameView.showPipScores(this);
            //do not switch turns
        }
        else if(userInput.toLowerCase().equals("hint")){
            BackgammonGameView.showHint();
            //do not switch turns
        }
        else if(userInput.equals("double"))
        {
            return this.doubleStakes(in);
            // return false;
        }
        
        return endTurn; //do not switch turns
    }

    public void rollOff(){
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
        resetDice();
    }
    
    public void setUpSequence(Scanner in, ArrayList<Player> matchPlayers){
        this.players = matchPlayers;
        this.eventLog.logEvent("Player 1 entered name: " + players.get(0).getName() + ", is BLACK checkers");
        this.eventLog.logEvent("Player 2 entered name: " + players.get(1).getName() + ", is RED checkers");
        this.rollOff();
        BackgammonGameView.display(this, true, true);
    }

    public boolean validateMoveInput(String userInput, int numPossibleMoves){
        if(userInput.toLowerCase().equals("quit")){
            Menu.immediateExit();
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
        else if(BackgammonGameView.reverseKeyCode(userInput.toUpperCase()) >= numPossibleMoves){
            System.out.println("Please select a key code from the ones shown.");
            return false;
        }
        return true;
    }

    public boolean validateInput(String userInput){
        if(userInput.toLowerCase().equals("quit")){
            Menu.immediateExit();
        }
        else{
            userInput = userInput.toLowerCase();
            if(!userInput.equals("roll") && !userInput.equals("pip") && !userInput.equals("hint") && !userInput.equals("double") &&!userInput.matches("dice [1-6] [1-6]")){
                System.out.println("Please ensure you enter a valid command, try again.");
                return false;
            }
        }
        return true;
    }

    public ArrayList<Player> gameRoutine(Scanner in, ArrayList<Player> matchPlayers, BackgammonGame game)
    {
        game.setUpSequence(in, matchPlayers);

        while(!game.checkGameOver()){
            while(!game.takeAction(BackgammonGameView.readNewInput(in, game.getCurrentPlayer(), game), in));
            game.swapPlayers();
            BackgammonGameView.display(game, true, true);
        }

        return this.players;
    }
}
