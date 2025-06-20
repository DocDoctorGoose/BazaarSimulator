package base;

import java.util.Random;

public class GameMaster {
    private Player player;
    private double clockTick = .1;
    public static GameClock gameClock = new GameClock();
    public static Random rand = new Random();


    public GameMaster() {
        player = new Player(250);
    }

    public void start() {
        while(gameClock.getCurrentTime() <= 30) {
            gameClock.tick(clockTick);
            player.tick(clockTick);
        }
        System.out.println(player.getOutDamage());
    }


}
