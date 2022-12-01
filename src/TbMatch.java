/* Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen*/
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class TbMatch {
    Match match;

    @BeforeEach
    void setUp(){
        this.match = new Match();
    }

    @Test
    void testNotMatchOverInitially(){
        String filename = "testTempMatch.txt";
        String fileContents = "Aness\nYev";
        MultiScanner multiScanner = new MultiScanner(System.in);
        this.match.setLength(2);
        File file = TbMultiScanner.createFileWithContents(filename, fileContents);

        multiScanner.setReadFile(filename);
        this.match.setNames(multiScanner);
        multiScanner.closeScanner();
        file.delete();
        assertFalse(match.checkMatchOver());
    }
}
