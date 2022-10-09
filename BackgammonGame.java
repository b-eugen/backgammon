package backgammon;

import backgammon.CheckerColumn;

public class BackgammonGame {
    public static void main(String[] args)
    {
        Checker checker = new Checker();
        Checker checker2 = new Checker(Checker.Color.RED);
        System.out.println(checker +" "+ checker2);

        // CheckerColumn col = new CheckerColumn();
        // System.out.println(col);
        // col.append(checker2);
        // System.out.println(col);
        // System.out.println(col.pop());
        // System.out.println(col);
    }
}
