import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class TbMultiScanner {
    MultiScanner multiScanner;

    static File createFileWithContents(String filename, String contents){
        // helper method for tests to create files with certain string content
        File file = new File(filename);
        try{
            file.createNewFile();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(contents);
            fileWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    static File createEmptyFile(String filename){
        // helper method for tests to create empty files
        File file = new File(filename);
        try{
            file.createNewFile();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return file;
    }

    @Test
    void testUserInputRead(){
        // Test multiscanner ability to read user input
        String testStr = "this is a test string";
        InputStream in = new ByteArrayInputStream(testStr.getBytes());
        System.setIn(in);

        this.multiScanner = new MultiScanner(System.in);
        assertEquals(multiScanner.nextLine(), testStr);
    }

    @Test
    void testFileReadWhenFileNotThere(){
        // By design, the multi scanner should revert to reading user input when it cannot read from the file
        String testStr = "this is a test string";
        InputStream in = new ByteArrayInputStream(testStr.getBytes());
        System.setIn(in);

        this.multiScanner = new MultiScanner(System.in);
        multiScanner.setReadFile("doesNotExist.txt");
        assertEquals(multiScanner.nextLine(), testStr);
    }
    
    @Test
    void testFileReadWhenFileIsThere(){
        // The multiscanner should read from file if the it is set to read from file that exists
        String testStrUser = "this is a user test string";
        InputStream in = new ByteArrayInputStream(testStrUser.getBytes());
        System.setIn(in);
        this.multiScanner = new MultiScanner(System.in);

        String filename = "tempTest.txt";
        String testStrFile = "this is a file test string";
        String readResult;
        File file = createFileWithContents(filename, testStrFile);
        
        multiScanner.setReadFile(filename);
        readResult = multiScanner.nextLine();
        multiScanner.closeScanner();
        try {
            in.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.delete());
        assertEquals(readResult, testStrFile);
    }
    
}
