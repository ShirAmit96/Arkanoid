package sprites;
import biuoop.DrawSurface;
import gamemngr.GameLevel;
import geometry.Rectangle;
import interfaces.Sprite;;
import java.awt.Color;
/**
 * @author Shir Amit
 * ID: 207640228
 * This Class represents the name indicator.
 */
public class NameIndicator implements Sprite {
    private String levelName;
    private Color color;
    private Rectangle rec;
    /**
     * Constructor.
     * @param name the name of the level.
     * @param rec  the rectangle shape which the name will be written on.
     */
    public NameIndicator(String name, Rectangle rec) {
     this.levelName = name;
     this.color = Color.BLACK;
     this.rec = rec;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawText((int) (this.rec.getUpperLeft().getX() + (this.rec.getWidth() / 2) + 100),
                (int) (this.rec.getUpperLeft().getY() + (this.rec.getHeight() / 2) + 5),
                "Level Name: " + this.levelName, 15);
    }
    @Override
    public void timePassed() {
    }
    /**
     * This method adds the name indicator to the game.
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
