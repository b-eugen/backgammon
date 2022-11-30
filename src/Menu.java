/**
 * Represents the main menu of the backgammon program 
 * @version 1 2022-21-11
 * @author Aness Al-Qawlaq
 */

import java.io.File;

 /**
 * {@code Menu} is the main menu of the backgammon program
 */

public class Menu {
    
    /**
     * method to validate the menu input 
     * @param userInput - requested string input
     * @return boolean - true if input is valid
     */
    public boolean validateMenuInput(String userInput){
        if(userInput.toLowerCase().equals("new") || userInput.toLowerCase().equals("no")){
            return true;
        }
        else if(userInput.toLowerCase().matches("[t][e][s][t]\\s[a-z\\d]+.txt")){
            if(new File(userInput.replace("test ", "")).isFile()){
                return true;
            }
        }
        return false;
    }

    /**
     * method to immediately halt program execution
     */
    public static void immediateExit(){
        MenuView.exitMessage();
        System.exit(1);
    }

    
    /** 
     * main program execution
     * @param args
     */
    public static void main(String[] args)
    {
        MultiScanner in = new MultiScanner(System.in);
        Menu menu = new Menu();
        MenuView.welcomeMessage();

        while(MenuView.askNewMatch(in, menu)){
            Match match = new Match();
            match.matchRoutine(in);
        }

        MenuView.exitMessage();        
        in.closeScanner();
    }
}
