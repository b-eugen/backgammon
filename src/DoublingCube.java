/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * This program is the DoublingCube class, which represents the doubling cube
 * @version 1 2022-21-11
 * @author Yevhenii Mormul
 */
/**
 * A {@code DoublingCube} object represents the doubling cube
 */
public class DoublingCube {
    public static final int MAX_COEF = 64;
    int current_stake;
    Checker.Color owner;

    DoublingCube()
    {
        current_stake = 1;
        owner = Checker.Color.INVALID;
    }

    
    /** 
     * @return int - current stake on the doubling cube
     */
    public int getCurrentStake() 
    {
        return current_stake;
    }

    
    /** 
     * @return Color - color of the owner of the cube
     */
    public Checker.Color getOwner() 
    {
        return owner;
    }

    
    /** 
     * @param playerColor - color of the player who wants to double the stakes
     * @return boolean - true if can double the stakes
     */
    public boolean canDoubleStakes(Checker.Color playerColor)
    {
        boolean result = false;
        if ((playerColor == owner || owner==Checker.Color.INVALID) && current_stake!=MAX_COEF)
        {
            result= true;
        }
        return result;
    }

    
    /** 
     * @param playerColor - color of the player who wants to double the stakes
     * @return boolean - true if stakes were doubled successfully
     */
    public boolean doubleStakes(Checker.Color playerColor)
    {
        boolean result = canDoubleStakes(playerColor);
        if (result)
        {
            current_stake*= 2;
            if (playerColor == Checker.Color.BLACK)
            {
                owner = Checker.Color.RED;
            }
            else
            {
                owner = Checker.Color.BLACK;
            }
        }
        return result;
    }

    

}
