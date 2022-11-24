//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.Test;
//
//public class TbDie {
//    Die die;
//    @BeforeEach
//    void setUp()
//    {
//        die = new Die();
//    }
//
//    @Test
//    void testRoll()
//    {
//        int result = die.getRollValue();
//
//        assertTrue(result<=6 && result>=1);
//        for (int i = 1; i <= 6; i++)
//        {
//            result = die.setLastRoll(i).getLastRoll();
//            assertEquals(result, i);
//        }
//    }
//
//}
