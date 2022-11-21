
import java.util.*;

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
     * @return int
     */
    public int getScore()
    {
        return this.score;
    }

    
    /** 
     * @return String
     */
    public String getName()
    {
        return this.name;
    }

    
    /** 
     * @return Color
     */
    public Checker.Color getColor()
    {
        return this.color;
    }

    
    /** 
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getMoves()
    {
        return this.moves;
    }

    
    /** 
     * @param increment
     * @return int
     */
    public int incrementScore(int increment)
    {
        if (increment >0)
            this.score+=increment;
        return this.score;
    }

    
    /** 
     * @param roll1
     * @param roll2
     * @return ArrayList<Integer>
     */
    private ArrayList<Integer> processRoll(int roll1, int roll2)
    {
        if (roll2 == roll1){
            this.moves = new ArrayList<Integer>(Arrays.asList(roll1, roll1, roll1, roll1));
        }
        else{
            this.moves = new ArrayList<Integer>(Arrays.asList(roll1, roll2));
        }
        return this.moves;
    }
    
    /** 
     * @param die1
     * @param die2
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> forceRoll(Die die1, Die die2)
    {
        int roll1 = die1.getLastRoll();
        int roll2 = die2.getLastRoll();
        return processRoll(roll1, roll2);
    }

    
    /** 
     * @param die1
     * @param die2
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> roll(Die die1, Die die2)
    {
        int roll1 = die1.getRollValue();
        int roll2 = die2.getRollValue();
        // System.out.println(this.getName() + ": " + die1 + " " + die2 + " rolls " + roll1 + " and " + roll2);
        return processRoll(roll1, roll2);
    }

    
    /** 
     * @return Checker
     */
    public Checker getDisplayCheckerColor(){
        return this.displayCheckerColor;
    }

    
    /** 
     * @return String
     */
    public String toString()
    {
        // String outputString="";
        // for (int move : this.moves)
        // {
        //     if (move>0)
        //     {
        //         outputString = outputString+ " " +move;
        //     }
        // }
        // if (outputString.length() > 0) 
        // {
        //     outputString = "  Possible moves:"+outputString;
        // }
        // else
        // {
        //     outputString = "  No possible moves";
        // }

        return "Player: "+this.getName() + new Checker(this.getColor())+" ";//+ outputString;
    }
}
