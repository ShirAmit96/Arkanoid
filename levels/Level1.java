package levels;
import geometry.Point;
import geometry.Rectangle;
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
 * This interface represents level 1.
 */
public class Level1 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v = Velocity.fromAngleAndSpeed(360, 10);
        List<Velocity> initialBallVelocities = new ArrayList<Velocity>();
        initialBallVelocities.add(v);
        return initialBallVelocities;
    }
    @Override
    public int paddleSpeed() {
        return 7;
    }
    @Override
    public int paddleWidth() {
        return 100;
    }
    @Override
    public String levelName() {
        return "Direct Hit";
    }
    @Override
    public Sprite getBackground() {
        Sprite background = new GameBackground();
        return background;
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Rectangle rec = new Rectangle(new Point(360, 200), 70, 70);
        Block b = new Block(rec, Color.red);
        blocks.add(b);
        return blocks;
    }
    @Override
     public int numberOfBlocksToRemove() {
        return blocks().size();
     }
}
