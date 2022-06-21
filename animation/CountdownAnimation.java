package animation;
import collections.SpriteCollection;
import biuoop.DrawSurface;
import interfaces.Animation;
import java.awt.Color;
/**
 * @author Shir Amit
 * Id: 207640228
 * This class represents the coundown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int currentCount;
    private float millsSec;
    private SpriteCollection gameScreen;
    private  long startingTime;
    private boolean running;
    /**
     * Constructor.
     * @param numOfSeconds the num of seconds
     * @param countFrom    the number which we count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.startingTime = System.currentTimeMillis();
        this.numOfSeconds = numOfSeconds;
        this.millsSec = (float) (1000 * this.numOfSeconds);
        this.countFrom = countFrom;
        this.currentCount = this.countFrom;
        this.gameScreen = gameScreen;
        this.running = true;
    }
    /**
     * This method cacls if we should update the count.
     * @return true or false.
     */
    public boolean shouldUpdateCount() {
       if ((System.currentTimeMillis() - this.startingTime) > (this.millsSec / this.countFrom)) {
           return true;
       }
       return false;
    }
    /**
     * This method updates the count.
     */
    public void updateCount() {
        this.startingTime = System.currentTimeMillis();
        --this.currentCount;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        //The CountdownAnimation will display the given gameScreen:
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.BLACK);
        String count = Integer.toString(this.currentCount);
        d.drawText((d.getWidth() / 2) - 30, d.getHeight() / 2, count, 80);
        if (shouldUpdateCount()) {
            updateCount();
        }
        //Stop the animation if count has reached to 0:
      if (this.currentCount == 0) {
          this.running = false;
      }
    }
    @Override
    public boolean shouldStop() {
        return (!this.running);
    }
}
