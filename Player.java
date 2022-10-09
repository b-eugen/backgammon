package backgammon;

public class Player {
    private String name;
    private Checker.Color color;

    public Player()
    {
        this.name = "Player";
        this.color = Checker.Color.WHITE;
    }

    public Player(Checker.Color color)
    {
        this.color = color;
    }

    public Player(String name, Checker.Color color)
    {
        this.name = name;
        this.color = color;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return this.name;
    }

    public String getColor()
    {
        return this.getColor();
    }
}
