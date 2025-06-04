package base;

import java.util.HashSet;
import java.util.Set;

public class Card {
    public String name;
    public int size = 1;
    public Set<String> tags = new HashSet<>();
    public double internalCooldown = .2;
    public Double cooldown;
    public Double time = 0.0;
    public int startingTier = 1;
    public int currentTier = 1;
    public int bonusValue = 0;
    private boolean isHasted = false;
    private boolean isSlowed = false;
    private int triggerBacklog = 0;

    public Card() {

    }

    /**
     * Push the card forward in time.
     * @param clockTick Time to add.
     * @return True if the card hit its cooldown.
     */
    public boolean incrementTime(double clockTick) {
        double timeDelta = clockTick;
        timeDelta = isHasted ? timeDelta * 2 : timeDelta;
        timeDelta = isSlowed ? timeDelta / 2 : timeDelta;
        time += timeDelta;



        if(time >= cooldown) {
            time = 0.0;
            return true;
        }
        return false;
    }

    public void addTrigger(int triggerAmount) {
        triggerBacklog += triggerAmount;
    }

    private void trigger() {

    }

    public int getValue() {
        return 2 * currentTier * size + bonusValue;
    }

}
