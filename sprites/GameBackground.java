package sprites;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import java.awt.Color;
/**
 * @author Shir Amit
 * ID: 207640228
 * This Class represents the gamebackground.
 */
public class GameBackground implements Sprite  {
    private Rectangle rec;
    private Color color;
    /**
     * Constructor.
     */
    public GameBackground() {
        this.rec = new Rectangle(new Point(20, 20), 760, 580);
        this.color = Color.LIGHT_GRAY;
    }
    @Override
    public void drawOn(DrawSurface d) {
        int topLeftX = (int) Math.round(this.rec.getUpperLeft().getX());
        int topLeftY = (int) Math.round(this.rec.getUpperLeft().getY());
        d.setColor(this.color);
        int width = (int) Math.round(this.rec.getWidth());
        int height = (int) Math.round(this.rec.getHeight());
        d.fillRectangle(topLeftX, topLeftY, width, height);
    }
    @Override
    public void timePassed() {

    }
}
