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
     * @return int
     */
    public int getCurrentStake() 
    {
        return current_stake;
    }

    
    /** 
     * @return Color
     */
    public Checker.Color getOwner() 
    {
        return owner;
    }

    
    /** 
     * @param playerColor
     * @return boolean
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
     * @param playerColor
     * @return boolean
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
