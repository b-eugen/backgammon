public class BarView {
    
    /** 
     * @param bar
     * @return String[]
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
