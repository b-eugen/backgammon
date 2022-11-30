/**
 * Represents a user/file input scanner object
 * @version 1 2022-21-11
 * @author Aness Al-Qawlaq
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

 /**
 * {@code MultiScanner} is a flexible Scanner which can read from a file or user input as required. It is used throughout all classes
 * in this program to allow for dynamic switching between user input and file input for all possible commands. 
 */

public class MultiScanner {
    Scanner userIn;
    Scanner fileIn;
    Scanner currentScanner;

    public MultiScanner(InputStream in){
        this.userIn = new Scanner(in);
        this.currentScanner = this.userIn;
    }

    
    /** 
     * method which sets the MultiScanner object to read from the requested file until all commands within the file are read
     * @param filename - the name of the file to read from
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

    /** 
     * method which sets the MultiScanner object to read from user input until further notice
     */
    public void setReadUser(){
        if(this.fileIn != null){
            this.fileIn.close();  //close the file scanner if one was open
        }
        this.currentScanner = this.userIn;
    }

    
    /** 
     * method which mimics the Scanner.nextLine() method, reading the next line from user input / file as required. 
     * If the file has no more lines to read from, the input is taken directly from the user instead
     * @return String - the read line
     */
    public String nextLine(){
        String userInput;

        if(!this.currentScanner.hasNextLine()){
            setReadUser();  //if the current scanner is set to read from a file where EOF is reached, set the current input to be from the user instead 
        }
        userInput = this.currentScanner.nextLine(); //take the input from the current scanner (file or user as required)
        
        if(userInput.toLowerCase().matches("[t][e][s][t]\\s[a-z\\d]+.txt")){
            this.setReadFile(userInput.replace("test ", "")); //if the user input is from test file, set the current scanner to be from the requested file at any time during program execution
        }
        if(userInput.toLowerCase().equals("quit")){
            Menu.immediateExit();  //if the command read is quit, then immediately quit at any time during program execution
        }
        if(currentScanner == fileIn){
            System.out.println("Command read from file: " + userInput); //if the current scanner is set to the file scanner, print out the command for testing purposes
        }
        return userInput;
    }

    /** 
     * method which closes all open scanners
     */
    public void closeScanner(){
        if(this.userIn != null){
            this.userIn.close();
        }
        if(this.fileIn != null){
            this.fileIn.close();
        }
        if(this.currentScanner != null){
            this.currentScanner.close();
        }
    }
}
