/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * This program is the Die class, which allows to view the die
 * @version 1 2022-21-11
 * @author Aness Al-Qawlaq
 */
/**
 * A {@code DieView} object displays the die as a string
 */
public class DieView {
    
    /** 
     * @param die - object of Die class
     * @return String - string representation  of the die
     */
    public static String display(Die die){
        switch(die.getLastRoll()) {
            case 1:
                return("⚀");
            case 2:
                return("\u2681");
            case 3:
                return("⚂");
            case 4:
                return("⚃");
            case 5:
                return("⚄");
            case 6:
                return("⚅");
            default:
                return("");
        }
    }
}