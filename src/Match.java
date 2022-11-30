/**
 * Represents a match
 * @version 1 2022-21-11
 * @author Aness Al-Qawlaq
 */

import java.util.ArrayList;

/**
 * {@code Match} is a class which models a match of multiple backgammon games
 */

public class Match {
    private ArrayList<Player> players;
    private int length;

    public Match(){
        this.players = new ArrayList<Player>();
    }

    /** 
     * method to set the length of the match, after which it should end 
     * @param lengthToSet - the score length
     */
    public void setLength(int lengthToSet){
        this.length = lengthToSet;
    }
    
    /** 
     * method which checks if the match is over or if another game needs to be played
     * @return boolean - true if game is over
     */
    public boolean checkMatchOver(){
        boolean matchOver = false;

        if(players.get(0).getScore() >= length){
            MatchView.declareMatchWinner(players.get(0), players.get(0).getScore());
            matchOver = true;
        }
        else if(players.get(1).getScore() >= length){
            MatchView.declareMatchWinner(players.get(1), players.get(1).getScore());
            matchOver = true;
        }
        return(matchOver);
    }

    
    /** 
     * method to update the score of players in the match
     * @param player - the player whose score is updated
     * @param game - the backgammon game
     */
    public static void updatePlayersMatchScore(Player player, BackgammonGame game){
        player.incrementScore(game.getBoard().getEndgameMultiplier() * game.getBoard().getCube().getCurrentStake());
    }

    /** 
     * method to set the names of the players in the match from the view input
     */
    public void setNames(MultiScanner in){
        String[] names = MatchView.getNames(in);
        this.players.add(new Player(names[0], Checker.Color.BLACK));
        this.players.add(new Player(names[1], Checker.Color.RED));
    }

    
    /** 
     * method to orchestrate match behaviour
     * @param in - input scanner
     */
    public void matchRoutine(MultiScanner in){
        MatchView.newMatchNotice();
        this.setLength(MatchView.askMatchLength(in));
        this.setNames(in);
        
        do{
            BackgammonGame game = new BackgammonGame(length);
            this.players = game.gameRoutine(in, this.players, game);
        }
        while(!checkMatchOver());
    }
    
}
