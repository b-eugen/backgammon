public class DoublingCubeView {
    public static final int height = 3;
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
