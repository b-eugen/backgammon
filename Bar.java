
/**
 * This program is the Bar class, which extends the checker column
 * @version 1 2022-21-11
 * @author Yevhenii Mormul
 */
/**
 * A {@code Bar} object represents a bar
 */
public class Bar extends CheckerColumn{

    public Bar()
    {
        super();
    }

    public Bar(Bar newBar)
    {
        super(newBar);
    }

    
    /** 
     * @param color - color of the checkers, which are placed on the bar
     * @return int - number of checkers of the given color placed on the bar
     */
    public int colorCount(Checker.Color color)
    {
        int count = 0;
        for (Checker checker: this.getCheckers())
        {
            if (checker.getColor() == color)
            {
                count++;
            }
        }
        return count;
    }

    
    /** 
     * @return Color - representation of active destination color
     */
    public Checker.Color getColor()
    {
        return Checker.Color.INVALID;
    }

    
    /** 
     * @param color - color of the checker to be found on the bar
     * @return int - integer showing the position of the checker of the given color
     */
    public int getColorIndex(Checker.Color color)
    {
        int popInd = -1;
        for (int ind=0; ind < this.getSize(); ind++)
        {
            if (this.get(ind).getColor() == color)
            {
                popInd = ind;
                break;
            }
        }
        return popInd;
    }

    
    /** 
     * @param color - color to be removed
     * @return boolean - flag indicating success/failure
     */
    public boolean removeColor(Checker.Color color)
    {
        
        int popInd = getColorIndex(color);
        boolean result = false;
        if (popInd>=0)
        {
            this.pop(popInd);
            result = true;
        }
        return result;
    }

    
    /** 
     * @param destination - desitnation checker column (can be bar or point)
     * @param color - color of the checker
     * @return boolean - flag indicating the success/failure
     */
    public boolean moveTo(CheckerColumn destination, Checker.Color color)
    {
        int popInd = getColorIndex(color);
        if (popInd>=0)
        {
            if (color==destination.getColor() || destination.getColor()==Checker.Color.INVALID)
            {
                Checker popped = this.pop(popInd);
                if (popped.getColor() != Checker.Color.INVALID)
                {
                    return destination.append(popped);
                }
            }
        }
        return false;
    }
}
