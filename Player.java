
import java.util.*;

public class Player {
    private String name;
    private Checker.Color color;
    private ArrayList<Integer> moves;

    public Player()
    {
        this.name = "Player";
        this.color = Checker.Color.BLACK;
        this.moves = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0));
    }

    public Player(Checker.Color color)
    {
        this.color = color;
        this.moves = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0));
    }

    public Player(String name, Checker.Color color)
    {
        this.name = name;
        this.color = color;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return this.name;
    }

    public Checker.Color getColor()
    {
        return this.color;
    }

    public ArrayList<Integer> getMoves()
    {
        return this.moves;
    }

    public boolean anyMovesLeft()
    {
        for (int move: moves)
        {
            if (move>0)
            {
                return true;
            }
        }
        return false;
    }

    public boolean popMove(int move)
    {
        for (int ind=0; ind < this.moves.size(); ind++)
        {
            if (move == this.moves.get(ind))
            {
                this.moves.set(ind, 0);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> roll(Die die1, Die die2)
    {
        int roll1 = die1.getRollValue();
        int roll2 = die2.getRollValue();
        System.out.println(this.getName() + ": " + die1 + " " + die2 + " rolls " + roll1 + " and " + roll2);
        
        if (roll2 == roll1){
            this.moves = new ArrayList<Integer>(Arrays.asList(roll1, roll1, roll1, roll1));
        }
        else{
            this.moves = new ArrayList<Integer>(Arrays.asList(roll1, roll2, 0, 0));
        }
        return this.moves;
    }

    public int sumRoll(Die die1, Die die2)
    {
        this.roll(die1, die2);
        return this.moves.get(0)+this.moves.get(1);
    }

    public String toString()
    {
        String outputString="";
        for (int move : this.moves)
        {
            if (move>0)
            {
                outputString = outputString+ " " +move;
            }
        }
        if (outputString.length() > 0) 
        {
            outputString = "  Possible moves:"+outputString;
        }
        else
        {
            outputString = "  No possible moves";
        }

        return "Player: "+this.getName() + new Checker(this.getColor())+ outputString;
    }
}
