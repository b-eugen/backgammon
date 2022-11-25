 import static org.junit.jupiter.api.Assertions.*;

 import org.junit.jupiter.api.*;
 import org.junit.jupiter.api.Test;
 import java.util.*;

 public class TbPlayer {
     Player player;
     @BeforeEach
     void setUp()
     {
         player = new Player();
     }

     @Test
     void testPlayer()
     {
         assertNotNull(player);
         Checker checker = new Checker(Checker.Color.BLACK);
         assertEquals(player.toString(), "Player: Player[0m⬤[0m ");
         assertEquals(player.getColor(), Checker.Color.BLACK);
         assertEquals(player.getName(), "Player");
         player = new Player("foo", Checker.Color.RED);
         assertNotNull(player);
         assertEquals(player.getName(), "foo");
         assertEquals(player.getColor(), Checker.Color.RED);
         player = new Player("boo", Checker.Color.BLACK);
         assertNotNull(player);
         assertEquals(player.getName(), "boo");
         assertEquals(player.getColor(), Checker.Color.BLACK);
     }

     @Test
     void testDisplayPlayerColor()
     {
         player = new Player("foo", Checker.Color.RED);
         assertEquals(player.getDisplayCheckerColor().getColor(), Checker.Color.RED);

         player = new Player("foo", Checker.Color.BLACK);
         assertEquals(player.getDisplayCheckerColor().getColor(), Checker.Color.BLACK);
     }
     @Test
     void testRoll()
     {
         Die die1 = new Die();
         Die die2 = new Die();
         ArrayList<Integer> moves = this.player.getMoves();
         assertEquals(moves.size(), 0);
         this.player.roll(die1, die2);
         moves = this.player.getMoves();
         assertTrue(2 == moves.size() || moves.size() == 4);
         assertTrue(0 <= moves.get(0) && moves.get(0) <= 6);
         assertTrue(0 <= moves.get(1) && moves.get(1) <= 6);
         if (moves.size()>2)
         {
             assertTrue(0 <= moves.get(2) && moves.get(2) <= 6);
             assertTrue(0 <= moves.get(3) && moves.get(3) <= 6);
         }

         for (int i = 1; i<=6; i++)
         {
            for (int j = 1; j<=6; j++)
            {
                die1.setLastRoll(i);
                die2.setLastRoll(j);
                this.player.forceRoll(die1, die2);
                moves = this.player.getMoves();
                assertTrue(2 == moves.size() || moves.size() == 4);

                assertTrue(0 <= moves.get(0) && moves.get(0) <= 6);
                assertTrue(0 <= moves.get(1) && moves.get(1) <= 6);
                if (moves.size()>2)
                {
                    assertTrue(0 <= moves.get(2) && moves.get(2) <= 6);
                    assertTrue(0 <= moves.get(3) && moves.get(3) <= 6);
                }
            }
         }

     }


     @Test
     void testScore()
     {
         assertEquals(this.player.getScore(), 0);
         this.player.incrementScore(4);
         assertEquals(this.player.getScore(), 4);
         this.player.incrementScore(3);
         assertEquals(this.player.getScore(), 7);
         this.player.incrementScore(-4);
         assertEquals(this.player.getScore(), 7);
         this.player.incrementScore(0);
         assertEquals(this.player.getScore(), 7);
     }
 }