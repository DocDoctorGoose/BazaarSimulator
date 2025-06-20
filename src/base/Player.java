package base;

import cards.Bayonet;
import cards.Claws;

public class Player {
    public Board board;
    public int health;
    private int shield = 0;
    private int poison = 0;
    private int burn = 0;
    private int regeneration = 0;
    private int outDamage = 0;
    private int outShield = 0;
    private int outPoison = 0;
    private int outBurn = 0;
    private int outRegeneration = 0;

    public Player(int health) {
        this.health = health;
        board = new Board(this);

        initializeCards();
    }

    public void tick(Double clockTick) {
        board.incrementCards(clockTick);
    }

    private void initializeCards() {
        Card card1 = new Claws();
        board.addCard(card1);

        Card card2 = new Bayonet();
        board.addCard(card2);



        board.initializeCards();
    }

    public void addOutDamage(int damage) {
        outDamage += damage;
    }

    public int getOutDamage() {
        return outDamage;
    }

    public int getOutShield() {
        return outShield;
    }

    public int getOutPoison() {
        return outPoison;
    }

    public int getOutBurn() {
        return outBurn;
    }

    public int getOutRegeneration() {
        return outRegeneration;
    }
}
