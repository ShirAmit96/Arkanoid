package sprites;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import other.Velocity;
import gamemngr.GameLevel;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
/**
 * @author Shir Amit
 * ID: 207640228
 * This Class represents a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private Color color;
    private int hits;
    private List<HitListener> hitListeners;
    /**
     * Constructor.
     * @param rec   - The rectangular structure of the block.
     * @param color - the color of the block.
     */
    public Block(Rectangle rec, Color color) {
        this.rec = rec;
        this.color = color;
        this.hits = 0;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * This method returns the rectangular shape of the block.
     * @return rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }
    /**
     * This method returns the number of times the block was hit.
     * @return the amount of hits.
     */
    public int getHits() {
        return this.hits;
    }
    /**
     * This method computes and returns a new velocity expected after the hit (based on
     *  the force the object inflicted on us).
     * The method also notifies the object and the ball that a collision has occurred.
     * @param hitter the hitting ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if tha ball is stuck inside a block, call notify hit method:
        if (this.getCollisionRectangle().isContainingBall(hitter)) {
            this.hits += 1;
            this.notifyHit(hitter);
        }
        //if the hit is in on one of the corners of the block change dx and dy:
        if (collisionPoint.equals(this.rec.getUpperLeft()) || collisionPoint.equals(this.rec.getBottomLeft())
                || collisionPoint.equals(this.rec.getUpperRight())
                || collisionPoint.equals(this.rec.getBottomRight())) {
            double newDx = (-1) * currentVelocity.getDx();
            double newDy = (-1) * currentVelocity.getDy();
            Velocity newVelocity = new Velocity(newDx, newDy);
            this.hits += 1;
            this.notifyHit(hitter);
            return newVelocity;
        } else {
            boolean isColliding = false;
            Line lineCollisionPoint = new Line(collisionPoint, collisionPoint);
            double newDx = currentVelocity.getDx();
            double newDy = currentVelocity.getDy();
            //if the hit is on the right or left line of the block change dx:
            if (this.rec.getRightLine().isIntersecting(lineCollisionPoint)
                    || this.rec.getLeftLine().isIntersecting(lineCollisionPoint)) {
                newDx *= (-1);
                isColliding = true;
            }
            //if the hit is on the top or bottom line of the block change dy:
            if (this.rec.getTopLine().isIntersecting(lineCollisionPoint)
                    || this.rec.getBottomLine().isIntersecting(lineCollisionPoint)) {
                newDy *= (-1);
                isColliding = true;
            }
            if (isColliding) {
                this.hits += 1;
                this.notifyHit(hitter);
            }
            Velocity newVelocity = new Velocity(newDx, newDy);
            return newVelocity;

        }
    }
    /**
     * This method draws the block on a given surface.
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        int topLeftX = (int) Math.round(this.rec.getUpperLeft().getX());
        int topLeftY = (int) Math.round(this.rec.getUpperLeft().getY());
        surface.setColor(this.color);
        int width = (int) Math.round(this.rec.getWidth());
        int height = (int) Math.round(this.rec.getHeight());
        surface.fillRectangle(topLeftX, topLeftY, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(topLeftX, topLeftY, width, height);
    }
    /**
     * This method notifies the block that time has passed.
     */
    public void timePassed() {
    }
    /**
     * This method add the block to the sprites and to the collidables of the game.
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
    /**
     * This method removes the block from the sprites and from the collidables of the game.
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * This method notifies all the listeners of the block about a hit event.
     * @param hitter the hitting ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * This method adds a given hit listener to the listeners list of the block.
     * @param hl a listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * This method removes a given hit listener from the listeners list of the block.
     * @param hl a listener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

