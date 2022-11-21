
import java.util.*;

public abstract class CheckerColumn {
    private ArrayList<Checker> checkers;

    public CheckerColumn()
    {
        this.checkers = new ArrayList<Checker>();
    }

    public CheckerColumn(CheckerColumn newColumn)
    {
        this.checkers = newColumn.getCheckersCopy();
        // this(newColumn.getCheckers());
    }

    
    /** 
     * @return ArrayList<Checker>
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
     * @return ArrayList<Checker>
     */
    public ArrayList<Checker> getCheckers() {
        return this.checkers;
    }

    
    /** 
     * @param color
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
     * @return int
     */
    public int getSize()
    {
        return this.checkers.size();
    }

    
    /** 
     * @return Color
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
     * @param ind
     * @return Checker
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
     * @param ind
     * @return Checker
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
     * @return Checker
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
     * @param checker
     * @return boolean
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
     * @param destination
     * @return boolean
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
