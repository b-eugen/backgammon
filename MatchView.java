public class MatchView { 

    public static boolean validateNames(String userInput){
        if(userInput.length() > 15){
            System.out.println("Please ensure you name includes no more than 15 characters");
            return false;
        }
        else if(userInput.length() < 1){
            System.out.println("Please ensure you name includes no less than 1 character");
            return false;
        }
        return true;
    }
    

    public static int askMatchLength(MultiScanner in){
        String userInput;
        do{
            System.out.println("What is the preferred length for this match? Please enter a positive integer.");
            userInput = in.nextLine();
        }
        while(!userInput.matches("[+]?[0-9]+"));

        return Integer.valueOf(userInput);
    }

    public static void newMatchNotice(){
        System.out.println(String.format(" %1$-190s", BackgammonGameView.spacer));
        System.out.println("A new match has started!!");
        System.out.println(String.format(" %1$-190s", BackgammonGameView.spacer));
    }

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
