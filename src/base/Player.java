package base;

public class Player {
    public Board board;
    public int health;
    public int shield = 0;
    public int poison = 0;
    public int burn = 0;
    public int regeneration = 0;

    public Player(int health) {
        this.health = health;
    }

    public void tick(Double clockTick) {
        board.incrementCards(clockTick);
    }
}
