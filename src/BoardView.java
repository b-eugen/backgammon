/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * This program is the BoardView class, which displays the Board
 * @version 1 2022-21-11
 * @author Yevhenii Mormul
 */
/**
 * A {@code BoardView} object displayes the board
 */
public class BoardView {

    BoardView()
    {
    }
    

    /*
     * Method which concantenates 2 arrays of strings
     * @param base - first string array
     * @param appendable - second string array
     * @return - result of array concatentations
     */
    private static String[] concatStringArrays(String[] base, String[] appendable)
    {
        String[] result = new String[base.length+appendable.length];
        System.arraycopy(base, 0, result, 0, base.length);
        System.arraycopy(appendable, 0, result, base.length, appendable.length);
        return result;
    }

    
    /** 
     * @param board - board to be displayed
     * @param playerColor - color of the active player
     * @return String - string representation of the board
     */
    public static String display(Board board, Checker.Color playerColor)
    {
        String[] barString = BarView.toArrayOfStrings(board.getBar());//use bar view

        String[][] array2D = new String[Point.MAX_POINTS/2+2][barString.length];//2d representation, to be converted to output String

        //convert points and bar to board
        String[] stub = {"          "};
        for (int col=0; col<Point.MAX_POINTS/2+1; col++)
        {
            if (col < Point.MAX_POINTS/4)
            {
                array2D[col] = concatStringArrays(concatStringArrays(PointView.toArrayOfStrings(board.getPoints()[Point.MAX_POINTS/2+col], playerColor), stub), PointView.toArrayOfStrings(board.getPoints()[Point.MAX_POINTS/2-col-1], playerColor));
            }
            else if (col == Point.MAX_POINTS/4)
            {
                array2D[col] = barString;
            }
            else{
                array2D[col] = concatStringArrays(concatStringArrays(PointView.toArrayOfStrings(board.getPoints()[Point.MAX_POINTS/2+col-1], playerColor), stub), PointView.toArrayOfStrings(board.getPoints()[Point.MAX_POINTS/2-col], playerColor));
            }
            
        }

        // add the start cube to the 2d array
        //determine the owner
        int start_cube;
        switch (board.getCube().getOwner())
        {
            case BLACK: start_cube = barString.length - DoublingCubeView.height; break;
            case INVALID: start_cube = (barString.length - DoublingCubeView.height)/2; break;
            default: start_cube = 0;
        }

        //place cube accordingly
        String[] cubeString = DoublingCubeView.toArrayOfStrings(board.getCube());
    
        for (int row = 0; row<barString.length; row++)
        {
            if (row >= start_cube && row < start_cube + DoublingCubeView.height)
            {
                array2D[Point.MAX_POINTS/2+1][row] = cubeString[row-start_cube];
            }
            else
            {
                array2D[Point.MAX_POINTS/2+1][row] = stub[0];
            }
            
        }
        

        //convert 2d array of strings into the resulting string
        String result = "";
        for (int row=0; row<barString.length; row++)
        {
            for (int col=0; col<Point.MAX_POINTS/2+2; col++)
            {
                result = result+array2D[col][row];
            }
            result = result+"\n";
        }
        return result;
        
    }
}
