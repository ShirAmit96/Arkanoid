package interfaces;
import sprites.Block;
import sprites.Ball;

/**
 * @author Shir Amit
 * ID: 207640228
 * This interface represents a Hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the being hit object.
     * @param hitter   the hitting ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}