package sprites;
import biuoop.DrawSurface;
import interfaces.Sprite;
import other.Counter;
import geometry.Rectangle;
import gamemngr.GameLevel;
import java.awt.Color;
/**
 * @author Shir Amit
 * ID: 207640228
 * This class represents a score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private Rectangle rec;
    /**
     * Constructor.
     * @param scoreCounter the score counter
     * @param rec the rectangle shape which the score will be written on.
     */
    public ScoreIndicator(Counter scoreCounter, Rectangle rec) {
        this.scoreCounter = scoreCounter;
        this.rec = rec;
    }
    /**
     * This method draws score indicator on a given surface.
     * @param d the draw surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText((int) (this.rec.getUpperLeft().getX() + (this.rec.getWidth() / 2) - 30),
               (int) (this.rec.getUpperLeft().getY() + (this.rec.getHeight() / 2) + 5),
                "Score: " + (this.scoreCounter.getValue()), 15);
    }
    /**
     * This method notifies the score indicator that the has time passed.
     */
   public void timePassed() {
    }
    /**
     * This method adds the score indicator to the game.
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
