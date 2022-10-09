package backgammon;

import backgammon.CheckerColumn;
// cd ~/Desktop/Java && javac backgammon/*.java && java backgammon/BackgammonGame && cd ~/Desktop/Java/backgammon
public class BackgammonGame {
    public static void main(String[] args)
    {
        Checker checker = new Checker();
        Checker checker2 = new Checker(Checker.Color.RED);
        System.out.println(checker +" "+ checker2);

        Bar bar = new Bar();

        System.out.println(bar);
        for (String line : bar.toArrayOfStrings())
        {
            System.out.println(line);
        }
        bar.append(checker2);
        System.out.println(bar);
        for (String line : bar.toArrayOfStrings())
        {
            System.out.println(line);
        }
        // CheckerColumn col = new CheckerColumn();
        // System.out.println(col);
        // col.append(checker2);
        // System.out.println(col);
        // System.out.println(col.pop());
        // System.out.println(col);
    }
}
