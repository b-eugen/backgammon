public class MenuView {
    public static void welcomeMessage(){
        System.out.println("\nWelcome to Backgammon. Enter quit at any time to exit.");
    }

    public static boolean askNewMatch(MultiScanner in, Menu menu){
        String userInput;
        
        do{
            System.out.println("\n\nNew match? Enter 'new' for new match, (test file.txt for test), or 'no' to exit.\n");
            userInput = in.nextLine();
        }
        while(!menu.validateMenuInput(userInput));
        
        return (!userInput.toLowerCase().equals("no"));
    }

    public static void exitMessage(){
        System.out.println("\n\nYou have quit backgammon.\n\n");
    }
}
