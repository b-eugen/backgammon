package backgammon;

public class Bar extends CheckerColumn{

    public Bar()
    {
        super();
    }

    public String toString()
    {
        return String.format("Bar %s", super.toString());
    }

    public String[] toArrayOfStrings()
    {
        return new String[] {"| Bar |", String.format("|  %s  |", super.getTop()), String.format("|%3d  |", super.getSize())};
    }
}
