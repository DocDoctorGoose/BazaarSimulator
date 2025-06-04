package base;

public class GameMaster {
    private Player player;
    private Double clock = 0.0;
    private double clockTick = .1;


    public GameMaster() {

    }

    public void start() {
        while(clock <= 30) {
            clock += clockTick;
            player.tick(clockTick);
        }
    }


}
