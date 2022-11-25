import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
public class TbBoardView {
    BoardView boardView;
    @BeforeEach
    void setUp()
    {
        boardView = new BoardView();
    }

    @Test
    void testView()
    {
        String dispStr = boardView.display(new Board(), Checker.Color.BLACK);
        String dispStr2 = boardView.display(new Board(), Checker.Color.RED);
        assertTrue(dispStr.length()>0 && dispStr.length() ==dispStr2.length());
    }
}
