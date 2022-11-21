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

    public static String display(Board board, Checker.Color playerColor)
    {
        String[] barString = BarView.toArrayOfStrings(board.getBar());

        String[][] array2D = new String[Point.MAX_POINTS/2+2][barString.length];

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
        int start_cube;
        switch (board.getCube().getOwner())
        {
            case BLACK: start_cube = barString.length - DoublingCubeView.height; break;
            case INVALID: start_cube = (barString.length - DoublingCubeView.height)/2; break;
            default: start_cube = 0;
        }

        String[] cubeString = DoublingCubeView.toArrayOfqStrings(board.getCube());
    
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
