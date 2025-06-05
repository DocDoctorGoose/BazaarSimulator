package base;

public class GameMaster {
    private Player player;
    private Double clock = 0.0;
    private double clockTick = .1;
    public static GameClock gameClock = new GameClock();


    public GameMaster() {

    }

    public void start() {
        while(gameClock.getCurrentTime() <= 30) {
            gameClock.tick(clockTick);
        }
    }


}
