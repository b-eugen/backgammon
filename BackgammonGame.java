import java.util.Scanner;
import java.util.ArrayList;

// cd ~/Desktop/Java && javac backgammon/*.java && java backgammon/BackgammonGame && cd ~/Desktop/Java/backgammon
public class BackgammonGame {
    private boolean gameOver = false;
    private Board board;
    private Die die1;
    private Die die2;
    private ArrayList<Player> players;
    
    public BackgammonGame(){
        this.players = new ArrayList<Player>();
        this.board = new Board();
        this.die1 = new Die();
        this.die2 = new Die();
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
        if (players.size()<2)
        {
            return false;
        }
        Player temp = players.get(0);
        players.set(0, players.get(1));
        players.set(1, temp);
        return true;
    }

    public Player getCurrentPlayer(){
        return this.players.get(0);
    }

    public void immediateExit(){
        System.out.println("\n\nYou have quit backgammon.");
        System.exit(1);
    }

    public boolean parseInput(String userInput, boolean takeName){
        if(userInput.toLowerCase().equals("quit")){
            this.immediateExit();
        }
        if(takeName){
            if(userInput.length() > 15){
                System.out.println("Please ensure you name includes no more than 15 characters");
                return false;
            }
            else if(userInput.length() < 1){
                System.out.println("Please ensure you name includes no less than 1 character");
                return false;
            }
            else{
                this.players.get(0).setName(userInput);
            }
        }
        else{
            userInput = userInput.toLowerCase();
            if(!userInput.equals("roll")){
                System.out.println("Please ensure you enter a valid command, try again.");
                return false;
            }
            else{
                this.players.get(0).sumRoll(this.die1, this.die2);
            }
        }
        return true;
    }
    
    public void setUpSequence(Scanner in){
        System.out.println("\nWelcome To Backgammon.");
        System.out.println("\nUsage Instructions: 'roll' to roll, 'quit' to quit.");

        this.addPlayer(new Player());
        System.out.println("\n\nPlayer 1, enter your name: ");
        while(!this.parseInput(in.nextLine(), true));
        
        this.addPlayer(new Player());
        this.swapPlayers();
        System.out.println("\n\nPlayer 2, enter your name: ");
        while(!this.parseInput(in.nextLine(), true));

        this.swapPlayers();
        System.out.println("\n" + this.board + "\n");
    }


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        BackgammonGame game = new BackgammonGame();
        game.setUpSequence(in);

        while(!game.getGameOver()){
            System.out.println("\n" + game.getCurrentPlayer().getName() + ", your turn. Enter a command:");
            while(!game.parseInput(in.nextLine(), false));
            game.swapPlayers();
        }
    }
}
