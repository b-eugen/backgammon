/* Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen*/
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class TbDieView {
    DieView dieView;
    @BeforeEach
    void setUp()
    {
        dieView = new DieView();
    }

    @Test
    void testDieView()
    {
        Die die = new Die();
        assertEquals(DieView.display(die), "⚀");
        die.setLastRoll(2);
        assertEquals(DieView.display(die), "\u2681");
        die.setLastRoll(3);
        assertEquals(DieView.display(die), "⚂");
        die.setLastRoll(4);
        assertEquals(DieView.display(die), "⚃");
        die.setLastRoll(5);
        assertEquals(DieView.display(die), "⚄");
        die.setLastRoll(6);
        assertEquals(DieView.display(die), "⚅");

    }

}
