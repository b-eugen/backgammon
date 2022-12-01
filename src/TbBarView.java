/* Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TbBarView {
    BarView barView;

    @BeforeEach
    void setUp()
    {
        barView = new BarView();
    }

    @Test
    void testDisplayBar()
    {
        Bar bar= new Bar();
        String[] reference  = new String[] {"|‾‾‾‾‾|",
                "|     |",
                "|     |",
                String.format("|  %s  |", new Checker(Checker.Color.RED)),
                String.format("|%3d  |", bar.colorCount(Checker.Color.RED)),
                "| Bar |",
                String.format("|  %s  |", new Checker(Checker.Color.BLACK)),
                String.format("|%3d  |", bar.colorCount(Checker.Color.BLACK)),
                "|     |",
                "|     |",
                "|_____|"};
        String[] test = BarView.toArrayOfStrings(bar);
        assertEquals(String.join("\n", reference), String.join("\n", test));
    }

}
