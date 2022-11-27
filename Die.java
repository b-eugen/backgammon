/**
 * This program is the Die class, which represents the die
 * @version 1 2022-21-11
 * @author Aness Al-Qawlaq
 */
import java.util.Random;

/**
 * A {@code Die} object represents the die
 */
public class Die {
    private static final int MAX_DICE = 6;
    private int lastRoll = 1;

    
    /** 
     * @return int - value of the last roll
     */
    public int getLastRoll(){
        return this.lastRoll;
    }

    
    /** 
     * @return int - result of the new roll
     */
    public int getRollValue(){
        Random rand = new Random();
        this.lastRoll = rand.nextInt(MAX_DICE-1)+1;
        return this.lastRoll;
    }

    
    /** 
     * @param lastRoll - force the value of the last roll (cheat, only valid values are permitted, remains the same if invalid value is entered)
     * @return Die - current die with the value set tot the last roll
     */
    public Die setLastRoll(int lastRoll)
    {
        if (lastRoll>0 && lastRoll<=6)
        {
            this.lastRoll = lastRoll;
        }
        return this;
    }
}
