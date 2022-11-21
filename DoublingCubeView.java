/**
 * This program is the DoublingCubeView class
 * @version 1 2022-21-11
 * @author Yevhenii Mormul
 */


/**
 * A {@code DoublingCubeView} object allows to view the doubling cube
 */
public class DoublingCubeView {
    public static final int height = 3;//height of the cube
    
    /** 
     * @param cube - DoublingCube to be displayed
     * @return String[] – representation of the DoublingCube in Strings
     */
    public static String[] toArrayOfStrings(DoublingCube cube)
    {
        
        String[] output = new String[] {"   ----   ", "", "   ----   "};
        int stake = cube.getCurrentStake();
        if (stake == 1)
        {
            stake = 64;
        }
        output[1] = String.format("  | %2d |  ", stake);
        return output;
    }
}
