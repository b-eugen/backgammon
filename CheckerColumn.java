
import java.util.*;
import java.io.Serializable;

public abstract class CheckerColumn {
    private ArrayList<Checker> checkers;

    public CheckerColumn()
    {
        this.checkers = new ArrayList<Checker>();
    }

    public CheckerColumn(ArrayList<Checker> checkers)
    {
        this.checkers = checkers;
    }

    public CheckerColumn(CheckerColumn newColumn)
    {
        this.checkers = newColumn.getCheckersCopy();
        // this(newColumn.getCheckers());
    }

    public ArrayList<Checker> getCheckersCopy() {
        ArrayList<Checker> checkers = new ArrayList<Checker>();
        for (int ind = 0; ind < this.getSize(); ind++)
        {
            Checker newChecker = new Checker(this.checkers.get(ind).getColor());
            checkers.add(newChecker);
        }
        return checkers;
    }
    public ArrayList<Checker> getCheckers() {
        return this.checkers;
    }

    public int getSize()
    {
        return this.checkers.size();
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

    public Checker get(int ind)
    {
        if (this.getSize()>ind && ind>=0)
        {
            return checkers.get(ind);
        }
        return new Checker(Checker.Color.INVALID);
    }

    public Checker pop(int ind)
    {
        if (this.getSize()>ind && ind>=0)
        {
            return checkers.remove(ind);
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
