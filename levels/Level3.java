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
 * This interface represents level 3.
 */
public class Level3 implements LevelInformation {
        private final int  numberOfBalls = 2;
        @Override
        public int numberOfBalls() {
            return this.numberOfBalls;
        }
        @Override
        public List<Velocity> initialBallVelocities() {
            List<Velocity> initialBallVelocities = new ArrayList<Velocity>();
            Velocity v1 = Velocity.fromAngleAndSpeed(330, 8);
            initialBallVelocities.add(v1);
            Velocity v2 = Velocity.fromAngleAndSpeed(30, 8);
            initialBallVelocities.add(v2);
            return initialBallVelocities;
        }
        @Override
        public int paddleSpeed() {
            return 10;
        }
        @Override
        public int paddleWidth() {
            return 100;
        }
        @Override
        public String levelName() {
            return "Green 3";
        }
        @Override
        public Sprite getBackground() {
            GameBackground background = new GameBackground();
            return background;
        }
        @Override
        public List<Block> blocks() {
            List<Block> blocks = new ArrayList<>();
            List<Color> colors = new ArrayList<>();
            colors.add(Color.WHITE);
            colors.add(Color.BLUE);
            colors.add(Color.YELLOW);
            colors.add(Color.RED);
            colors.add(Color.GRAY);
            int blockWidth = 50;
            int blockHeight = 20;
            for (int i = 5; i > 0; --i) {
                Color bColor = colors.get(i - 1);
                for (int j = 0; j < (i + 6); ++j) {
                    int x = (800 - 20 - blockWidth) - (j * blockWidth);
                    int y = 200 + 20 - (i * blockHeight);
                    Point p = new Point(x, y);
                    Rectangle rec = new Rectangle(p, blockWidth, blockHeight);
                    Block block = new Block(rec, bColor);
                    blocks.add(block);
                }
            }
                    return blocks;
            }
            @Override
            public int numberOfBlocksToRemove() {
                    return blocks().size();
                }
            }