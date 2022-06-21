package collections;
import interfaces.Collidable;
import other.CollisionInfo;
import geometry.Point;
import geometry.Line;
import java.util.List;
import java.util.ArrayList;
/**
 * @author Shir Amit
 * ID:207640228
 * This class represents the game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }
    /**
     * This method returns the collidables list.
     * @return the collidables list
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
    /**
     * This method adds the given collidable to the environment.
     * @param c a collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * This method remove collidable from the collidables list.
     * @param c - a collodable object.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * This method returns information about the closest collision of the ball with a collidable object.
     * @param trajectory the movement vector of the ball.
     * @return information about the closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> cInfoList = new ArrayList<CollisionInfo>();
        for (int i = 0; i < collidables.size(); ++i) {
            Point cPoint = trajectory.closestIntersectionToStartOfLine(this.collidables.get(i).getCollisionRectangle());
            //if a potential collision has been found add the relevant object and the collision point to the list:
            if (cPoint != null) {
                Collidable cObject = this.collidables.get(i);
                CollisionInfo cInfo = new CollisionInfo(cPoint, cObject);
                cInfoList.add(cInfo);
            }
        }
        // If the ball will not collide with any of the collidables return null:
        if (cInfoList.isEmpty()) {
            return null;
        } else {
            //check which of the collision points is the closest potential collision:
            Point closestP = cInfoList.get(0).collisionPoint();
            Collidable closestO = cInfoList.get(0).collisionObject();
            CollisionInfo closestCInfo = new CollisionInfo(closestP, closestO);
            for (int i = 1; i < cInfoList.size(); ++i) {
                if (trajectory.start().distance(cInfoList.get(i).collisionPoint())
                        < (trajectory.start().distance(closestP))) {
                    closestP = cInfoList.get(i).collisionPoint();
                    closestO = cInfoList.get(i).collisionObject();
                    closestCInfo = new CollisionInfo(closestP, closestO);
                }
            }
            return closestCInfo;
        }


    }
}
