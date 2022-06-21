package animation;
import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Animation;
import other.Counter;
import java.awt.Color;
/**
 * @author Shir Amit
 * Id: 207640228
 * This class represents the "You Win" animation.
 */
public class YouWin implements Animation {
    private Counter score;
    private geometry.Rectangle rec;
    private boolean running;
    /**
     * Constructor.
     * @param score the score
     */
    public YouWin(Counter score) {
        this.score = score;
        this.rec = new geometry.Rectangle(new Point(0, 0), 800, 600);
        this.running = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int topLeftX = (int) Math.round(this.rec.getUpperLeft().getX());
        int topLeftY = (int) Math.round(this.rec.getUpperLeft().getY());
        d.setColor(Color.PINK);
        int width = (int) Math.round(this.rec.getWidth());
        int height = (int) Math.round(this.rec.getHeight());
        d.fillRectangle(topLeftX, topLeftY, width, height);
        d.setColor(Color.BLACK);
        d.drawText(200, 200, "You win!", 50);
        String finalScore = Integer.toString(this.score.getValue());
        d.setColor(Color.BLACK);
        d.drawText(200, 300,  "Your score is : " + finalScore, 50);

    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
