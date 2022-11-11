import java.util.ArrayList;
import java.util.Scanner;

public class Match {
    private ArrayList<Player> players;
    private int length;

    public Match(){
        this.players = new ArrayList<Player>();
    }


    public boolean checkMatchOver(){
        boolean matchOver = false;

        if(players.get(0).getScore() >= length){
            MatchView.declareMatchWinner(players.get(0));
            matchOver = true;
        }
        else if(players.get(1).getScore() >= length){
            MatchView.declareMatchWinner(players.get(1));
            matchOver = true;
        }
        return(matchOver);
    }

    public static void updatePlayersMatchScore(Player player, BackgammonGame game){
        player.incrementScore(game.getBoard().getEndgameMultiplier() * game.getBoard().getCube().getCurrentStake());
    }

    public void matchRoutine(Scanner in){
        MatchView.newMatchNotice();
        this.length = MatchView.askMatchLength(in);

        String[] names = MatchView.getNames(in);
        this.players.add(new Player(names[0], Checker.Color.BLACK));
        this.players.add(new Player(names[1], Checker.Color.RED));
        
        do{
            BackgammonGame game = new BackgammonGame(length);
            this.players = game.gameRoutine(in, this.players, game, false);
        }
        while(!checkMatchOver());
    }
    
}
