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
 * This interface represents level 2.
 */
public class Level2 implements LevelInformation {
    private final int  numberOfBalls = 10;
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new ArrayList<Velocity>();
        for (int i = 0; i < (this.numberOfBalls / 2); ++i) {
            Velocity v = Velocity.fromAngleAndSpeed(300 + (15 * i), 11);
            initialBallVelocities.add(v);
        }
        for (int i = 0; i < (this.numberOfBalls / 2); ++i) {
            Velocity v = Velocity.fromAngleAndSpeed(0 + (15 * i), 11);
            initialBallVelocities.add(v);
        }
        return initialBallVelocities;
    }
    @Override
    public int paddleSpeed() {
        return 3;
    }
    @Override
    public int paddleWidth() {
        return 600;
    }
    @Override
    public String levelName() {
        return "Wide Easy";
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
        colors.add(Color.red);
        colors.add(Color.red);
        colors.add(Color.ORANGE);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.GREEN);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.BLUE);
        colors.add(Color.PINK);
        colors.add(Color.PINK);
        colors.add(Color.cyan);
        colors.add(Color.cyan);
        for (int i = 0; i < colors.size(); ++i) {
            Rectangle rec = new Rectangle(new Point(20 + (50.6 * i), 250), 50.6, 20);
            Block b = new Block(rec, colors.get(i));
            blocks.add(b);
        }
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

}
