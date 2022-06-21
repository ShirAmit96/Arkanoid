package sprites;
import biuoop.DrawSurface;
import gamemngr.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import java.awt.Color;
/**
 * @author Shir Amit
 * ID: 207640228
 * This class represents the top surface of the game screen.
 */
public class TopSurface implements Sprite {
    private Rectangle rec;
    private Color color;
    /**
     * Constructor.
     */
    public TopSurface() {
        this.rec = new Rectangle(new Point(0, 0), 800, 20);
        this.color = Color.orange;
    }
   @Override
    public void drawOn(DrawSurface d) {
        int topLeftX = (int) Math.round(this.rec.getUpperLeft().getX());
        int topLeftY = (int) Math.round(this.rec.getUpperLeft().getY());
        d.setColor(this.color);
        int width = (int) Math.round(this.rec.getWidth());
        int height = (int) Math.round(this.rec.getHeight());
        d.fillRectangle(topLeftX, topLeftY, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(topLeftX, topLeftY, width, height);
    }
    @Override
    public void timePassed() {
    }

    /**
     * This method adds the Top surface to the game.
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * This method returns the rectangular shape of the top surface.
     * @return the rectangle.
     */
    public Rectangle getRec() {
        return this.rec;
    }


}
