 import static org.junit.jupiter.api.Assertions.*;

 import org.junit.jupiter.api.*;

 public class TbBar {
     Bar bar;
     @BeforeEach
     void setUp()
     {
         bar = new Bar();
     }

     @Test
     void colorCount()
     {
         for (int i = 0; i < 15; i++)
         {
             this.bar.append(new Checker(Checker.Color.RED));
             assertEquals(this.bar.colorCount(Checker.Color.RED), i+1);
             assertEquals(this.bar.colorCount(Checker.Color.BLACK), 0);
         }

         for (int i = 0; i < 15; i++)
         {
             this.bar.append(new Checker(Checker.Color.BLACK));
             assertEquals(this.bar.colorCount(Checker.Color.BLACK), i+1);
             assertEquals(this.bar.colorCount(Checker.Color.RED), 15);
         }
     }

     @Test
     void testDuplicate()
     {
         Bar copy = new Bar(this.bar);
         assertEquals(copy.getSize(), this.bar.getSize());
         copy.append(new Checker(Checker.Color.RED));
         assertEquals(copy.getSize(), 1);
         assertEquals(this.bar.getSize(), 0);
     }

     @Test
     void testBar()
     {
         assertNotNull(bar);
         assertEquals(bar.getColor(), Checker.Color.INVALID);
     }

     @Test
     void testRemoveColor()
     {
         bar.append(new Checker(Checker.Color.RED));
         assertEquals(bar.getSize(), 1);
         assertEquals(bar.getColorIndex(Checker.Color.RED), 0);
         assertEquals(bar.getColorIndex(Checker.Color.BLACK), -1);
         assertEquals(bar.getColorIndex(Checker.Color.INVALID), -1);
         bar.append(new Checker(Checker.Color.BLACK));
         assertEquals(bar.getColorIndex(Checker.Color.BLACK), 1);
         bar.removeColor(Checker.Color.RED);
         assertEquals(bar.getColorIndex(Checker.Color.RED), -1);
         assertEquals(bar.getColorIndex(Checker.Color.BLACK), 0);
         bar.removeColor(Checker.Color.RED);
         assertEquals(bar.getColorIndex(Checker.Color.RED), -1);
         assertEquals(bar.getColorIndex(Checker.Color.BLACK), 0);
         bar.removeColor(Checker.Color.BLACK);
         assertEquals(bar.getColorIndex(Checker.Color.RED), -1);
         assertEquals(bar.getColorIndex(Checker.Color.BLACK), -1);
     }

     @Test
     void testMoveTo()
     {
         bar.append(new Checker(Checker.Color.RED));
         Point destination = new Point();
         bar.moveTo(destination, Checker.Color.BLACK);
         assertEquals(bar.getSize(), 1);
         bar.moveTo(destination, Checker.Color.RED);
         assertEquals(bar.getColorIndex(Checker.Color.RED), -1);
         assertEquals(destination.getColor(), Checker.Color.RED);
         assertEquals(destination.getSize(), 1);
         assertEquals(bar.getSize(), 0);

         bar.append(new Checker(Checker.Color.BLACK));
         assertEquals(bar.getColorIndex(Checker.Color.RED), -1);
         assertEquals(bar.getColorIndex(Checker.Color.BLACK), 0);
         assertEquals(destination.getColor(), Checker.Color.RED);
         assertEquals(destination.getSize(), 1);
         assertEquals(bar.getSize(), 1);

     }
 }
