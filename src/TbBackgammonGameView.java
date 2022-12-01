/* Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen*/

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TbBackgammonGameView {
    @Test
    void testGenKeyCode(){
        assertEquals("AA", BackgammonGameView.genKeyCode(0));
    }

    @Test
    void testGenKeyCodeEdge(){
        assertTrue("AZ".equals(BackgammonGameView.genKeyCode(25)) && "BA".equals(BackgammonGameView.genKeyCode(26)) && "BZ".equals(BackgammonGameView.genKeyCode(51)) && "CA".equals(BackgammonGameView.genKeyCode(52)));
    }

    @Test
    void testReverseKeyCodeEdge(){
        assertTrue(BackgammonGameView.reverseKeyCode("AA") == 0 && BackgammonGameView.reverseKeyCode("AZ") == 25 && BackgammonGameView.reverseKeyCode("BA") == 26 && BackgammonGameView.reverseKeyCode("BZ") == 51 && BackgammonGameView.reverseKeyCode("CA") == 52);
    }
}
