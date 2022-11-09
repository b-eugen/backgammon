public class DieView {
    public static String toString(Die die){
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