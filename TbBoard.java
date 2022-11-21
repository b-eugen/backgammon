 import static org.junit.jupiter.api.Assertions.*;

 import org.junit.*;
 import org.junit.jupiter.api.*;
 import org.junit.jupiter.api.Test;
 import java.util.*;
 import java.util.ArrayList;
 import java.util.AbstractMap;


 public class TbBoard {
     Board board;
     @BeforeEach
     void setUp()
     {
         board = new Board();
     }
     @Test
     void testBoard()
     {
         assertNotNull(board);

         assertEquals(board.getBar().getSize(), 0);
         assertEquals(board.getPoints().length, 24);
         int i;
         int size;
         Checker.Color color;

         for (i =0; i<24; i++)
         {
             size = board.getPoints()[i].getSize();
             color = board.getPoints()[i].getColor();
             switch (i+1)
             {
                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
                 case 2: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 12: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 13: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
                 case 14: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


             }
         }
         board.moveColumns(13, 14);

         for (i =0; i<24; i++)
         {
             size = board.getPoints()[i].getSize();
             color = board.getPoints()[i].getColor();
             switch (i+1)
             {
                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
                 case 2: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 12: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 13: assertEquals(size, 4); assertEquals(color, Checker.Color.BLACK); break;
                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.BLACK); break;
                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


             }
            
         }
         board.moveColumns(12, 14);

         for (i =0; i<24; i++)
         {
             size = board.getPoints()[i].getSize();
             color = board.getPoints()[i].getColor();
             switch (i+1)
             {
                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
                 case 2: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 12: assertEquals(size, 4); assertEquals(color, Checker.Color.RED); break;
                 case 13: assertEquals(size, 4); assertEquals(color, Checker.Color.BLACK); break;
                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.RED); break;
                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


             }
            
         }
         assertEquals(board.getBar().getColorIndex(Checker.Color.BLACK), 0);

         AbstractMap.SimpleEntry<Integer,Integer>  move= new AbstractMap.SimpleEntry<Integer,Integer>(25, 1);

         board.makeMove( move, Checker.Color.BLACK);
//         board.moveFromBar(1, Checker.Color.BLACK);

         for (i =0; i<24; i++)
         {
             size = board.getPoints()[i].getSize();
             color = board.getPoints()[i].getColor();
             switch (i+1)
             {
                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
                 case 2: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 12: assertEquals(size, 4); assertEquals(color, Checker.Color.RED); break;
                 case 13: assertEquals(size, 4); assertEquals(color, Checker.Color.BLACK); break;
                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.RED); break;
                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


             }
            
         }
         assertEquals(board.getBar().getColorIndex(Checker.Color.BLACK), 0);
        
        

         board.moveFromBar(2, Checker.Color.BLACK);

         for (i =0; i<24; i++)
         {
             size = board.getPoints()[i].getSize();
             color = board.getPoints()[i].getColor();
             switch (i+1)
             {
                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
                 case 2: assertEquals(size, 1); assertEquals(color, Checker.Color.BLACK); break;
                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 12: assertEquals(size, 4); assertEquals(color, Checker.Color.RED); break;
                 case 13: assertEquals(size, 4); assertEquals(color, Checker.Color.BLACK); break;
                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.RED); break;
                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


             }
            
         }
         assertEquals(board.getBar().getColorIndex(Checker.Color.BLACK), -1);

//         board.moveColumns(13, 14);
         move= new AbstractMap.SimpleEntry<Integer,Integer>(13, 14);
         board.makeMove(move, Checker.Color.BLACK);
         for (i =0; i<24; i++)
         {
             size = board.getPoints()[i].getSize();
             color = board.getPoints()[i].getColor();
             switch (i+1)
             {
                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
                 case 2: assertEquals(size, 1); assertEquals(color, Checker.Color.BLACK); break;
                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 12: assertEquals(size, 4); assertEquals(color, Checker.Color.RED); break;
                 case 13: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.BLACK); break;
                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


             }
            
         }
         assertEquals(board.getBar().getColorIndex(Checker.Color.RED), 0);


         board.moveFromBar(2, Checker.Color.RED);

         for (i =0; i<24; i++)
         {
             size = board.getPoints()[i].getSize();
             color = board.getPoints()[i].getColor();
             switch (i+1)
             {
                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
                 case 2: assertEquals(size, 1); assertEquals(color, Checker.Color.RED); break;
                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 12: assertEquals(size, 4); assertEquals(color, Checker.Color.RED); break;
                 case 13: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.BLACK); break;
                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


             }
            
         }
         assertEquals(board.getBar().getColorIndex(Checker.Color.BLACK), 0);

          move= new AbstractMap.SimpleEntry<Integer,Integer>(6, 0);

          board.makeMove( move, Checker.Color.BLACK);

         for (i =0; i<24; i++)
         {
             size = board.getPoints()[i].getSize();
             color = board.getPoints()[i].getColor();
             switch (i+1)
             {
                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
                 case 2: assertEquals(size, 1); assertEquals(color, Checker.Color.RED); break;
                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 6: assertEquals(size, 4); assertEquals(color, Checker.Color.BLACK); break;
                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 12: assertEquals(size, 4); assertEquals(color, Checker.Color.RED); break;
                 case 13: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.BLACK); break;
                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;
             }

         }
     }

     @Test
     void testGetPipScore()
     {
         assertEquals(this.board.getPipScore(Checker.Color.BLACK), 167);
         for (int ind=0; ind<24; ind++)
             this.board.getPoints()[ind] = new Point(ind);
         assertEquals(this.board.getPipScore(Checker.Color.BLACK), 0);

     }

     @Test
     void testDuplicate()
     {
         Board test = new Board(board);
         assertEquals(test.getPoints()[0].getNumber(), board.getPoints()[0].getNumber());
         test.getPoints()[0].pop();
         assertEquals(test.getPoints()[0].getSize(), 1);
         assertEquals(board.getPoints()[0].getSize(), 2);


     }

     @Test
     void testMapToPip()
     {
         assertEquals(Board.mapFromPip(1, Checker.Color.BLACK), 1);
         assertEquals(Board.mapToPip(1, Checker.Color.BLACK), 1);
         assertEquals(Board.mapFromPip(1, Checker.Color.RED), 24);
         assertEquals(Board.mapToPip(1, Checker.Color.RED), 24);
     }

     @Test
     void testAutowin()
     {
         board.autoWin(Checker.Color.BLACK);
         int size;
         Checker.Color color;
         for (int i =0; i<24; i++)
         {
             size = board.getPoints()[i].getSize();
             color = board.getPoints()[i].getColor();
             switch (i+1)
             {
                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
                 case 2: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 6: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 8: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 12: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 13: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 14: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
                 default: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;

             }

         }

         assertEquals(board.getEndgameMultiplier(), 3);
         board.getPoints()[0] = new Point();
         assertEquals(board.getEndgameMultiplier(), 1);
         board.getPoints()[18].append(new Checker(Checker.Color.RED));

     }

     @Test
     void testMoveGenerator()
     {
         ArrayList<Integer> moves= new ArrayList<Integer>();
         moves.add(5);
         moves.add(6);

         ArrayList<ArrayList<AbstractMap.SimpleEntry<Integer,Integer>>> result = board.getAllPossibleMovesWrapper(Checker.Color.BLACK, moves);
         assertEquals(result.size(), 8);
     }

     @Test
     void testGetDoublingCube()
     {
         DoublingCube cube = board.getCube();
         assertEquals(cube.current_stake, 1);
     }
 }
