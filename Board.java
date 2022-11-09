import java.util.AbstractMap;
import java.util.ArrayList;
// import java.util.Pair;

import javax.print.attribute.standard.Destination;


// import javax.print.attribute.standard.Destination;

// import Checker.Color;
// import javafx.util.Pair;

public class Board {
    private Bar bar;
    private Point[] points;

    private static final int BEAR_OFF_THOLD=6;
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
    
    public int getPipScore(Checker.Color playerColor)
    {
        int count = 0;
        for (int ind = 0; ind < Point.MAX_POINTS; ind++)
        {
            if (points[ind].getColor() == playerColor)
            {
                count += points[ind].getSize()*mapToPip(ind+1, playerColor);
            }
        }
        return count;
    }

    public boolean makeMove(AbstractMap.SimpleEntry<Integer,Integer> move, Checker.Color playerColor)
    {
        boolean result = false;
        if (move.getKey()==Point.MAX_POINTS+1)
        {  
            result = this.moveFromBar(mapFromPip(move.getValue(), playerColor), playerColor);

        }
        else if (move.getValue()==0)
        {
            Checker checker = points[mapFromPip(move.getKey(), playerColor)-1].getTop();
            if (checker.getColor() == playerColor)
            {
                points[mapFromPip(move.getKey(), playerColor)-1].pop();
                result = true;
            }
        }
        else
        {
            result = this.moveColumns(mapFromPip(move.getKey(), playerColor), mapFromPip(move.getValue(), playerColor));
        }

        return result;
    }

    public ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> getPossibleMoves(Checker.Color playerColor, ArrayList<Integer> moves)
    {
        

        //find unique moves in the provided list
        ArrayList<Integer> uniqueMoves = new ArrayList<Integer>();
        for (int ind=0; ind<moves.size(); ind++)
        {
            Boolean uniqueFlag = true;
            for (int jnd=0; jnd<uniqueMoves.size(); jnd++)
            {
                if (uniqueMoves.get(jnd) == moves.get(ind) || moves.get(ind)==0)
                {
                    uniqueFlag = false;
                    break;
                }
            }
            if (uniqueFlag)
            {
                uniqueMoves.add(moves.get(ind));
            }
        }

        ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> result = new ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>();

        for (int move: uniqueMoves)
        {
            if (bar.colorCount(playerColor)>0)
            {
                int pointIndex = mapFromPip(Point.MAX_POINTS+1-move, playerColor)-1;
                if (points[pointIndex].getColor()==Checker.Color.INVALID || points[pointIndex].getColor()==playerColor || points[pointIndex].getSize()<=1)
                {
                    //move
                    result.add(new AbstractMap.SimpleEntry<Integer,Integer>(Point.MAX_POINTS+1, Point.MAX_POINTS+1-move));
                }

            }
            else
            {
                boolean bear_off = true;
                for (int pip=1; pip<Point.MAX_POINTS+1; pip++)
                {
                    if (points[mapFromPip(pip, playerColor) -1].getColor() == playerColor)
                    {
                        int destination = pip-move;
                        int destinationIndex = mapFromPip(destination, playerColor) -1;

                        // System.out.println("Debug"+ pip+" "+ move +" " + destination);

                        if (destination>0)
                        {
                            if (points[destinationIndex].getColor() == Checker.Color.INVALID || points[destinationIndex].getColor()==playerColor || points[destinationIndex].getSize()<=1)
                            {
                                //move
                                result.add(new AbstractMap.SimpleEntry<Integer,Integer>(pip, destination));
                                Checker checktest = new Checker(playerColor);
                                
                                System.out.println(pip +" " +destination +" "+ checktest +" "+ destinationIndex);
                            }
                        }
                        
                        if (pip>BEAR_OFF_THOLD) 
                        {
                            bear_off=false;
                        }
                        // break;
                    }
                }

                if (bear_off)
                {
                    int pointIndex = mapFromPip(move, playerColor)-1;
                    if ( points[pointIndex].getColor()==playerColor)
                    {
                        result.add(new AbstractMap.SimpleEntry<Integer,Integer>(move, 0));
                        //move
                    }
                }
                // else
                // {


                // }
            }

        }

        
        return result;
    }

    public static int mapToPip(int index, Checker.Color playerColor)
    {
        if (playerColor == Checker.Color.RED)
        {
            index = Point.MAX_POINTS + 1 - index;
        }
        return index;
    }

    public static int mapFromPip(int pip, Checker.Color playerColor)
    {
        if (playerColor == Checker.Color.RED)
        {
            pip = Point.MAX_POINTS + 1 - pip;
        }
        return pip;
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
    

    
}
