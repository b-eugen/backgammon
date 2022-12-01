/**
 * Group 47: Aness Al-Qawlaq, Yevhenii Mormul
 * Github IDs: anessk01, b-eugen
 * This program is the Checker class, which represents the checker
 * @version 1 2022-21-11
 * @author Yevhenii Mormul
 */
/**
 * A {@code Checker} object represents the checker
 */
public class Checker {

    //color encoding
    private static final String RED_COLOR="\033[0;31m";
    private static final String BLACK_COLOR="\033[0m";
    private static final String CYAN_COLOR="\033[0;36m";

    /**
     * A {@code Color} object represents the color
     */
    public enum Color{RED(RED_COLOR), BLACK(BLACK_COLOR), INVALID(CYAN_COLOR);
        private String color;
        private Color(String color)
        {
            this.color=color;
        }

        @Override
        public String toString(){
            return this.color;
        }
    }

    private Color color;

    public Checker()
    {
        this.color = Color.BLACK;
    }

    public Checker(Color color)
    {
        this.color = color;
    }
    
    
    /** 
     * @return Color - color of the checker
     */
    public Color getColor()
    {
        return this.color;
    }

    
    /** 
     * @return String - string representation of the checker
     */
    public String toString()
    {
        return String.format("%s%s%s", this.color, "⬤", BLACK_COLOR);
    }

    
    /** 
     * @return boolean - true, if checker is red or black
     */
    public boolean isValid()
    {
        if (this.color != Color.INVALID)
        {
            return true;
        }
        return false;
    }
}
