import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MultiScanner {
    Scanner userIn;
    Scanner fileIn;
    Scanner currentScanner;

    public MultiScanner(){
        this.userIn = new Scanner(System.in);
        this.currentScanner = this.userIn;
    }

    public void setReadFile(String filename){
        try {
            File file = new File(filename);
            this.fileIn = new Scanner(file);
        } 
        catch (FileNotFoundException e) {
            System.out.println("Cannot read from file: " + filename);
        }
        this.currentScanner = this.fileIn;
    }

    public void setReadUser(){
        this.fileIn.close();
        this.currentScanner = this.userIn;
    }

    public String nextLine(){
        if(!this.currentScanner.hasNextLine()){
            setReadUser();
        }
        return this.currentScanner.nextLine();
    }

    public void closeScanner(){
        this.userIn.close();
    }
}
