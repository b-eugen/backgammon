
public class Bar extends CheckerColumn{

    public Bar()
    {
        super();
    }

    public String toString()
    {
        return String.format("Bar %s", super.toString());
    }

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

    public Checker.Color getColor()
    {
        return Checker.Color.INVALID;
    }

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

    public String[] toArrayOfStrings()
    {
        return new String[] {"|-----|",
        // return new String[] {"|‾‾‾‾‾|",
                            "|     |", 
                            "|     |",
                            String.format("|  %s  |", new Checker(Checker.Color.RED)), 
                            String.format("|%3d  |", this.colorCount(Checker.Color.RED)), 
                            "| Bar |", 
                            String.format("|  %s  |", new Checker(Checker.Color.BLACK)), 
                            String.format("|%3d  |", this.colorCount(Checker.Color.BLACK)), 
                            "|     |",
                            "|     |",
                            "|_____|"};
    }
}
