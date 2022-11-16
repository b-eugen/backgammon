public class MenuView {
    public static void welcomeMessage(){
        System.out.println("\nWelcome to Backgammon. Enter quit at any time to exit.");
    }

    public static boolean askNewMatch(MultiScanner in, Menu menu){
        String userInput;
        
        do{
            System.out.println("\n\nNew match? Enter 'yes' or 'no'.\n");
            userInput = in.nextLine();
        }
        while(!menu.validateMenuInput(userInput));
        
        return userInput.toLowerCase().equals("yes");
    }
}
