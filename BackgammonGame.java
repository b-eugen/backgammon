import java.util.Scanner;
import java.awt.Color;
import java.util.AbstractMap;
import java.util.ArrayList;

// cd ~/Desktop/Java && javac backgammon/*.java && java backgammon/BackgammonGame && cd ~/Desktop/Java/backgammon
public class BackgammonGame {
    private boolean gameOver = false;
    private Board board;
    private Die die1;
    private Die die2;
    private DieView dieView;
    private EventLog eventLog;
    private ArrayList<Player> players;
    
    public BackgammonGame(){
        this.players = new ArrayList<Player>();
        this.board = new Board();
        this.die1 = new Die();
        this.die2 = new Die();
        this.eventLog = new EventLog();
        this.dieView = new DieView();
    }

    public Player getCurrentPlayer(){
        return this.players.get(0);
    }

    public Die getDie1(){
        return this.die1;
    }

    public Die getDie2(){
        return this.die2;
    }

    public EventLog getEventLog(){
        return this.eventLog;
    }

    public boolean getGameOver() {
        return(this.gameOver);
    }

    public void endGame(){
        this.gameOver = true;
    }

    public boolean addPlayer(Player player)
    {
        if (players.size() < 2)
        {
            this.players.add(player);
            return true;
        }
        return false;
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
        return true;
    }


    public boolean takeAction(String userInput){
        if(userInput.toLowerCase().equals("quit")){
            BackgammonGameView.immediateExit();
            System.exit(1);
        }
        else{
            userInput = userInput.toLowerCase();
            this.players.get(0).sumRoll(this.die1, this.die2);
            ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> possibleMoves = board.getPossibleMoves(this.getCurrentPlayer().getColor(), this.getCurrentPlayer().getMoves());
            for (AbstractMap.SimpleEntry<Integer,Integer> pair : possibleMoves)
            {
                System.out.println(pair.getKey()+" "+pair.getValue());
            }
            this.eventLog.logEvent(this.getCurrentPlayer().getName() + ": " + DieView.toString(die1) + " - " + DieView.toString(die2) + " rolled " + this.die1.getLastRoll() + " and " + this.die2.getLastRoll());
        }
        return true;
    }
    
    public void setUpSequence(Scanner in){
        String names[] = BackgammonGameView.getNames(in);
        this.addPlayer(new Player(names[0], Checker.Color.BLACK));
        this.addPlayer(new Player(names[1], Checker.Color.RED));
        this.eventLog.logEvent("Player 1 entered name: " + names[0] + ", is BLACK checkers");
        this.eventLog.logEvent("Player 2 entered name: " + names[1] + ", is RED checkers");
        System.out.println("\n" + BoardView.display(this.board, Checker.Color.BLACK) + "\n");
    }

    public void updateDisplay(BackgammonGame game){
        System.out.println("\n" + BoardView.display(this.board, this.getCurrentPlayer().getColor()));
        BackgammonGameView.gameToString(game);
    }


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        BackgammonGame game = new BackgammonGame();
        game.setUpSequence(in);

        while(!game.getGameOver()){
            game.takeAction(BackgammonGameView.readNewInput(in, game.getCurrentPlayer()));
            game.swapPlayers();
            game.updateDisplay(game);
        }
    }
}
