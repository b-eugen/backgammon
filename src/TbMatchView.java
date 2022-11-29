import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

public class TbMatchView {

    @Test
    void testValidateNamesNoName(){
        String nameToTest = "";
        
        assertFalse(MatchView.validateNames(nameToTest));
    }

    @Test
    void testValidateNamesLongName(){
        String nameToTest = "thisIsAVeryLongAndUnacceptableNameThatShouldBeRejected";
        
        assertFalse(MatchView.validateNames(nameToTest));
    }

    @Test
    void testValidateNamesValidName(){
        String nameToTest = "Aness";
        
        assertTrue(MatchView.validateNames(nameToTest));
    }

    @Test
    void testAskMatchLengthValid(){
        String filename = "tempTest.txt";
        String requestedLength = "3";
        File file = TbMultiScanner.createFileWithContents(filename, requestedLength);
        MultiScanner multiScanner = new MultiScanner(System.in);
        int callResult;
        multiScanner.setReadFile(filename);

        callResult = MatchView.askMatchLength(multiScanner);

        multiScanner.closeScanner();
        file.delete();
        assertEquals(3, callResult);
    }
}
