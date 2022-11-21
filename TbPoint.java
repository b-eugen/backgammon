 import static org.junit.jupiter.api.Assertions.*;

 import org.junit.*;
 import org.junit.jupiter.api.*;
 import org.junit.jupiter.api.Test;
 import java.util.*;

 public class TbPoint {
     Point point;
     private static final int MAX_CHECKERS = 15;
     @BeforeEach
     void setUp()
     {
         point = new Point();
     }

     @Test
     void testPoint()
     {
         assertNotNull(point);
         point.append(new Checker(Checker.Color.INVALID));
         assertEquals(point.getSize(), 0);
     }

     @Test
     void testDuplication()
     {
         Point second = new Point(this.point);
         assertEquals(this.point.getSize(), second.getSize());
         assertEquals(this.point.getNumber(), second.getNumber());
         Checker black_checker = new Checker(Checker.Color.BLACK);
         second.append(black_checker);
         assertNotEquals(this.point.getSize(), second.getSize());
         assertEquals(this.point.getNumber(), second.getNumber());
     }
     @Test
     void testPrepForGame()
     {
         for(int i=1;  i<=24; i++)
         {
             point = new Point(i);
             point.prepareForGame();
             assertNotNull(point);
             switch (i)
             {
                 case 1: assertEquals(point.getColor(), Checker.Color.RED); assertEquals(point.getSize(), 2); break;
                 case 6: assertEquals(point.getColor(), Checker.Color.BLACK); assertEquals(point.getSize(), 5); break;
                 case 8: assertEquals(point.getColor(), Checker.Color.BLACK); assertEquals(point.getSize(), 3); break;
                 case 12: assertEquals(point.getColor(), Checker.Color.RED); assertEquals(point.getSize(), 5); break;
                 case 13: assertEquals(point.getColor(), Checker.Color.BLACK); assertEquals(point.getSize(), 5); break;
                 case 17: assertEquals(point.getColor(), Checker.Color.RED); assertEquals(point.getSize(), 3); break;
                 case 19: assertEquals(point.getColor(), Checker.Color.RED); assertEquals(point.getSize(), 5); break;
                 case 24: assertEquals(point.getColor(), Checker.Color.BLACK); assertEquals(point.getSize(), 2); break;
                 default: break;
             }
         }
     }


     @Test
     void testDesignColor()
     {
         for(int i=1;  i<=24; i++)
         {
             point = new Point(i);
            
             assertNotNull(point);
             if (i%2==0)
             {
                 assertEquals(point.getDesignColor(), "\033[0;32m");
             }
             else
             {
                 assertEquals(point.getDesignColor(), "\033[0;34m");
             }
         }
     }

     @Test
     void testGetNumberGetColor()
     {
         for (int num =0; num<MAX_CHECKERS; num++)//max num of chekers in backgammon
         {
             for (int colorRand = 0; colorRand <= 1; colorRand++)
             {
                 point = new Point();
                 Checker.Color color;
                 if (num == 0)
                 {
                     color = Checker.Color.INVALID;
                 }
                 else
                 {
                     color = Checker.Color.values()[colorRand];
                 }
    
                 Checker checker = new Checker(color);
    
                 for (int i=0; i<num; i++)
                 {
                     point.append(checker);
                     // System.out.println(point.getSize() + " "+ i+" "+num);
                 }
    
                 assertEquals(point.getSize(), num);
                 assertEquals(point.getColor(), color);
             }
            
         }
        
     }

     @Test
     void testGetPop()
     {
         Random rand = new Random();
         for (int i=0; i<100; i++)
         {
             Point point = new Point();
             int number = rand.nextInt(MAX_CHECKERS);
             for (int j=0; j < number; j++)
             {
                 point.append(new Checker());
             }
             if(number == 0)
             {
                 assertEquals(point.getTop().getColor(), Checker.Color.INVALID);
                 assertEquals(point.get(1).getColor(), Checker.Color.INVALID);
                 assertEquals(point.pop(1).getColor(), Checker.Color.INVALID);
                 assertEquals(point.pop().getColor(), Checker.Color.INVALID);
             }
             else
             {
                 assertEquals(point.getTop().getColor(), Checker.Color.BLACK);
                 int getInd =0;
                 if (number > 1)
                 {
                     getInd = rand.nextInt(number-1);
                 }
                    
                 assertEquals(point.get(getInd).getColor(), Checker.Color.BLACK);
                 assertEquals(point.get(number).getColor(), Checker.Color.INVALID);
                 assertEquals(point.pop().getColor(), Checker.Color.BLACK);
                 point.append(new Checker());
                 assertEquals(point.pop(getInd).getColor(), Checker.Color.BLACK);
                 assertEquals(point.pop(number).getColor(), Checker.Color.INVALID);

             }
            
         }
     }

     @Test
     void testMoveTo()
     {
         Point point_red = new Point();
         Point point_empty = new Point();
         Point point_black = new Point();
         point_black.append(new Checker(Checker.Color.BLACK));
         point_red.append(new Checker(Checker.Color.RED));
         point.append(new Checker(Checker.Color.BLACK));
         point.moveTo(point_black);
         assertEquals(point.getSize(), 0);
         assertEquals(point_black.getSize(), 2);
         point_black.moveTo(point);
         point_black.moveTo(point);
         assertEquals(point_black.getSize(), 0);
         assertEquals(point.getSize(), 2);
         point.moveTo(point_empty);
         assertEquals(point.getSize(), 1);
         assertEquals(point_empty.getSize(), 1);
         point_empty.moveTo(point_red);
         assertEquals(point_empty.getSize(), 1);
         assertEquals(point_red.getSize(), 1);
     }
 }
