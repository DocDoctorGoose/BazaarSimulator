package base;

import cards.listener.ICardTriggerListener;
import utils.CardTags;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Card {
    private List<ICardTriggerListener> listeners = new ArrayList<>();
    public String name;
    public int size = 1;
    public Set<CardTags> tags = new HashSet<>();
    public static double INTERNAL_COOLDOWN = .2;
    public Double cooldown;
    public Double time = 0.0;
    public int startingTier = 1;
    public int currentTier = 1;
    public int bonusValue = 0;
    protected boolean isHasted = false;
    protected boolean isSlowed = false;
    protected int triggerBacklog = 0;
    protected Board board;
    public Integer position;
    protected GameClock gameClock = GameMaster.gameClock;
    protected double lastTriggerTime = 0;
    protected Integer damage;
    protected Integer shield;
    protected Integer poison;
    protected Integer burn;
    protected Integer regeneration;
    protected Double critChance = 0.0;
    public int critMultiplier = 1;

    public Card() {
        //Make sure to set the board and position before using.
    }

    public Card(Board board, int position) {
        this.board = board;
        this.position = position;
    }

    /**
     * Push the card forward in time. Assuming it is less than the internal cooldown
     * @param clockTick Time to add.
     */
    public void incrementTime(double clockTick) {
        if(cooldown == null) {
            return;
        }

        double timeDelta = clockTick;
        timeDelta = isHasted ? timeDelta * 2 : timeDelta;
        timeDelta = isSlowed ? timeDelta / 2 : timeDelta;
        time += timeDelta;

        if(time >= cooldown) {
            time = 0.0;
            addTrigger(1);
        }

        if(triggerBacklog > 0) {
            resolveTriggerBacklog();
        }
    }

    public void initialize() {
        //Typically do nothing unless you need to listen to other card's triggers.
    }

    private void resolveTriggerBacklog() {
        double currentTime = gameClock.getCurrentTime();
        if((currentTime - lastTriggerTime) >= INTERNAL_COOLDOWN) {
            addTriggerToBoardStack();
            lastTriggerTime = currentTime;
            triggerBacklog--;
        }
    }

    public void addTrigger(int triggerAmount) {
        triggerBacklog += triggerAmount;
    }

    protected void addTriggerToBoardStack() {
        board.addToStack(this);
    }

    public int getValue() {
        return 2 * currentTier * size + bonusValue;
    }

    public void setBoard(Board board, int position) {
        this.board = board;
        this.position = position;
    }

    protected boolean checkCrit() {
        return critChance > GameMaster.rand.nextDouble();
    }

    public void trigger() {
        cardAction();
        listeners.forEach(listener -> listener.cardTriggered(this));
    }

    public void addListener(ICardTriggerListener listener) {
        listeners.add(listener);
    }

    protected abstract void cardAction();
}
