
public class Checker {

    private static final String RED_COLOR="\033[0;31m";
    private static final String BLACK_COLOR="\033[0m";
    private static final String CYAN_COLOR="\033[0;36m";

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
     * @return Color
     */
    public Color getColor()
    {
        return this.color;
    }

    
    /** 
     * @return String
     */
    public String toString()
    {
        return String.format("%s%s%s", this.color, "⬤", BLACK_COLOR);
    }

    
    /** 
     * @return boolean
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
