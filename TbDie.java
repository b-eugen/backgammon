import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class TbDie {
    Die die;
    @BeforeEach
    void setUp()
    {
        die = new Die();
    }

    @Test
    void testRoll()
    {
        int result1 = die.getRollValue();

        assertTrue(result1<=6 && result1>=1);
        for (int i = -10; i <= 10; i++)
        {
            int result = die.setLastRoll(i).getLastRoll();
            if (i<1)
            {
                assertEquals(result, result1);
            }
            else if (i>=1 && i<7)
            {
                assertEquals(result, i);
            }
            else
            {
                assertEquals(result, 6);
            }
        }
    }

}
