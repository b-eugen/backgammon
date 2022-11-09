import java.util.Random;

public class Die {
    private static final int MAX_DICE = 6;
    private int lastRoll = 1;

    public int getLastRoll(){
        return this.lastRoll;
    }

    public int getRollValue(){
        Random rand = new Random();
        this.lastRoll = rand.nextInt(MAX_DICE-1)+1;
        return this.lastRoll;
    }
}
