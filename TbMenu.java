import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class TbMenu {
    Menu menu;

    @BeforeEach
    void setUp()
    {
        menu = new Menu();
    }

    @Test
    void testValidateMenuInputNew(){
        String testStr = "new";

        assertTrue(menu.validateMenuInput(testStr));
    }


    @Test
    void testValidateMenuInputNo(){
        String testStr = "no";

        assertTrue(menu.validateMenuInput(testStr));
    }


    @Test
    void testValidateMenuInputNewCapital(){
        String testStr = "NEW";

        assertTrue(menu.validateMenuInput(testStr));
    }


    @Test
    void testValidateMenuInputValidFilename(){
        String filename = "tempTest.txt";
        String command = "test " + filename;
        boolean callResult;
        File file = TbMultiScanner.createEmptyFile(filename);

        callResult = menu.validateMenuInput(command);
        System.out.println(callResult);
        file.delete();
        assertTrue(callResult);
    }


    @Test
    void testValidateMenuInputOtherFileTypes(){
        String filename = "tempTest.docx";
        String command = "test " + filename;
        boolean callResult;
        File file = TbMultiScanner.createEmptyFile(filename);

        callResult = menu.validateMenuInput(command);
        System.out.println(callResult);
        file.delete();
        assertFalse(callResult);
    }

}
