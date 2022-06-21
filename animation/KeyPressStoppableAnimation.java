package animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;
/**
 * @author Shir Amit
 * Id: 207640228
 * This class represents the "KeyPressStoppableAnimation" animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean shouldClose;
    private boolean isAlreadyPressed;
    /**
     * @param sensor    the keyboard sensor.
     * @param key       the key that should stop the animation.
     * @param animation the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.shouldClose = false;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        //make sure the key wasn't pressed before starting the animation:
        if (!this.sensor.isPressed(this.key)) {
            isAlreadyPressed = false;
        }
        //stop the animation if:
        if (!isAlreadyPressed && this.sensor.isPressed(this.key)) {
         shouldClose = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.shouldClose;
    }
}