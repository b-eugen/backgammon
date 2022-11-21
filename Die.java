import java.util.Random;

public class Die {
    private static final int MAX_DICE = 6;
    private int lastRoll = 1;

    
    /** 
     * @return int
     */
    public int getLastRoll(){
        return this.lastRoll;
    }

    
    /** 
     * @return int
     */
    public int getRollValue(){
        Random rand = new Random();
        this.lastRoll = rand.nextInt(MAX_DICE-1)+1;
        return this.lastRoll;
    }

    
    /** 
     * @param lastRoll
     * @return Die
     */
    public Die setLastRoll(int lastRoll)
    {
        this.lastRoll = lastRoll;
        return this;
    }
}
