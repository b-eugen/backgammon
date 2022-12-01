/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * This program is the Player class, which represents the player
 * @version 1 2022-21-11
 * @author Yevhenii Mormul
 */

import java.util.*;
/**
 * A {@code Player} object represents the player
 */
public class Player {
    private String name;
    private Checker.Color color;
    private Checker displayCheckerColor;
    private ArrayList<Integer> moves;
    private int score;

    public Player()
    {
        this.score = 0;
        this.name = "Player";
        this.color = Checker.Color.BLACK;
        this.moves = new ArrayList<Integer>();
        this.displayCheckerColor = new Checker(this.color);
    }

    public Player(String name, Checker.Color color)
    {
        this.score = 0;
        this.moves = new ArrayList<Integer>();
        this.name = name;
        this.color = color;
        this.displayCheckerColor = new Checker(this.color);
    }

    
    /** 
     * @return int - current score of the player
     */
    public int getScore()
    {
        return this.score;
    }

    
    /** 
     * @return String - name of the player
     */
    public String getName()
    {
        return this.name;
    }

    
    /** 
     * @return Color - color of the player
     */
    public Checker.Color getColor()
    {
        return this.color;
    }

    
    /** 
     * @return ArrayList<Integer> - integer arraylist of possible moves
     */
    public ArrayList<Integer> getMoves()
    {
        return this.moves;
    }

    
    /** 
     * @param increment - value to increment the score by
     * @return int - new score of the player
     */
    public int incrementScore(int increment)
    {
        if (increment >0)
            this.score+=increment;
        return this.score;
    }

    
    /** 
     * @param roll1 - integer value of the first roll
     * @param roll2 - integer value of the second roll
     * @return ArrayList<Integer> - array list of possible moves
     */
    private ArrayList<Integer> processRoll(int roll1, int roll2)
    {
        if (roll2 == roll1){// double the number of moves, if 2 dices land with the same result
            this.moves = new ArrayList<Integer>(Arrays.asList(roll1, roll1, roll1, roll1));
        }
        else{//save as by default
            this.moves = new ArrayList<Integer>(Arrays.asList(roll1, roll2));
        }
        return this.moves;
    }
    
    /** 
     * @param die1 - first die
     * @param die2 - second die
     * @return ArrayList<Integer> - arraylist of possible moves, from the forced rolls (cheat)
     */
    public ArrayList<Integer> forceRoll(Die die1, Die die2)
    {
        int roll1 = die1.getLastRoll();
        int roll2 = die2.getLastRoll();
        return processRoll(roll1, roll2);
    }

    
    /** 
     * @param die1 - first die
     * @param die2 - second die
     * @return ArrayList<Integer> - arraylist of possible moves, from the real random rolls
     */
    public ArrayList<Integer> roll(Die die1, Die die2)
    {
        int roll1 = die1.getRollValue();
        int roll2 = die2.getRollValue();
        return processRoll(roll1, roll2);
    }

    
    /** 
     * @return Checker - checker of the current player
     */
    public Checker getDisplayCheckerColor(){
        return this.displayCheckerColor;
    }

    
    /** 
     * @return String - string representation of the current player
     */
    public String toString()
    {
        return "Player: "+this.getName() + new Checker(this.getColor())+" ";//+ outputString;
    }
}
