package other;
import geometry.Point;
import interfaces.Collidable;

/**
 * @author Shir Amit
 * ID:207640228
 * This class represents the collision information in which a certain object involved.
 */
public class CollisionInfo {
    private Point cPoint;
    private Collidable cObject;

    /**
     * Constructor.
     * @param cPoint  the point at which the collision occurs.
     * @param cObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point cPoint, Collidable cObject) {
        this.cPoint = cPoint;
        this.cObject = cObject;
    }
    /**
     * This method returns the point at which the collision occurs.
     * @return the collision point
     */
    public Point collisionPoint() {
        return cPoint;
    }
    /**
     * This method returns the collidable object involved in the collision.
     * @return the object
     */
    public Collidable collisionObject() {
        return cObject;
    }
}
