package interfaces;
import biuoop.DrawSurface;
/**
 * @author Shir Amit
 * Id: 207640228
 * This interface represents animation.
 */
public interface Animation {
    /**
     * This method runs one frame.
     * @param d the drawSurface.
     */
    void doOneFrame(DrawSurface d);
    /**
     * This method indicates whether the animation should stop or not.
     * @return true or false.
     */
    boolean shouldStop();
}