package levels;
import geometry.Point;
import interfaces.LevelInformation;
import interfaces.Sprite;
import other.Velocity;
import sprites.Block;
import sprites.GameBackground;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Shir Amit
 * ID:207640228
 * This interface represents level 4.
 */
public class Level4 implements LevelInformation {
    private final int  numberOfBalls = 3;
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new ArrayList<Velocity>();
       Velocity v1 = Velocity.fromAngleAndSpeed(330, 7);
        Velocity v2 = Velocity.fromAngleAndSpeed(360, 7);
        Velocity v3 = Velocity.fromAngleAndSpeed(30, 7);
        initialBallVelocities.add(v1);
        initialBallVelocities.add(v2);
        initialBallVelocities.add(v3);
        return initialBallVelocities;
    }
    @Override
    public int paddleSpeed() {
        return 10;
    }
    @Override
    public int paddleWidth() {
        return 120;
    }
    @Override
    public String levelName() {
        return "Final Four";
    }
    @Override
    public Sprite getBackground() {
        GameBackground background = new GameBackground();
        return background;
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        List<Color> colors = new ArrayList<Color>();
        colors.add(Color.GRAY);
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.GREEN);
        colors.add(Color.WHITE);
        colors.add(Color.PINK);
        colors.add(Color.CYAN);
        for (int j = 0; j < 7; ++j) {
            for (int i = 0; i < 15; ++i) {
                geometry.Rectangle rec = new geometry.Rectangle(new Point(20 + (50.6 * i), 80 + (j * 20)), 50.6, 20);
                Block b = new Block(rec, colors.get(j));
                blocks.add(b);
            }
        }
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

}
