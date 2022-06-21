package listeners;
import sprites.Ball;
import other.Counter;
import interfaces.HitListener;
import sprites.Block;

/**
 * @author Shir Amit
 * ID: 207640228
 * This class represents a score tracking indicator.
 */
public class ScoreTrackingListener implements HitListener {
     private Counter currentScore;
    /**
     * Constructor.
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * This method is called in order to increase score counter in 5 points whenever a block is being hit.
     * @param beingHit the being hit object.
     * @param hitter   the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
