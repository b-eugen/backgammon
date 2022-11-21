import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class TbPointView {

    PointView pointView;

    @BeforeEach
    void setUp()
    {
        pointView = new PointView();
    }

    @Test
    void test_output()
    {
        Point p = new Point(24);
        String columnColor = p.getDesignColor();
        String[] array = {String.format("%s\\%sPoint %2d%s/%s", columnColor, Checker.Color.BLACK, p.getNumber(), columnColor, Checker.Color.BLACK),
                String.format(" %s\\%s %2d%s  %s/%s ", columnColor, Checker.Color.BLACK,  p.getSize(), p.getTop(), columnColor, Checker.Color.BLACK),
                String.format("  %s\\%s    %s/%s  ",  columnColor, Checker.Color.BLACK, columnColor, Checker.Color.BLACK),
                String.format("   %s\\%s  %s/%s   ",  columnColor, Checker.Color.BLACK,  columnColor, Checker.Color.BLACK),
                String.format("    %s\\/%s    ",  columnColor, Checker.Color.BLACK)};

        String[] test = pointView.toArrayOfStrings(p, Checker.Color.BLACK);
        assertEquals(String.join("\n", test), String.join("\n", array));
        p = new Point(1);
        test = pointView.toArrayOfStrings(p, Checker.Color.BLACK);
        array = new String[]{"    \u001B[0;34m/\\\u001B[0m    ", "   \u001B[0;34m/\u001B[0m  \u001B[0;34m\\\u001B[0m   ", "  \u001B[0;34m/\u001B[0m    \u001B[0;34m\\\u001B[0m  ", " \u001B[0;34m/\u001B[0m  0\u001B[0;36m⬤\u001B[0m  \u001B[0;34m\\\u001B[0m ", "\u001B[0;34m/\u001B[0mPoint  1\u001B[0;34m\\\u001B[0m"};
        assertEquals(String.join("\n", test), String.join("\n", array));

    }

}
