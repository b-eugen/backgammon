/**
 * Represents the view class (MVC) of the match 
 * @version 1 2022-21-11
 * @author Aness Al-Qawlaq
 */

 /**
 * {@code MatchView} is the (MVC) view of the Match object
 */
public class MatchView { 
    /** 
     * method to validate the entered names
     * @param userInput - the requested user input
     * @return boolean - true if the input is valid
     */
    public static boolean validateNames(String userInput){
        if(userInput.length() > 15){
            System.out.println("Please ensure your name includes no more than 15 characters");
            return false;
        }
        else if(userInput.length() < 1){
            System.out.println("Please ensure your name includes no less than 1 character");
            return false;
        }
        return true;
    }

    
    /** 
     * method to ask for the length of the match
     * @param in - input scanner
     * @return int - the length requested
     */
    public static int askMatchLength(MultiScanner in){
        String userInput;
        do{
            System.out.println("What is the preferred length for this match? Please enter a positive integer.");
            userInput = in.nextLine();
        }
        while(!userInput.matches("[+]?[0-9]+"));

        return Integer.valueOf(userInput);
    }

     /** 
     * method to notify players of a new match
     */
    public static void newMatchNotice(){
        System.out.println(String.format(" %1$-190s", BackgammonGameView.spacer));
        System.out.println(String.format(" %1$-190s", "A new match has started!!"));
        System.out.println(String.format(" %1$-190s", BackgammonGameView.spacer));
    }

    
    /** 
     * method which gets player names 
     * @param in - input scanner
     * @return String[] - array of valid entered names
     */
    public static String[] getNames(MultiScanner in){
        String names[] = new String[2];
        String userInput;

        System.out.println("\n\nPlayer 1, enter your name: ");

        do{
            userInput = in.nextLine();
        }
        while(!validateNames(userInput));

        names[0] = userInput;
        System.out.println("\n\nPlayer 2, enter your name: ");
        
        do{
            userInput = in.nextLine();
        }
        while(!validateNames(userInput));

        names[1] = userInput;
        System.out.println("\nFor usage instructions, please enter 'hint'");
        return names;
    }

    
    /** 
     * method to declare the winner of the entire match
     * @param p - the winning player
     * @param winningScore - the score of the winning player
     */
    public static void declareMatchWinner(Player p, int winningScore){
        System.out.println(String.format(" %1$-190s", BackgammonGameView.spacer));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", " _    _ _                       _ "));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", "| |  | (_)                     | |"));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", "| |  | |_ _ __  _ __   ___ _ __| |"));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", "| |/\\| | | '_ \\| '_ \\ / _ \\ '__| |"));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", "\\  /\\  / | | | | | | |  __/ |  |_|"));
        System.out.print(String.format("%1$45s", ""));
        System.out.println(String.format(" %1$-85s", " \\/  \\/|_|_| |_|_| |_|\\___|_|  (_)"));
        System.out.print(String.format("\n%1$57s", ""));
        System.out.println(String.format(" %1$-70s\n", p.getName() + " Wins the entire match with score: " + winningScore + "!"));
        System.out.println(String.format(" %1$-190s\n\n", BackgammonGameView.spacer));
    }
}
