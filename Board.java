
import java.util.ArrayList;

import javax.print.attribute.standard.Destination;

public class Board {
    private Bar bar;
    private Point[] points;


    public Board()
    {
        this.bar = new Bar();
        this.points = new Point[Point.MAX_POINTS];
        for (int ind=0; ind<Point.MAX_POINTS; ind++)
        {
            this.points[ind]=new Point(ind+1);
            this.points[ind].prepareForGame();
        }

    }

    public Bar getBar() {
        return bar;
    }
   
    public Point[] getPoints() {
        return points;
    }
    
    public boolean moveColumns(int source, int destination)
    {
        destination--;
        source--;
        boolean result = false;
        if (points[destination].getColor() == Checker.Color.INVALID || points[destination].getColor() == points[source].getColor())
        {
            result = points[source].moveTo(points[destination]);
        }
        else if (points[destination].getSize()==1)//blob
        {
            result = points[destination].moveTo(bar);
            if (result)
            {
                result = points[source].moveTo(points[destination]);
            }
            
        }
            
        return result;
    }

    public boolean moveFromBar(int destination, Checker.Color color)
    {
        destination--;
        boolean result = false;

        if (points[destination].getColor() == Checker.Color.INVALID || points[destination].getColor() == color)
        {
            result = bar.moveTo(points[destination]);
        }
        else if (points[destination].getSize()==1)//blob
        {
            result = points[destination].moveTo(bar);
            if (result)
            {
                result = bar.moveTo(points[destination], color);
            }
            
        }


        return result;

    }
    /*
     * Method which concantenates 2 arrays of strings
     * @param base - first string array
     * @param appendable - second string array
     * @return - result of array concatentations
     */
    public static String[] concatStringArrays(String[] base, String[] appendable)
    {
        String[] result = new String[base.length+appendable.length];
        System.arraycopy(base, 0, result, 0, base.length);
        System.arraycopy(appendable, 0, result, base.length, appendable.length);
        return result;
    }

    public String toString()
    {
        String[] barString = this.bar.toArrayOfStrings();

        String[][] array2D = new String[Point.MAX_POINTS/2+1][barString.length];

        String[] stub = {"          "};
        for (int col=0; col<Point.MAX_POINTS/2+1; col++)
        {
            if (col < Point.MAX_POINTS/4)
            {
                array2D[col] = concatStringArrays(concatStringArrays(points[Point.MAX_POINTS/2+col].toArrayOfStrings(), stub), points[Point.MAX_POINTS/2-col-1].toArrayOfStrings());
            }
            else if (col == Point.MAX_POINTS/4)
            {
                array2D[col] = barString;
            }
            else{
                array2D[col] = concatStringArrays(concatStringArrays(points[Point.MAX_POINTS/2+col-1].toArrayOfStrings(), stub), points[Point.MAX_POINTS/2-col].toArrayOfStrings());
            }
            
        }

        String result = "";
        for (int row=0; row<barString.length; row++)
        {
            for (int col=0; col<Point.MAX_POINTS/2+1; col++)
            {
                result = result+array2D[col][row];
            }
            result = result+"\n";
        }
        return result;
        
    }
}
