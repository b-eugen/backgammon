import java.util.Scanner;
import java.util.ArrayList;

// cd ~/Desktop/Java && javac backgammon/*.java && java backgammon/BackgammonGame && cd ~/Desktop/Java/backgammon
public class BackgammonGame {
    private boolean gameOver = false;
    private Board board;
    private ArrayList<Player> players;
    
    public BackgammonGame(){
        this.players = new ArrayList<Player>();
        this.board = new Board();
    }

    public boolean checkGameOver() {
        return(this.gameOver);
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


    public void endGame(){
        this.gameOver = true;
    }

    public void immediateExit(){
        System.out.println("\n\nYou have quit backgammon.");
        System.exit(1);
    }

    public boolean parseInput(String userInput, boolean takeName){
        userInput = userInput.toLowerCase();
        if(takeName){
            if(userInput.length() > 15){
                System.out.println("Please ensure you name includes no more than 15 characters");
                return false;
            }
            else{

            }
        }
        else{
            if(userInput != "quit" && userInput != "roll"){
                System.out.println("Please ensure you enter a valid command");
                return false;
            }
            else if(userInput == "quit"){
                this.immediateExit();
            }
            else if(userInput == "roll"){

            }
        }
        return true;
    }
    
    public void setUpSequence(Scanner in){
        System.out.println("\nWelcome To Backgammon.");
        System.out.println("\nUsage Instructions: 'roll' to roll, 'quit' to quit.");
        System.out.println("\n\nPlayer 1, enter your name: ");
        while(!this.parseInput(in.nextLine(), true));
        
        System.out.println("\n\nPlayer 2, enter your name: ");
        while(!this.parseInput(in.nextLine(), true));
    }


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        BackgammonGame game = new BackgammonGame();
        game.setUpSequence(in);
    }
}
