import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class TbDoublingCube {
    DoublingCube doublingCube;
    @BeforeEach
    void setUp()
    {
        doublingCube = new DoublingCube();
    }

    @Test
    void testDoublingCube() {
        assertEquals(doublingCube.getCurrentStake(), 1);
        doublingCube.doubleStakes(Checker.Color.BLACK);
        assertEquals(doublingCube.getCurrentStake(), 2);
        assertEquals(doublingCube.getOwner(), Checker.Color.RED);

        doublingCube.doubleStakes(Checker.Color.BLACK);
        assertEquals(doublingCube.getCurrentStake(), 2);
        assertEquals(doublingCube.getOwner(), Checker.Color.RED);

        doublingCube.doubleStakes(Checker.Color.RED);
        assertEquals(doublingCube.getCurrentStake(), 4);
        assertEquals(doublingCube.getOwner(), Checker.Color.BLACK);

        doublingCube.doubleStakes(Checker.Color.BLACK);
        assertEquals(doublingCube.getCurrentStake(), 8);
        assertEquals(doublingCube.getOwner(), Checker.Color.RED);

        doublingCube.doubleStakes(Checker.Color.RED);
        assertEquals(doublingCube.getCurrentStake(), 16);
        assertEquals(doublingCube.getOwner(), Checker.Color.BLACK);

        doublingCube.doubleStakes(Checker.Color.BLACK);
        assertEquals(doublingCube.getCurrentStake(), 32);
        assertEquals(doublingCube.getOwner(), Checker.Color.RED);

        doublingCube.doubleStakes(Checker.Color.RED);
        assertEquals(doublingCube.getCurrentStake(), 64);
        assertEquals(doublingCube.getOwner(), Checker.Color.BLACK);

        doublingCube.doubleStakes(Checker.Color.BLACK);
        assertEquals(doublingCube.getCurrentStake(), 64);
        assertEquals(doublingCube.getOwner(), Checker.Color.BLACK);
        assertEquals(doublingCube.canDoubleStakes(Checker.Color.BLACK), false);


    }
}
