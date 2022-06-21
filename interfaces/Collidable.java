package interfaces;
import sprites.Ball;
import geometry.Rectangle;
import geometry.Point;
import other.Velocity;
/**
 * @author Shir Amit
 * ID: 207640228
 * This interface represents a collidable object.
 */
public interface Collidable {
    /**
     * This method returns the "collision shape" of the object.
     * @return "collision shape"
     */
    Rectangle getCollisionRectangle();

    /**
     * This method computes and  returns a new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param hitter the hitting ball.
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
