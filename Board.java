/**
 * This program is the board class, which is a board in backgammon game
 * @version 1 2022-21-11
 * @author Yevhenii Mormul
 */

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A {@code Board} object represents a board
 */
public class Board{
    private Bar bar;
    private Point[] points;
    private DoublingCube cube;

    private static final int BER_OFF_THOLD=6;
    public Board()
    {
        this.bar = new Bar();
        this.cube = new DoublingCube();
        this.points = new Point[Point.MAX_POINTS];
        for (int ind=0; ind<Point.MAX_POINTS; ind++)
        {
            this.points[ind]=new Point(ind+1);
            this.points[ind].prepareForGame();
            
        }

    }


    public Board(Board board)
    {
        this.cube = board.cube;
        this.bar = new Bar(board.getBar());
        this.points = new Point[Point.MAX_POINTS];

        for (int ind=0; ind<Point.MAX_POINTS; ind++)
        {
            this.points[ind]=new Point(board.points[ind]);
        }
    }

    
    /** 
     * @return DoublingCube
     */
    public DoublingCube getCube() {
        return cube;
    }

    
    /** 
     * @return Bar
     */
    public Bar getBar() {
        return bar;
    }
   
    
    /** 
     * @return Point[]
     */
    public Point[] getPoints() {
        return points;
    }
    
    
    /** 
     * @param playerColor -  color of the player to find the pip score for
     * @return int - pip score
     */
    public int getPipScore(Checker.Color playerColor)
    {
        int count = 25*bar.colorCount(playerColor);
        for (int ind = 0; ind < Point.MAX_POINTS; ind++)
        {
            if (points[ind].getColor() == playerColor)
            {
                count += points[ind].getSize()*mapToPip(ind+1, playerColor);
            }
        }
        
        return count;
    }

    
    /** 
     * @param move - Pair of ints, <source><destination>, representing a move on the board ot be made
     * @param playerColor - color of the active player
     * @return boolean - true if successful
     */
    public boolean makeMove(AbstractMap.SimpleEntry<Integer,Integer> move, Checker.Color playerColor)
    {
        boolean result = false;
        if (move.getKey()==Point.MAX_POINTS+1)//move from the bar
        {  
            result = this.moveFromBar(mapFromPip(move.getValue(), playerColor), playerColor);
        }
        else if (move.getValue()==0)//ber off
        {
            Checker checker = points[mapFromPip(move.getKey(), playerColor)-1].getTop();
            if (checker.getColor() == playerColor)
            {
                points[mapFromPip(move.getKey(), playerColor)-1].pop();
                result = true;
            }
        }
        else//move between points
        {
            result = this.moveColumns(mapFromPip(move.getKey(), playerColor), mapFromPip(move.getValue(), playerColor));
        }

        return result;
    }

    

    
    /** 
     * @param playerColor - color of the active player
     * @param move - integer representation of the dice roll
     * @return ArrayList<SimpleEntry<Integer, Integer>> - list of possible moves in form of integer Pair <source><destination>
     */
    public ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> getPossibleMovesFromOneRoll(Checker.Color playerColor, int move)
    {
        ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> result = new ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>();

        if (move>0 && move <7)//allow only legal moves
        {
            if (bar.colorCount(playerColor)>0)//find if any moves from bar are possible
            {
                int pointIndex = mapFromPip(Point.MAX_POINTS+1-move, playerColor)-1;
                if (points[pointIndex].getColor()==Checker.Color.INVALID || points[pointIndex].getColor()==playerColor || points[pointIndex].getSize()<=1)
                {
                    //move
                    result.add(new AbstractMap.SimpleEntry<Integer,Integer>(Point.MAX_POINTS+1, Point.MAX_POINTS+1-move));
                }
            }
            else //otherwise
            {
                boolean berOff = true;
                boolean berOffStrict = false;
                //attempt to move checkers between points
                for (int pip=1; pip<Point.MAX_POINTS+1; pip++)
                {
                    if (points[mapFromPip(pip, playerColor) -1].getColor() == playerColor)
                    {
                        int destination = pip-move;
                        int destinationIndex = mapFromPip(destination, playerColor) -1;


                        if (destination>0)
                        {
                            //possible move is found
                            if (points[destinationIndex].getColor() == Checker.Color.INVALID || points[destinationIndex].getColor()==playerColor || points[destinationIndex].getSize()<=1)
                            {
                                result.add(new AbstractMap.SimpleEntry<Integer,Integer>(pip, destination));
                                berOffStrict = true;//if moves between points are possible, only strict ber-off is possible

                            }
                        }
                        //if any checker beyond the ber-off region, ber off is forbidden
                        if (pip>BER_OFF_THOLD) 
                        {
                            berOff=false;
                        }
                    }
                }

                if (berOff)//attempt ber-off
                {
                    for (int pipIndex = move;pipIndex>0; pipIndex--)
                    {
                        int pointIndex2 = mapFromPip(pipIndex, playerColor)-1;
                        //ber-off the legal checker (if strict, only one at pip=move, otherwise, first point, which satisfies move>pip)
                        if ( points[pointIndex2].getColor()==playerColor && (!berOffStrict || berOffStrict && pipIndex == move))
                        {
                            result.add(new AbstractMap.SimpleEntry<Integer,Integer>(mapToPip(pointIndex2+1, playerColor), 0));
                            break;
                        }
                    }
                    
                }
            }
        }

        return result;//return a list of possible moves
    }
    
    
    /** 
     * @param a - ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> first array list to be compared
     * @param b - ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> first array list to be compared
     * @return boolean - true if 2 array lists of pairs are the same
     */
    public boolean isSame(final ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> a, final ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> b)
    {
        boolean result = true;

        if (a.size() == b.size())
        {
            ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> temp = new ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>();//copy of b
            for (int ind = 0; ind < b.size(); ind++)
            {
                temp.add( new AbstractMap.SimpleEntry<Integer,Integer>((int) b.get(ind).getKey(), (int) b.get(ind).getValue()));
            }
            //compare and eliminate element wise and elimitate matching elements (NlogN) complexity
            for (int ind = 0; ind < a.size(); ind++)
            {
                boolean matchedElement = false;
                int jnd = 0;
                for (jnd = 0; jnd < temp.size(); jnd++)
                {
                    if(a.get(ind).getKey()==temp.get(jnd).getKey() && a.get(ind).getValue()==temp.get(jnd).getValue())
                    {
                        matchedElement=true;
                        break;
                    }
                }

                if (matchedElement)//elimination of matched element
                {
                    temp.remove(jnd);
                }
                else//if no match for 1 element, means arraylists are different, can break the loop
                {
                    result = false;
                    break;
                }
            }
        }
        else//if sizes are different, arrays are different
        {
            result = false;
        }

        return result;
    }

    
    /** 
     * @param inputArray - ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> to be converted into set of unique arrays
     * @return ArrayList<ArrayList<SimpleEntry<Integer, Integer>>> - inputArray without matching elements
     */
    public ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> getUniqueLongArrays(ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> inputArray)
    {
        //eliminate all elements, which sre smaller than maximal in size
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


        ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> outputArray = new ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>>();
        
       //keep only the arrays, which are the same
        for (ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> arrayInIntermediate:intermediateArray)
        {
            boolean isPresent = false;
            for (ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> arrayInOutput:outputArray)
            {
                if (isSame(arrayInOutput, arrayInIntermediate))
                {
                    isPresent= true;
                    break;
                }
            }
            if (!isPresent)
            {
                outputArray.add(arrayInIntermediate);
            }
        }
        return outputArray;
    }

    
    /** 
     * @param playerColor - color of the active player
     * @param moves - ArrayList<Integer> list of possible moves
     * @return ArrayList<ArrayList<SimpleEntry<Integer, Integer>>> - Array list of arraylists of possible combinations of moves
     */
    public ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>>  getAllPossibleMovesWrapper(Checker.Color playerColor, ArrayList<Integer> moves)
    {
        ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> output = new ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>>();
        ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> result = getAllPossibleMoves( playerColor, moves); // find all possible moves

        if (result.size()>0)
        {
            if (result.get(0).size() == 1) // if size = 1, only moves which represent the biggest roll can be played
            {
                int max_move = moves.get(1);//find the biggest move
                if (moves.get(0) > max_move)
                {
                    max_move = moves.get(0);
                }

                //find if the biggest move is at all present
                boolean maxMoveNotPresent = true;
                for (ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> move : result)
                {
                    if ((move.get(0).getKey() - move.get(0).getValue()) == max_move)
                    {
                        maxMoveNotPresent = false;
                        break;
                    }
                }
                //only use combinations, which use the biggest move
                for (ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> move : result)
                {
                    if (move.get(0).getValue() == 0 || (move.get(0).getKey() - move.get(0).getValue()) == max_move || maxMoveNotPresent)
                    {
                        output.add(move);
                    }
                }
            }
            else
            {
                output=result;
            }
        }


       
        return output;
    }

    
    /** 
     * @param playerColor -  color of the active player
     * @param moves - ArrayList<Integer> of moves resulting from roll of the die
     * @return ArrayList<ArrayList<SimpleEntry<Integer, Integer>>> - array list of all possisble permutations of moves
     */
    public ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>>  getAllPossibleMoves(Checker.Color playerColor, ArrayList<Integer> moves)
    {
        ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> result = new ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>>();
        for (int ind=0; ind<moves.size(); ind++)//loop through moves
        {
            //find all legal moves with the current roll
            ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> result_single = new ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>();
            result_single.addAll(getPossibleMovesFromOneRoll(playerColor, moves.get(ind)));

            //make a copy of all moves possible exculding the current move (as if it is used)
            ArrayList<Integer> moves_copy = new ArrayList<Integer>();
            for (int jnd=0; jnd<moves.size(); jnd++)
            {
                if (ind != jnd)
                {
                    moves_copy.add(moves.get(jnd));
                }
            }

            //iterate through the possible list of moves updating the board and finding all the possible moves till the end of the branch
            for (int knd=0; knd < result_single.size(); knd++)
            {
                Board cloneBoard = new Board(this);
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
                else//if in the enf of the branch, just append the current result
                {
                    ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> fillerArray = new ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>();
                    fillerArray.add(result_single.get(knd));
                    result.add(fillerArray);
                }
                
            }
        }
        

        return getUniqueLongArrays(result);//return only unique arrays, to reduce complexity
    }


    
    /** 
     * @param index - index of the array (1-24)
     * @param playerColor - color of the current player
     * @return int - pip representation of the index
     */
    public static int mapToPip(int index, Checker.Color playerColor)
    {
        if (playerColor == Checker.Color.RED)
        {
            index = Point.MAX_POINTS + 1 - index;
        }
        return index;
    }

    
    /** 
     * @param pip - pip of the point (1-24)
     * @param playerColor - color of the current player
     * @return int - index of the array (1-24)
     */
    public static int mapFromPip(int pip, Checker.Color playerColor)
    {
        if (playerColor == Checker.Color.RED)
        {
            pip = Point.MAX_POINTS + 1 - pip;
        }
        return pip;
    }

    
    /** 
     * @param source - int index of the source (1-24) to move from
     * @param destination - int index of the destiation (1-24) to move to
     * @return boolean - flag indicating the success of the move
     */
    public boolean moveColumns(int source, int destination)
    {
        destination--;
        source--;
        boolean result = false;
        if (points[destination].getColor() == Checker.Color.INVALID || points[destination].getColor() == points[source].getColor())//valid move and not a blob
        {
            result = points[source].moveTo(points[destination]);//move checker from source to destination
        }
        else if (points[destination].getSize()==1)//blob case
        {
            result = points[destination].moveTo(bar);//move a checker of the opposite color to bar
            if (result)
            {
                result = points[source].moveTo(points[destination]);//move checker from source to destination
            }
            
        }
            
        return result;
    }

    
    /** 
     * @param destination - int index of the destination (1-24)
     * @param color - color of the current player
     * @return boolean - flag indicating the success of the move
     */
    public boolean moveFromBar(int destination, Checker.Color color)
    {
        destination--;
        boolean result = false;

        if (points[destination].getColor() == Checker.Color.INVALID || points[destination].getColor() == color)
        {
            result = bar.moveTo(points[destination], color);//move from bar to empty/same color point
        }
        else if (points[destination].getSize()==1)//blob case, removing the checker of oposite color to the bar
        {
            result = points[destination].moveTo(bar);
            if (result)
            {
                result = bar.moveTo(points[destination], color);
            }
        }
        return result;
    }
    
    
    /** 
     * @param playerColor - color of the player, which autowins
     */
    public void autoWin(Checker.Color playerColor)
    {
        //to autowin, just remove all the checkers from the board
        for (int pip=1; pip < Point.MAX_POINTS+1; pip++)
        {
            if (points[mapFromPip(pip, playerColor)-1].getColor()==playerColor)
            {
                points[mapFromPip(pip, playerColor)-1].removeAllCheckersColor(playerColor);
            }
        }
        bar.removeAllCheckersColor(playerColor);
    }

    
    /** 
     * @param color - color of the current player
     * @return int - 1 - single ending, 2 - gammon, 3 - backgammon
     */
    public int getEndGameMultiplierColor(Checker.Color color)
    {
        int multiplier = 2;//gammon

        int trippleCount = bar.colorCount(color);//checkers, satisfying the backgammon condition
        int checkerCount = trippleCount;//total count of checkers on the board

        for (int pip=1; pip < Point.MAX_POINTS+1; pip++)
        {
            if (points[mapFromPip(pip, color)-1].getColor()==color)
            {
                checkerCount+=points[mapFromPip(pip, color)-1].getSize();
                if (pip > Point.MAX_POINTS-BER_OFF_THOLD)
                {
                    trippleCount += points[mapFromPip(pip, color)-1].getSize();
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

    
    /** 
     * @return int - endgame multiplier 0- not over, 1-single, 2-gammon, 3-backgammon
     */
    public int getEndgameMultiplier()
    {
        int blackPip = getPipScore(Checker.Color.BLACK);
        int redPip = getPipScore(Checker.Color.RED);
        int multiplier = 0;
        if (redPip == 0)//red wins
        {
            multiplier = getEndGameMultiplierColor(Checker.Color.BLACK);//find multiplier for black
        }
        else if (blackPip == 0)//black wins
        {
            multiplier = getEndGameMultiplierColor(Checker.Color.RED);//find multiplier for red
        }

        return multiplier;
    }
    


    
}
