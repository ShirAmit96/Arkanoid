package listeners;
import sprites.Ball;
import interfaces.HitListener;
import gamemngr.GameLevel;
import other.Counter;
import sprites.Block;
/**
 * @author Shir Amit
 * ID: 207640228
 * The class represents a block remover.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * Constructor.
     * @param game the game.
     * @param removedBlocks the counter of the remaining blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * This method is called whenever a ball hits a block,in order to remove the block from the game.
     * @param beingHit the block that was being hit.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
    }
}