/**
 * This program is the PointView class, which displays the point
 * @version 1 2022-21-11
 * @author Yevhenii Mormul
 */

/**
 * A {@code PointView} object displayes the point
 */
public class PointView {
    private static final String BLUE="\033[0;34m";
    private static final String GREEN="\033[0;32m";
    /**
     * @param point - point to be displayed
     * @return String - string representation of the design color
     */
    public static String getDesignColor(Point point)
    {
        if (point.getNumber()%2 == 0)
        {
            return GREEN;
        }
        return BLUE;
    }

    /** 
     * @param point - point to be displayed
     * @param color - color of the active player
     * @return String[] - array of strings to representing a template
     */
    private static String[] toArrayOfStringsTemplate(Point point, Checker.Color color)
    {
        int pip_point = point.getNumber();
        if (color == Checker.Color.RED)
        {
            pip_point = Point.MAX_POINTS + 1 - pip_point;
        }
        String columnColor = PointView.getDesignColor(point);
        String[] array = {String.format("%s\\%sPoint %2d%s/%s", columnColor, Checker.Color.BLACK, pip_point, columnColor, Checker.Color.BLACK),
                          String.format(" %s\\%s %2d%s  %s/%s ", columnColor, Checker.Color.BLACK,  point.getSize(), point.getTop(), columnColor, Checker.Color.BLACK),
                          String.format("  %s\\%s    %s/%s  ",  columnColor, Checker.Color.BLACK, columnColor, Checker.Color.BLACK),
                          String.format("   %s\\%s  %s/%s   ",  columnColor, Checker.Color.BLACK,  columnColor, Checker.Color.BLACK),
                          String.format("    %s\\/%s    ",  columnColor, Checker.Color.BLACK)};
        return array;

    }

    
    /** 
     * @param line - line where chracters are swapped
     * @param c1 - first char to be swapped
     * @param c2 - second char to be swapped
     * @return String - string with c1 and c2 swapped
     */
    private static String swapCharString(String line, char c1, char c2)
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

    
    /** 
     * @param point - point to be displayed
     * @param color - color of the current player
     * @return String[] - representation of the point
     */
    private static String[] toArrayOfStringsReversed(Point point, Checker.Color color)
    {
        String[] array = PointView.toArrayOfStringsTemplate(point, color);
        String[] outputArray = new String[array.length];

        for(int ind=0; ind<array.length; ind++)
        {
            outputArray[array.length-ind-1] = PointView.swapCharString(array[ind], '\\', '/');
        }
        return outputArray;
    }

    
    /** 
     * @param point - point to be displayed
     * @param color - color of the active player
     * @return String[] - array of strings representation of the active player
     */
    public static String[] toArrayOfStrings(Point point, Checker.Color color)
    {
        if (point.getNumber()>Point.MAX_POINTS/2)
        {
            return PointView.toArrayOfStringsTemplate(point, color);
        }
        return PointView.toArrayOfStringsReversed(point, color);
    }
}
