import java.io.File;

public class Menu {
    
    
    /** 
     * @param userInput
     * @return boolean
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

    public static void immediateExit(){
        MenuView.exitMessage();
        System.exit(1);
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args)
    {
        MultiScanner in = new MultiScanner();
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
