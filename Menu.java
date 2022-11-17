public class Menu {
    
    public boolean validateMenuInput(String userInput){
        if(userInput.toLowerCase().equals("new") || userInput.toLowerCase().equals("no") || userInput.toLowerCase().matches("[t][e][s][t]\\s[a-z\\d]+.txt")){
            return true;
        }
        return false;
    }

    public static void immediateExit(){
        System.out.println("\n\nYou have quit backgammon.\n\n");
        System.exit(1);
    }

    public static void main(String[] args)
    {
        MultiScanner in = new MultiScanner();
        Menu menu = new Menu();
        MenuView.welcomeMessage();

        while(MenuView.askNewMatch(in, menu)){
            Match match = new Match();
            match.matchRoutine(in);
        }
        
        in.closeScanner();
    }
}
