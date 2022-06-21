package collections;
import biuoop.DrawSurface;
import interfaces.Sprite;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Shir Amit
 * ID:207640228
 * This class represents a sprite's collection.
 */
public class SpriteCollection {
    private List<Sprite> spritesList;
    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.spritesList = new ArrayList<Sprite>();
    }

    /**
     * This method adds a given sprite to the sprite's collection.
     *
     * @param s a sprite
     */
    public void addSprite(Sprite s) {
        this.spritesList.add(s);
    }

    /**
     * This method removes a given sprite from  the sprite's collection.
     *
     * @param s a sprite
     */
    public void removeSprite(Sprite s) {
        this.spritesList.remove(s);

    }
    /**
     * This method notifies all sprites that time passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copySpritesList = new ArrayList<>(this.spritesList);
        for (Sprite s:copySpritesList) {
            s.timePassed();
        }
    }

    /**
     * This method calls drawOn(d) method of each sprite.
     * @param d the draw surface,
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s:spritesList) {
            s.drawOn(d);
        }
    }
}
