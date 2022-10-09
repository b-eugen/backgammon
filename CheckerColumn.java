package backgammon;

import java.util.*;

public abstract class CheckerColumn {
    private ArrayList<Checker> checkers;

    public CheckerColumn()
    {
        this.checkers = new ArrayList<Checker>();
    }

    public int getSize()
    {
        return checkers.size();
    }

    public Checker.Color getColor()
    {
        if (this.getSize()>0)
        {
            return checkers.get(0).getColor();
        }
        
        return Checker.Color.INVALID;
    }

    public Checker pop()
    {
        if (this.getSize()>0)
        {
            return checkers.remove(this.getSize()-1);
        }
        return new Checker(Checker.Color.INVALID);
    }

    public Checker getTop()
    {
        if (this.getSize()>0)
        {
            return checkers.get(this.getSize()-1);
        }
        return new Checker(Checker.Color.INVALID);
    }

    public boolean append(Checker checker)
    {
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

    public String toString()
    {
        return String.format("%s%s", this.getSize(), this.getTop());
    }

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
