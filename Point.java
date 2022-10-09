package backgammon;

public class Point extends CheckerColumn{
    private int number;
    private static final String INTENSE_GREEN="\033[0;92m";
    private static final String NORMAL_GREEN="\033[0;32m";

    public static final int MAX_POINTS = 24;

    public Point(int number)
    {
        super();
        this.number = number;
    }

    public Point()
    {
        super();
        this.number = 1;
    }

    public String getDesignColor()
    {
        if (this.number%2 == 0)
        {
            return INTENSE_GREEN;
        }
        return NORMAL_GREEN;
    }

    public int getNumber()
    {
        return this.number;
    }


    public String toString()
    {
        return String.format("Point %s - %s", this.getNumber(), super.toString());
    }


    // _____________PRINTER_______________
    private String[] toArrayOfStringsTemplate()
    {
        String columnColor = this.getDesignColor();
        
        String[] array = {String.format("%s\\%sPoint %2d%s/%s", columnColor, Checker.Color.BLACK, this.getNumber(), columnColor, Checker.Color.BLACK),
                          String.format(" %s\\%s %2d%s  %s/%s ", columnColor, Checker.Color.BLACK,  super.getSize(), super.getTop(), columnColor, Checker.Color.BLACK),
                          String.format("  %s\\%s    %s/%s  ",  columnColor, Checker.Color.BLACK, columnColor, Checker.Color.BLACK),
                          String.format("   %s\\%s  %s/%s   ",  columnColor, Checker.Color.BLACK,  columnColor, Checker.Color.BLACK),
                          String.format("    %s\\/%s    ",  columnColor, Checker.Color.BLACK)};
        return array;
    }

    private String swapCharString(String line, char c1, char c2)
    {
        for(int ind=0; ind<line.length(); ind++)
        {
            if (line.charAt(ind)==c1)
            {
                line = line.substring(0, ind)+c2+line.substring(ind+1);
            }
            else if(line.charAt(ind)==c2)
            {
                line = line.substring(0, ind)+c1+line.substring(ind+1);
            }
        }
        return line;
    }

    private String[] toArrayOfStringsReversed()
    {
        String[] array = this.toArrayOfStringsTemplate();
        String[] outputArray = new String[array.length];

        for(int ind=0; ind<array.length; ind++)
        {
            outputArray[array.length-ind-1] = this.swapCharString(array[ind], '\\', '/');
        }
        return outputArray;
    }

    public String[] toArrayOfStrings()
    {
        if (number>MAX_POINTS/2)
        {
            return this.toArrayOfStringsTemplate();
        }
        return this.toArrayOfStringsReversed();
    }
}
