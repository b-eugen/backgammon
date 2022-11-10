import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Board implements Serializable{
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

    // public Board deepClone() {
	// 	try {
    //     ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //     ObjectOutputStream oos = new ObjectOutputStream(baos);
    //     oos.writeObject(this);

    //     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    //     ObjectInputStream ois = new ObjectInputStream(bais);
    //     return (Board) ois.readObject();
	// 	} catch (IOException e) {
    //         System.out.println("Excepton"+ e);
	// 		return null;
	// 	} catch (ClassNotFoundException e) {
    //         System.out.println("Excepton"+ e);
	// 		return null;
	// 	}
	// }

    public String toString()
    {
        return "bar: "+bar+"; points:"+points;
    }

    public Board(Bar bar, Point[] points)
    {
        this.bar = bar;
        this.points = points;
    }

    public Board(Board board)
    {
        this.bar = new Bar(board.getBar());
        this.points = new Point[Point.MAX_POINTS];

        for (int ind=0; ind<Point.MAX_POINTS; ind++)
        {
            this.points[ind]=new Point(board.points[ind]);
        }
    }

    // @Override
    // public Object clone() {
        
    //     // try {
    //     //     return (Board) super.clone();
    //     // } catch (CloneNotSupportedException e) {
    //     //     return new Board(this.bar, this.points);
    //     // }
    // }

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

    

    public ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> getPossibleMovesFromOneRoll(Checker.Color playerColor, int move)
    {
        ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> result = new ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>();

        if (move>0 && move <7)
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
                                
                                // System.out.println(pip +" " +destination +" "+ checktest +" "+ destinationIndex);
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
                    for (int pointIndex = mapFromPip(move, playerColor)-1;pointIndex>=0; pointIndex--)
                    {
                        if ( points[pointIndex].getColor()==playerColor)
                        {
                            
                            result.add(new AbstractMap.SimpleEntry<Integer,Integer>(mapToPip(move+1, playerColor), 0));
                            break;
                        }
                    }
                    
                }
            }
        }
        return result;
    }
    
    public boolean isSame(ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> a, ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> b)
    {
        boolean result = true;
        ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> temp = new ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>();
        if (a.size() == b.size())
        {
            for (int ind = 0; ind < a.size(); ind++)
            {
                boolean matchedElement = false;
                int jnd = 0;
                for (jnd = 0; jnd < b.size(); jnd++)
                {
                    if(a.get(ind).getKey()==b.get(jnd).getKey() && a.get(ind).getValue()==b.get(jnd).getValue())
                    {
                        matchedElement=true;
                        break;
                    }
                }

                if (matchedElement)
                {
                    temp.add(b.remove(jnd));
                }
                else
                {
                    result = false;
                    break;
                }
            }
        }
        // b=a;
        b.addAll(temp);
        return result;
    }

    public ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> getUniqueLongArrays(ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> inputArray)
    {
        int maxLength = 0;
        for (ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> array : inputArray)
        {
            int currentSize = array.size();
            if (currentSize>maxLength)
            {
                maxLength = currentSize;
            }
        }
        ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> intermediateArray = new ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>>();
        
        for (ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> array : inputArray)
        {
            int currentSize = array.size();
            if (currentSize==maxLength)
            {
                intermediateArray.add(array);
            }
        }

        // System.out.println("intermediate "+intermediateArray);

        ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> outputArray = new ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>>();
        
       
        for (ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> arrayInIntermediate:intermediateArray)
        {
            boolean isPresent = false;
            for (ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> arrayInOutput:outputArray)
            {
                if (isSame(arrayInOutput, arrayInIntermediate))
                {
                    // System.out.println("Same: "+arrayInOutput +" "+  arrayInIntermediate);
                    isPresent= true;
                    break;
                }
            }
            if (!isPresent)
            {
                // System.out.println("Adding: "+  arrayInIntermediate);
                outputArray.add(arrayInIntermediate);
                // System.out.println("outputArray: " +outputArray);
            }
        }



        return outputArray;
    }

    public ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>>  getAllPossibleMovesWrapper(Checker.Color playerColor, ArrayList<Integer> moves)
    {
        ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> output = new  ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>>();
        ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> result = getAllPossibleMoves( playerColor, moves);
        if (result.size()>0)
        {
            if (result.get(0).size() == 1)
            {
                int max_move = moves.get(1);
                if (moves.get(2) > max_move)
                {
                    max_move = moves.get(2);
                }

                for (ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> move : result)
                {
                    if (move.get(0).getValue() == 0 || (move.get(0).getKey() - move.get(0).getValue()) == max_move)
                    {
                        output.add(move);
                    }
                }
            }
        }
        return output;
    }

    public ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>>  getAllPossibleMoves(Checker.Color playerColor, ArrayList<Integer> moves)
    {
        ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> result = new ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>>();
        for (int ind=0; ind<moves.size(); ind++)
        {
            ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> result_single = new ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>();
            // System.out.println("Size moves: "+ getPossibleMovesFromOneRoll(playerColor, moves.get(ind)).size());
            result_single.addAll(getPossibleMovesFromOneRoll(playerColor, moves.get(ind)));

            // System.out.println("result_single "+result_single);
            ArrayList<Integer> moves_copy = new ArrayList<Integer>();
            for (int jnd=0; jnd<moves.size(); jnd++)
            {
                if (ind != jnd)
                {
                    moves_copy.add(moves.get(jnd));
                }
            }
            
            for (int knd=0; knd < result_single.size(); knd++)
            {
                Board cloneBoard = new Board(this);//(Board) this.clone();
                
                cloneBoard.makeMove(result_single.get(knd), playerColor);
                ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> previous_result = cloneBoard.getAllPossibleMoves(playerColor, moves_copy);
                if (previous_result.size() > 0)
                {
                    for (int lnd = 0; lnd<previous_result.size(); lnd++)
                    {
    
                        
                        previous_result.get(lnd).add(0, result_single.get(knd));
                        
                    }
                    result.addAll(previous_result);
                }
                else
                {
                    ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> fillerArray = new ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>();
                    fillerArray.add(result_single.get(knd));
                    result.add(fillerArray);
                }
                
            }
        }
        

        return getUniqueLongArrays(result);
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
            result.addAll(getPossibleMovesFromOneRoll(playerColor, move));
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
    

    public int getEndGameMultiplierColor(Checker.Color color)
    {
        int multiplier = 2;//gammon

        int trippleCount = bar.colorCount(color);
        int checkerCount = trippleCount;

        for (int pip=1; pip < Point.MAX_POINTS+1; pip++)
        {
            if (points[mapFromPip(pip, color)].getColor()==color)
            {
                checkerCount+=points[mapFromPip(pip, color)].getSize();
                if (pip > Point.MAX_POINTS-BEAR_OFF_THOLD)
                {
                    trippleCount += points[mapFromPip(pip, color)].getSize();
                }
            }
        }

        if (checkerCount < Point.START_CHECKERS)
        {
            multiplier = 1;//single
        }
        else if(trippleCount > 0)
        {
            multiplier = 3;//backgammon
        }
        
        return multiplier;
    }

    // to be tested
    public int getEndgameMultiplier()
    {
        int blackPip = getPipScore(Checker.Color.BLACK);
        int redPip = getPipScore(Checker.Color.RED);
        int multiplier = 0;
        if (redPip == 0)
        {
            multiplier = getEndGameMultiplierColor(Checker.Color.BLACK);
        }
        else if (blackPip == 0)
        {
            multiplier = getEndGameMultiplierColor(Checker.Color.RED);
        }

        return multiplier;
    }
    


    
}
