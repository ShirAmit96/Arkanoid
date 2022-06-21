package sprites;
import geometry.Point;
import interfaces.Sprite;
import other.Velocity;
import collections.GameEnvironment;
import geometry.Line;
import interfaces.Collidable;
import gamemngr.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Shir Amit
 * ID: 207640228
 * The class represents a ball.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private Paddle paddle;
    /**
     * Constructor.
     * @param center          the center point of the ball
     * @param r               the radius of the ball
     * @param color           the color of the ball
     * @param velocity        the velocity of the ball
     * @param gameEnvironment The game environment in which the ball is located
     * @param paddle          the paddle
     */
    public Ball(Point center, int r, java.awt.Color color, Velocity velocity,
                GameEnvironment gameEnvironment, Paddle paddle) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = velocity;
        this.gameEnvironment = gameEnvironment;
        this.paddle = paddle;
    }

    /**
     * Constructor.
     * @param center the center point of the ball
     * @param r      the radius of the ball.
     * @param color  the color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * This method returns the x value of the center point.
     * @return x value of the center point.
     */
    public int getX() {
        int x;
        x = (int) Math.round(center.getX());
        return x;
    }

    /**
     * This method returns the y value of the center point.
     * @return y value of the center point.
     */
    public int getY() {
        int y;
        y = (int) Math.round(center.getY());
        return y;
    }

    /**
     * This method returns the radius of the ball.
     * @return the radius of the ball.
     */
    public int getSize() {
        return r;
    }

    /**
     * This method returns the color of the ball.
     * @return color. color
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * This method draws the ball on the given DrawSurface.
     * @param surface the surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), getSize());
    }

    /**
     * This method sets new velocity to the ball.
     * @param v the new velocity of the ball.
     */
    public void setVelocity(Velocity v) {

        this.velocity = v;
    }

    /**
     * This method sets new velocity to the ball.
     *
     * @param dx -steps on x axis.
     * @param dy -steps on y axis.
     */
    public void setVelocity(double dx, double dy) {

        velocity = new Velocity(dx, dy);
    }

    /**
     * This method returns the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {

        return velocity;
    }

    /**
     * This method returns the center point of the ball.
     * @return the center point of the ball.
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     * This method sets the ball a new center.
     * @param x the x value of the new center.
     * @param y the x value of the new center.
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }
    /**
     * This method computes the ball trajectory.
     * @return the line starting at current location,
     * and ending where the velocity will take the ball if no collisions will occur.
     */
    public Line trajectory() {
        Point tEnd = new Point((this.center.getX() + velocity.getDx()), (this.center.getY() + velocity.getDy()));
        Line trajectory = new Line(this.center, tEnd);
        return trajectory;
    }
    /**
     * This method locates the ball in a new location accordingly to its speed.
     * If the ball is going to collide with a certain object,
     * its center point its velocity will be changed accordingly.
     */
    public void moveOneStep() {
        Line trajectory = trajectory();
        //Check if moving on trajectory will hit anything:
        if (this.gameEnvironment.getClosestCollision(trajectory) != null) {
            Point cPoint = this.gameEnvironment.getClosestCollision(trajectory).collisionPoint();
            //move the ball to "almost" the hit point, but just slightly before it:
            this.center = new Point(cPoint.getX() - this.velocity.getDx(), cPoint.getY() - this.velocity.getDy());
            //check if the hit object is the paddle:
            if (this.gameEnvironment.getClosestCollision(trajectory).collisionObject() == paddle) {
                //adjust the height of the ball so it won't get stuck under the paddle:
                this.center =
                        new Point(this.center.getX(), (this.paddle.getCollisionRectangle().getUpperLeft().getY() - r));
            }
            //notify the hit object and use hit method in order to calc new velocity:
            Velocity v =
                    this.gameEnvironment.getClosestCollision(trajectory).collisionObject().hit(this,
                            cPoint, getVelocity());
            setVelocity(v);
        } else {
            //else, move the ball according to its speed:
            this.center = this.getVelocity().applyToPoint(this.center);
        }

    }
    /**
     * This method releases the ball if it is stuck inside a rectangle.
     */
    public void releaseBall() {
        for (Collidable c : this.gameEnvironment.getCollidables()) {
            if (c.getCollisionRectangle().isContainingBall(this)) {
                if (this.getVelocity().getDy() < 0) {
                    this.center = new Point(this.getCenter().getX(),
                            this.getCenter().getY() + c.getCollisionRectangle().getHeight());
                } else {
                    this.center = new Point(this.getCenter().getX(),
                            this.getCenter().getY() - c.getCollisionRectangle().getHeight());
                }
                this.setVelocity(this.getVelocity().getDx(), this.getVelocity().getDy() * -1);
            }
        }
    }
    /**
     * This method adds the ball to the game.
     *
     * @param game - the game.
     */
    public  void addToGame(GameLevel game) {
        game.addSprite(this);
    }
    /**
     * This method removes the ball to the game.
     *
     * @param g - the game.
     */
    public  void removeFromGame(GameLevel g) {
       g.removeSprite(this);
    }
    /**
     * This method calls 'moveOneStep' and 'releaseBall' methods.
     */
    public void timePassed() {
        this.moveOneStep();
        this.releaseBall();

    }
}








