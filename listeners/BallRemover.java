package listeners;
import sprites.Ball;
import other.Counter;
import gamemngr.GameLevel;
import sprites.Block;
import interfaces.HitListener;
/**
 * @author Shir Amit
 * ID: 207640228
 * The class represents a ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * Constructor.
     * @param game           the game.
     * @param remainingBalls the counter of the remaining balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    /**
     * This method is called whenever a ball hits the 'death region',
     * in order to remove the ball from the game.
     * @param beingHit         the 'death region'.
     * @param hitter the ball that hit the 'death region'.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       hitter.removeFromGame(this.game);
       this.remainingBalls.decrease(1);
    }
}
