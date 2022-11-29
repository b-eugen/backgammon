import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class MultiScanner {
    Scanner userIn;
    Scanner fileIn;
    Scanner currentScanner;

    public MultiScanner(InputStream in){
        this.userIn = new Scanner(in);
        this.currentScanner = this.userIn;
    }

    
    /** 
     * @param filename
     */
    public void setReadFile(String filename){
        try {
            File file = new File(filename);
            this.fileIn = new Scanner(file);
            this.currentScanner = this.fileIn;
        } 
        catch (FileNotFoundException e) {
            System.out.println("Cannot read from file: " + filename + " - try again.");
        }
    }

    public void setReadUser(){
        if(this.fileIn != null){
            this.fileIn.close();
        }
        this.currentScanner = this.userIn;
    }

    
    /** 
     * @return String
     */
    public String nextLine(){
        String userInput;

        if(!this.currentScanner.hasNextLine()){
            setReadUser();
        }
        userInput = this.currentScanner.nextLine();
        
        if(userInput.toLowerCase().matches("[t][e][s][t]\\s[a-z\\d]+.txt")){
            this.setReadFile(userInput.replace("test ", ""));
        }
        if(userInput.toLowerCase().equals("quit")){
            Menu.immediateExit();
        }
        if(currentScanner == fileIn){
            System.out.println("Command read from file: " + userInput);
        }
        return userInput;
    }

    public void closeScanner(){
        if(this.userIn != null){
            this.userIn.close();
        }
        if(this.fileIn != null){
            this.fileIn.close();
        }
    }
}
