/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * This program is the BarView class, which displayes the Bar
 * @version 1 2022-21-11
 * @author Yevhenii Mormul
 */
/**
 * A {@code BarView} object represents a bar
 */
public class BarView {
    
    /** 
     * @param bar - bar to be displayed
     * @return String[] - string representation of the bar
     */
    public static String[] toArrayOfStrings(Bar bar)
    {
        return new String[] {"|‾‾‾‾‾|",
                            "|     |", 
                            "|     |",
                            String.format("|  %s  |", new Checker(Checker.Color.RED)), 
                            String.format("|%3d  |", bar.colorCount(Checker.Color.RED)), 
                            "| Bar |", 
                            String.format("|  %s  |", new Checker(Checker.Color.BLACK)), 
                            String.format("|%3d  |", bar.colorCount(Checker.Color.BLACK)), 
                            "|     |",
                            "|     |",
                            "|_____|"};
    }
}
