package interfaces;
import other.Velocity;
import sprites.Block;
import java.util.List;
/**
 * @author Shir Amit
 * Id: 207640228
 * This interface represents a level information.
 */
public interface LevelInformation {
    /**
     * This method returns the required number of balls in the level.
     * @return the number of balls.
     */
    int numberOfBalls();
    /**
     * This method returns a list if the initial velocity of each ball.
     * @return the velocities list
     */
    List<Velocity> initialBallVelocities();
    /**
     * This method returns the required paddle speed.
     * @return the paddle's speed.
     */
    int paddleSpeed();
    /**
     * This method returns the required paddle width.
     * @return the paddle's width.
     */
    int paddleWidth();
    /**
     * This method returns the level's name.
     * @return the he level's name.
     */
    String levelName();
    /**
     * This method returns the background of the level.
     * @return the background of the level.
     */
    Sprite getBackground();
    /**
     * This method returns a blocks list.
     * @return the blocks list
     */
    List<Block> blocks();
    /**
     * This method returns the number of blocks that should be removed before the level is considered to be "cleared".
     * @return the number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
