package backgammon;

public class BackgammonGame {
    public static void main(String[] args)
    {
        Checker checker = new Checker();
        Checker checker2 = new Checker(Checker.Color.RED);
        System.out.println(checker +" "+ checker2);
    }
}
