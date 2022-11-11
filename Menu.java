import java.util.Scanner;

public class Menu {
    
    public boolean validateMenuInput(String userInput){
        if(userInput.toLowerCase().equals("yes") || userInput.toLowerCase().equals("no")){
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
        Scanner in = new Scanner(System.in);
        Menu menu = new Menu();
        MenuView.welcomeMessage();

        do{
            Match match = new Match();
            match.matchRoutine(in);
        }
        while(MenuView.askNewMatch(in, menu));
        
        in.close();
    }
}
