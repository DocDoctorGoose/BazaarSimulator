package base;

import java.util.HashSet;
import java.util.Set;

public class Card {
    public String name;
    public int size = 1;
    public Set<String> tags = new HashSet<>();
    public static double INTERNAL_COOLDOWN = .2;
    public Double cooldown;
    public Double time = 0.0;
    public int startingTier = 1;
    public int currentTier = 1;
    public int bonusValue = 0;
    private boolean isHasted = false;
    private boolean isSlowed = false;
    private int triggerBacklog = 0;
    private Board board;
    public Integer position;
    private GameClock gameClock = GameMaster.gameClock;
    private double lastTriggerTime = 0;

    public Card(Board board, int position) {
        this.board = board;
        this.position = position;
    }

    /**
     * Push the card forward in time. Assuming it is less than the internal cooldown
     * @param clockTick Time to add.
     * @return True if the card hit its cooldown.
     */
    public boolean incrementTime(double clockTick) {
        boolean isTriggered = false;
        double timeDelta = clockTick;
        timeDelta = isHasted ? timeDelta * 2 : timeDelta;
        timeDelta = isSlowed ? timeDelta / 2 : timeDelta;
        time += timeDelta;

        if(time >= cooldown) {
            time = 0.0;
            isTriggered = true;
            addTrigger(1);
        }

        if(triggerBacklog > 0) {
            resolveTriggerBacklog();
        }
        return isTriggered;
    }

    private void resolveTriggerBacklog() {
        double currentTime = gameClock.getCurrentTime();
        if((currentTime - lastTriggerTime) >= INTERNAL_COOLDOWN) {
            trigger();
            lastTriggerTime = currentTime;
            triggerBacklog--;
        }
    }

    public void addTrigger(int triggerAmount) {
        triggerBacklog += triggerAmount;
    }

    private void trigger() {
        //Add a function to the Board's stack that is this card's action. Allow the Board
        // to handle the resolution of multiple triggers happening on the same tick.
    }

    public int getValue() {
        return 2 * currentTier * size + bonusValue;
    }

}
