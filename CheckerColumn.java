/**
 * This program is the CheckerColumn class, which represents the array of checkers
 * @version 1 2022-21-11
 * @author Yevhenii Mormul
 */

import java.util.*;

/**
 * A {@code CheckerColumn} object represents the checker
 */
public abstract class CheckerColumn {
    private ArrayList<Checker> checkers;

    public CheckerColumn()
    {
        this.checkers = new ArrayList<Checker>();
    }

    public CheckerColumn(CheckerColumn newColumn)
    {
        this.checkers = newColumn.getCheckersCopy();
    }

    
    /** 
     * @return ArrayList<Checker> - copy of checkers in the CheckerColumn
     */
    public ArrayList<Checker> getCheckersCopy() {
        ArrayList<Checker> checkers = new ArrayList<Checker>();
        for (int ind = 0; ind < this.getSize(); ind++)
        {
            Checker newChecker = new Checker(this.checkers.get(ind).getColor());
            checkers.add(newChecker);
        }
        return checkers;
    }
    
    /** 
     * @return ArrayList<Checker> - checkers in the current array
     */
    public ArrayList<Checker> getCheckers() {
        return this.checkers;
    }

    
    /** 
     * @param color - remove all checkers of a particular color from the CheckerColumn
     */
    public void removeAllCheckersColor(Checker.Color color)
    {
        int ind = 0;
        while (ind<this.checkers.size())
        {
            if(this.checkers.get(ind).getColor() ==color)
            {
                this.checkers.remove(ind);
            }
            else
            {
                ind++;
            }
        }
    }

    
    /** 
     * @return int - length of the CheckerColumn
     */
    public int getSize()
    {
        return this.checkers.size();
    }

    
    /** 
     * @return Color - color of the current checker column, invalid if empty
     */
    public Checker.Color getColor()
    {
        if (this.getSize()>0)
        {
            return checkers.get(0).getColor();
        }
        
        return Checker.Color.INVALID;
    }

    
    /** 
     * @return Checker
     */
    public Checker pop()
    {
        if (this.getSize()>0)
        {
            return checkers.remove(this.getSize()-1);
        }
        return new Checker(Checker.Color.INVALID);
    }

    
    /** 
     * @param ind - index of the checker to get from CheckerColumn
     * @return Checker - checker returned, invalid by default
     */
    public Checker get(int ind)
    {
        if (this.getSize()>ind && ind>=0)
        {
            return checkers.get(ind);
        }
        return new Checker(Checker.Color.INVALID);
    }

    
    /** 
     * @param ind - checker to be poped
     * @return Checker - poped checker, invalid by default
     */
    public Checker pop(int ind)
    {
        if (this.getSize()>ind && ind>=0)
        {
            return checkers.remove(ind);
        }
        return new Checker(Checker.Color.INVALID);
    }

    
    /** 
     * @return Checker - Checker at the top of the Checker Column, invalid by default
     */
    public Checker getTop()
    {
        if (this.getSize()>0)
        {
            return checkers.get(this.getSize()-1);
        }
        return new Checker(Checker.Color.INVALID);
    }

    
    /** 
     * @param checker - checker to be appended
     * @return boolean - true if successful, false if invalid checker is passed
     */
    public boolean append(Checker checker)
    {
        if (checker.getColor() == Checker.Color.INVALID)
        {
            return false;
        }
        try
        {
            this.checkers.add(checker);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    
    /** 
     * @param destination - destination to move the checker column to
     * @return boolean - true, if successful
     */
    public boolean moveTo(CheckerColumn destination)
    {
        if (this.getColor()==destination.getColor() || destination.getColor()==Checker.Color.INVALID)
        {
            Checker popped = this.pop();
            if (popped.getColor() != Checker.Color.INVALID)
            {
                return destination.append(popped);
            }
        }
        return false;
    }
}
