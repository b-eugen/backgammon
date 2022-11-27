import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class TbBackgammonGame {
    BackgammonGame game;

    ArrayList<Player> setGameUp(){
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("A", Checker.Color.RED));
        players.add(new Player("B", Checker.Color.BLACK));
        game.setUpSequence(new MultiScanner(System.in), players);
        return players;
    }

    @BeforeEach
    void setUp(){
        this.game = new BackgammonGame(2);
    }

    @Test
    void testNoGameOverInitially(){
        setGameUp();
        assertFalse(this.game.checkGameOver());
    }

    @Test
    void testPlayerSwap(){
        setGameUp();
        Player initialFirstPlayer = game.getCurrentPlayer();
        
        game.swapPlayers();
        assertNotEquals(initialFirstPlayer, game.getCurrentPlayer());
    }

    @Test
    void testPlayerSwapCircularity(){
        setGameUp();
        Player initialFirstPlayer = game.getCurrentPlayer();
        
        game.swapPlayers();
        game.swapPlayers();
        assertEquals(initialFirstPlayer, game.getCurrentPlayer());
    }

    @Test
    void testDoubleAbility(){
        MultiScanner multiScanner = new MultiScanner(System.in);
        String filename = "testFile.txt";
        String fileContents = "accept";
        game.setUpSequence(new MultiScanner(System.in), setGameUp());
        
        File file = TbMultiScanner.createFileWithContents(filename,fileContents);
        multiScanner.setReadFile(filename);
        boolean callResult = game.doubleStakes(multiScanner);
        multiScanner.closeScanner();
        file.delete();
        assertFalse(callResult);  //Game should continue
    }

    @Test
    void testHintNotEndingTurn(){
        assertFalse(game.takeAction("hint", new MultiScanner(System.in)));
    }

    @Test
    void testRollEndingTurn(){
        MultiScanner multiScanner = TbMultiScanner.fakeUserInput("AA");
        setGameUp();
        
        boolean callResult = game.takeAction("roll", multiScanner);
        
        multiScanner.closeScanner();
        assertTrue(callResult);
    }

    @Test
    void testRollOffDifferentRolls(){
        setGameUp();
        assertNotEquals(game.getDie1().getLastRoll(), game.getDie2().getLastRoll());
    }

    @Test
    void testValidateMoveInputValid(){
        assertTrue(game.validateMoveInput("AA", 2));
    }

    @Test
    void testValidateMoveInputInvalid(){
        assertTrue(!game.validateMoveInput("AAA", 2) && !game.validateMoveInput("A2", 2) && !game.validateMoveInput("A", 2) && !game.validateMoveInput("", 2));
    }

    @Test 
    void testValidateInputValid(){
        assertTrue(game.validateInput("ROLL") && game.validateInput("pip") && game.validateInput("hint"));
    }

    @Test 
    void testValidateInputInvalid(){
        assertTrue(!game.validateInput("rull") && !game.validateInput("roll ") && !game.validateInput(" roll"));
    }

}
