public class PointView {

    private static String[] toArrayOfStringsTemplate(Point point, Checker.Color color)
    {
        int pip_point = point.getNumber();
        if (color == Checker.Color.BLACK)
        {
            pip_point = Point.MAX_POINTS + 1 - pip_point;
        }
        String columnColor = point.getDesignColor();
        String[] array = {String.format("%s\\%sPoint %2d%s/%s", columnColor, Checker.Color.BLACK, pip_point, columnColor, Checker.Color.BLACK),
                          String.format(" %s\\%s %2d%s  %s/%s ", columnColor, Checker.Color.BLACK,  point.getSize(), point.getTop(), columnColor, Checker.Color.BLACK),
                          String.format("  %s\\%s    %s/%s  ",  columnColor, Checker.Color.BLACK, columnColor, Checker.Color.BLACK),
                          String.format("   %s\\%s  %s/%s   ",  columnColor, Checker.Color.BLACK,  columnColor, Checker.Color.BLACK),
                          String.format("    %s\\/%s    ",  columnColor, Checker.Color.BLACK)};
        return array;

    }

    private static String[] toArrayOfStringsTemplate(Point point)
    {
        
        String columnColor = point.getDesignColor();
        
        String[] array = {String.format("%s\\%sPoint %2d%s/%s", columnColor, Checker.Color.BLACK, point.getNumber(), columnColor, Checker.Color.BLACK),
                          String.format(" %s\\%s %2d%s  %s/%s ", columnColor, Checker.Color.BLACK,  point.getSize(), point.getTop(), columnColor, Checker.Color.BLACK),
                          String.format("  %s\\%s    %s/%s  ",  columnColor, Checker.Color.BLACK, columnColor, Checker.Color.BLACK),
                          String.format("   %s\\%s  %s/%s   ",  columnColor, Checker.Color.BLACK,  columnColor, Checker.Color.BLACK),
                          String.format("    %s\\/%s    ",  columnColor, Checker.Color.BLACK)};
        return array;
    }

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

    public static String[] toArrayOfStrings(Point point, Checker.Color color)
    {
        if (point.getNumber()>point.MAX_POINTS/2)
        {
            return PointView.toArrayOfStringsTemplate(point, color);
        }
        return PointView.toArrayOfStringsReversed(point, color);
    }
}
