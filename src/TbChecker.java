 import static org.junit.jupiter.api.Assertions.*;

 import org.junit.jupiter.api.*;
 import org.junit.jupiter.api.Test;

 public class TbChecker {
     private Checker checker;

     @BeforeEach
     void setUp()
     {
         checker = new Checker(Checker.Color.BLACK);
     }
     @Test
     void testChecker()
     {
         assertNotNull(checker);
     }

     @Test
     void testColor()
     {
         checker = new Checker(Checker.Color.BLACK);
         assertNotNull(checker);
         assertEquals(Checker.Color.BLACK, checker.getColor());

         checker = new Checker(Checker.Color.RED);
         assertNotNull(checker);
         assertEquals(Checker.Color.RED, checker.getColor());

         checker = new Checker(Checker.Color.INVALID);
         assertNotNull(checker);
         assertEquals(Checker.Color.INVALID, checker.getColor());

     }

     @Test
     void testIsValid()
     {
         checker = new Checker(Checker.Color.BLACK);
         assertNotNull(checker);
         assertEquals(true, checker.isValid());

         checker = new Checker(Checker.Color.RED);
         assertNotNull(checker);
         assertEquals(true, checker.isValid());

         checker = new Checker(Checker.Color.INVALID);
         assertNotNull(checker);
         assertEquals(false, checker.isValid());
     }

     @Test
     void testToString()
     {
         checker = new Checker(Checker.Color.BLACK);
         assertNotNull(checker);
         assertEquals("\033[0m⬤\033[0m", checker.toString());

         checker = new Checker(Checker.Color.RED);
         assertNotNull(checker);
         assertEquals("\033[0;31m⬤\033[0m", checker.toString());

         checker = new Checker(Checker.Color.INVALID);
         assertNotNull(checker);
         assertEquals("\033[0;36m⬤\033[0m", checker.toString());
     }
 }
