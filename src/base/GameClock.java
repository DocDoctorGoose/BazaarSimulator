package base;

public class GameClock {
    private double currentTime = 0.0;

    public void tick(double timeDelta) {
        currentTime += timeDelta;
    }

    public double getCurrentTime() {
        return currentTime;
    }
}
