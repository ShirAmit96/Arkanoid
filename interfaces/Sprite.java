package interfaces;
import biuoop.DrawSurface;
/**
 * @author Shir Amit
 * ID:207640228
 * This interface represents all the drawables objects.
 */
public interface Sprite {
    /**
     * This method draws  the sprite to the screen.
     * @param d the draw surface.
     */
    void drawOn(DrawSurface d);
    /**
     * This method notifies the sprite that the has time passed.
     */
    void timePassed();
}
