package animation;
import biuoop.DrawSurface;
import interfaces.Animation;
/**
 * @author Shir Amit
 * Id: 207640228
 * This class represents the "KeyPressStoppableAnimation" animation.
 */
public class PauseScreen implements Animation {
   /* public PauseScreen() {

    }*/
@Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
    return false; }
}
