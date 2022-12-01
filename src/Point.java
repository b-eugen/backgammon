/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * This program is the Point class, which represents the point
 * @version 1 2022-21-11
 * @author Yevhenii Mormul
 */

/**
 * A {@code Point} object represents the point
 */
public class Point extends CheckerColumn{
    private int number;


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
     * @return int - number of the current point
     */
    public int getNumber()
    {
        return this.number;
    }

    /**
     * method, prepares the point for game putting a particular number of checkers into the point
     */
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
