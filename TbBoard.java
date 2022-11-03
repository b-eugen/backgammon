// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.*;
// import org.junit.jupiter.api.*;
// import org.junit.jupiter.api.Test;
// import java.util.*;
// import java.util.ArrayList;


// public class TbBoard {
//     Board board;
//     @BeforeEach
//     void setUp()
//     {
//         board = new Board();
//     }
//     @Test
//     void testBoard()
//     {
//         assertNotNull(board);
//         Player player =new Player();
//         board.addPlayer(player);
//         board.addPlayer(player);
//         board.addPlayer(player);
//         ArrayList<Player> array = new ArrayList<Player>();
//         array.add(player);
//         array.add(player);
//         assertEquals(board.getPlayers(), array);

//         assertEquals(board.getBar().getSize(), 0);
//         assertEquals(board.getPoints().length, 24);
//         int i;
//         int size;
//         Checker.Color color;

//         for (i =0; i<24; i++)
//         {
//             size = board.getPoints()[i].getSize();
//             color = board.getPoints()[i].getColor();
//             switch (i+1)
//             {
//                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
//                 case 2: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
//                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
//                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 12: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
//                 case 13: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
//                 case 14: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
//                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
//                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


//             }
//         }
//         board.moveColumns(13, 14);

//         for (i =0; i<24; i++)
//         {
//             size = board.getPoints()[i].getSize();
//             color = board.getPoints()[i].getColor();
//             switch (i+1)
//             {
//                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
//                 case 2: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
//                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
//                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 12: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
//                 case 13: assertEquals(size, 4); assertEquals(color, Checker.Color.BLACK); break;
//                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.BLACK); break;
//                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
//                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
//                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


//             }
            
//         }
//         board.moveColumns(12, 14);

//         for (i =0; i<24; i++)
//         {
//             size = board.getPoints()[i].getSize();
//             color = board.getPoints()[i].getColor();
//             switch (i+1)
//             {
//                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
//                 case 2: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
//                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
//                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 12: assertEquals(size, 4); assertEquals(color, Checker.Color.RED); break;
//                 case 13: assertEquals(size, 4); assertEquals(color, Checker.Color.BLACK); break;
//                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.RED); break;
//                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
//                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
//                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


//             }
            
//         }
//         assertEquals(board.getBar().getColorIndex(Checker.Color.BLACK), 0);

       
//         board.moveFromBar(1, Checker.Color.BLACK);

//         for (i =0; i<24; i++)
//         {
//             size = board.getPoints()[i].getSize();
//             color = board.getPoints()[i].getColor();
//             switch (i+1)
//             {
//                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
//                 case 2: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
//                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
//                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 12: assertEquals(size, 4); assertEquals(color, Checker.Color.RED); break;
//                 case 13: assertEquals(size, 4); assertEquals(color, Checker.Color.BLACK); break;
//                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.RED); break;
//                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
//                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
//                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


//             }
            
//         }
//         assertEquals(board.getBar().getColorIndex(Checker.Color.BLACK), 0);
        
        

//         board.moveFromBar(2, Checker.Color.BLACK);

//         for (i =0; i<24; i++)
//         {
//             size = board.getPoints()[i].getSize();
//             color = board.getPoints()[i].getColor();
//             switch (i+1)
//             {
//                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
//                 case 2: assertEquals(size, 1); assertEquals(color, Checker.Color.BLACK); break;
//                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
//                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
//                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 12: assertEquals(size, 4); assertEquals(color, Checker.Color.RED); break;
//                 case 13: assertEquals(size, 4); assertEquals(color, Checker.Color.BLACK); break;
//                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.RED); break;
//                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
//                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
//                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


//             }
            
//         }
//         assertEquals(board.getBar().getColorIndex(Checker.Color.BLACK), -1);

//         board.moveColumns(13, 14);

//         for (i =0; i<24; i++)
//         {
//             size = board.getPoints()[i].getSize();
//             color = board.getPoints()[i].getColor();
//             switch (i+1)
//             {
//                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
//                 case 2: assertEquals(size, 1); assertEquals(color, Checker.Color.BLACK); break;
//                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
//                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
//                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 12: assertEquals(size, 4); assertEquals(color, Checker.Color.RED); break;
//                 case 13: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
//                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.BLACK); break;
//                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
//                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
//                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


//             }
            
//         }
//         assertEquals(board.getBar().getColorIndex(Checker.Color.RED), 0);


//         board.moveFromBar(2, Checker.Color.RED);

//         for (i =0; i<24; i++)
//         {
//             size = board.getPoints()[i].getSize();
//             color = board.getPoints()[i].getColor();
//             switch (i+1)
//             {
//                 case 0: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 1:assertEquals(size, 2); assertEquals(color, Checker.Color.RED); break;
//                 case 2: assertEquals(size, 1); assertEquals(color, Checker.Color.RED); break;
//                 case 3: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 4: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 5: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 6: assertEquals(size, 5); assertEquals(color, Checker.Color.BLACK); break;
//                 case 7: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 8: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
//                 case 9: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 10: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 11: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 12: assertEquals(size, 4); assertEquals(color, Checker.Color.RED); break;
//                 case 13: assertEquals(size, 3); assertEquals(color, Checker.Color.BLACK); break;
//                 case 14: assertEquals(size, 1); assertEquals(color, Checker.Color.BLACK); break;
//                 case 15: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 16: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 17: assertEquals(size, 3); assertEquals(color, Checker.Color.RED); break;
//                 case 18: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 19: assertEquals(size, 5); assertEquals(color, Checker.Color.RED); break;
//                 case 20: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 21: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 22: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 case 23: assertEquals(size, 0); assertEquals(color, Checker.Color.INVALID); break;
//                 default: assertEquals(size, 2); assertEquals(color, Checker.Color.BLACK); break;


//             }
            
//         }
//         assertEquals(board.getBar().getColorIndex(Checker.Color.BLACK), 0);

//     }
// }
