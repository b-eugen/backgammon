
public class Point extends CheckerColumn{
    private int number;
    private static final String BLUE="\033[0;34m";
    private static final String GREEN="\033[0;32m";

    public static final int MAX_POINTS = 24;
    public static final int START_CHECKERS = 15;

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

    public Point(Point newPoint)
    {
        super(newPoint);
        this.number = newPoint.getNumber();
    }

    
    /** 
     * @return String
     */
    public String getDesignColor()
    {
        if (this.number%2 == 0)
        {
            return GREEN;
        }
        return BLUE;
    }

    
    /** 
     * @return int
     */
    public int getNumber()
    {
        return this.number;
    }

    public void prepareForGame()
    {
        switch (this.number)
        {
            case 1: for (int ind=0; ind <2; ind++){this.append(new Checker(Checker.Color.RED));} break;
            case 6: for (int ind=0; ind <5; ind++){this.append(new Checker(Checker.Color.BLACK));} break;
            case 8: for (int ind=0; ind <3; ind++){this.append(new Checker(Checker.Color.BLACK));} break;
            case 12: for (int ind=0; ind <5; ind++){this.append(new Checker(Checker.Color.RED));} break;
            case 13: for (int ind=0; ind <5; ind++){this.append(new Checker(Checker.Color.BLACK));} break;
            case 17: for (int ind=0; ind <3; ind++){this.append(new Checker(Checker.Color.RED));} break;
            case 19: for (int ind=0; ind <5; ind++){this.append(new Checker(Checker.Color.RED));} break;
            case 24: for (int ind=0; ind <2; ind++){this.append(new Checker(Checker.Color.BLACK));} break;
            default: break;
        }
        return;
    }

}
