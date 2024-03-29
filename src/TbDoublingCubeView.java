/* Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen*/
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TbDoublingCubeView {
    DoublingCubeView doublingCubeView;
    @BeforeEach
    void setUp()
    {
        doublingCubeView = new DoublingCubeView();
    }

    @Test
    void testDoublingCubeView() {
        String[] result = DoublingCubeView.toArrayOfStrings(new DoublingCube());
        String[] expectation = new String[] {"   ----   ", String.format("  | %2d |  ", 64), "   ----   "};
        assertEquals(String.join("\n", result), String.join("\n", expectation));


    }
}
