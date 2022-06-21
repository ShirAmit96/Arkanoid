package animation;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Animation;
import other.Counter;
import java.awt.Color;
/**
 * @author Shir Amit
 * Id: 207640228
 * This class represents the "Game Over" animation.
 */
public class GameOver implements Animation {
    private Counter score;
    private Rectangle rec;
    /**
     * Constructor.
     * @param score the score
     */
    public GameOver(Counter score) {
        this.score = score;
        this.rec = new Rectangle(new Point(0, 0), 800, 600);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
            int topLeftX = (int) Math.round(this.rec.getUpperLeft().getX());
            int topLeftY = (int) Math.round(this.rec.getUpperLeft().getY());
            d.setColor(Color.BLACK);
            int width = (int) Math.round(this.rec.getWidth());
            int height = (int) Math.round(this.rec.getHeight());
            d.fillRectangle(topLeftX, topLeftY, width, height);
            d.setColor(Color.GREEN);
            d.drawText(200, 200, "Game Over.", 50);
            String finalScore = Integer.toString(this.score.getValue());
            d.setColor(Color.GREEN);
            d.drawText(200, 300, "Your score is : " + finalScore, 50);
    }
    @Override
    public boolean shouldStop() {
       return false;
    }
}
