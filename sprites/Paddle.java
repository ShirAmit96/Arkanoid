package sprites;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;
import interfaces.Sprite;
import geometry.Rectangle;
import other.Velocity;
import gamemngr.GameLevel;
import java.awt.Color;

/**
 * @author Shir Amit
 * ID: 207640228
 * This class represents a paddle.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rec;
    private int frameWidth;
    private int frameHeight;
    private int frameBoundWidth;
    private Color color;
    private int  padWidth;
    private int  padHeight;
    private int speed;

    /**
     * Constructor.
     *
     * @param gui             the gui
     * @param frameWidth      the frame width
     * @param frameHeight     the frame height
     * @param frameBoundWidth the frame bound width
     * @param padWidth        the width of the paddle
     * @param padHeight       the pad height of the paddle
     * @param speed           the speed
     */
    public Paddle(GUI gui, int frameWidth, int frameHeight,
                  int frameBoundWidth, int padWidth, int padHeight, int speed) {
        this.keyboard = gui.getKeyboardSensor();
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameBoundWidth = frameBoundWidth;
        Point upperLeft = new Point((frameWidth - padWidth) / 2, (frameHeight - (frameBoundWidth * 2)));
        this.rec = new Rectangle(upperLeft, padWidth, padHeight);
        this.color = Color.PINK;
        this.padWidth = padWidth;
        this.padHeight = padHeight;
        this.speed = speed;
    }
    /**
     * This method moves the paddle to the left.
     */
    public void moveLeft() {
        double newX;
        if (this.rec.getUpperLeft().getX() > frameBoundWidth) {
            newX = this.rec.getUpperLeft().getX() - this.speed;
        } else {
            newX = frameBoundWidth;
        }
        Point upperLeft = new Point(newX, this.rec.getUpperLeft().getY());
        this.rec = new Rectangle(upperLeft, padWidth, padHeight);

    }
    /**
     * This method moves the paddle to the right.
     */
    public void moveRight() {
        double newX;
        if (this.rec.getUpperRight().getX() < (frameWidth - frameBoundWidth)) {
            newX = this.rec.getUpperLeft().getX() + this.speed;
        } else {
            newX = frameWidth - frameBoundWidth - padWidth;
        }
        Point upperLeft = new Point(newX, this.rec.getUpperLeft().getY());
        this.rec = new Rectangle(upperLeft, padWidth, padHeight);
    }
    /**
     * This method calls to move left / move a method respectively
     * in response to pressing the left or right key on the keyboard.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    /**
     * This method draws the paddle on a given surface.
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        int topLeftX = (int) Math.round(this.rec.getUpperLeft().getX());
        int topLeftY = (int) Math.round(this.rec.getUpperLeft().getY());
        d.setColor(color);
        int width = (int) Math.round(this.rec.getWidth());
        int height = (int) Math.round(this.rec.getHeight());
        d.fillRectangle(topLeftX, topLeftY, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(topLeftX, topLeftY, width, height);
    }
    /**
     * This method returns the rectangular shape of the paddle.
     * @return rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }
    /**
     * This method computes and returns a new velocity expected after the hit (based on
     *  the force the object inflicted on us).
     * @param hitter the hitting ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line lineCollisionPoint = new Line(collisionPoint, collisionPoint);
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();
        //if the hit is in on one of the corners of the paddle change dx and dy:
        if (collisionPoint.equals(this.rec.getUpperLeft()) || collisionPoint.equals(this.rec.getBottomLeft())
                || collisionPoint.equals(this.rec.getUpperRight())
                || collisionPoint.equals(this.rec.getBottomRight())) {
            newDx = (-1) * currentVelocity.getDx();
            newDy = (-1) * currentVelocity.getDy();
            Velocity newVelocity = new Velocity(newDx, newDy);
            return newVelocity;
        } else if (this.rec.getRightLine().isIntersecting(lineCollisionPoint)
                || this.rec.getLeftLine().isIntersecting(lineCollisionPoint)) {
            //if the collision point is in the left/right side of the paddle change dx:
            newDx *= (-1);
            Velocity newVelocity = new Velocity(newDx, newDy);
            return newVelocity;
        } else if (this.rec.getTopLine().isIntersecting(lineCollisionPoint)) {
            //If the collision point is on the top of the paddle:
            double xInter = this.rec.getTopLine().intersectionWith(lineCollisionPoint).getX();
            double section1 = this.rec.getUpperLeft().getX();
            double section2 = section1 + (padWidth / 5);
            double section3 = section2 + (padWidth / 5);
            double section4 = section3 + (padWidth / 5);
            double section5 = section4 + (padWidth / 5);
            double section6 = section5 + (padWidth / 5);
            newDy = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx())
                    + (currentVelocity.getDy() * currentVelocity.getDy()));
            //change the angle of the ball depending on the area of the paddle where the collision occurs:
            if (xInter >= section1 && xInter < section2) {
                newDx = 300;
                Velocity newVelocity = Velocity.fromAngleAndSpeed(newDx, newDy);
                return newVelocity;
            } else if (xInter >= section2 && xInter < section3) {
                newDx = 330;
                Velocity newVelocity = Velocity.fromAngleAndSpeed(newDx, newDy);
                return newVelocity;
            } else if (xInter >= section3 && xInter < section4) {
                newDy = currentVelocity.getDy() * (-1);
                Velocity newVelocity = new Velocity(newDx, newDy);
                return newVelocity;
            } else if (xInter >= section4 && xInter < section5) {
                newDx = 30;
                Velocity newVelocity = Velocity.fromAngleAndSpeed(newDx, newDy);
                return newVelocity;
            } else if (xInter >= section5 && xInter <= section6) {
                newDx = 60;
                Velocity newVelocity = Velocity.fromAngleAndSpeed(newDx, newDy);
                return newVelocity;
            }
        }
        Velocity newVelocity = new Velocity(newDx, newDy);
        return newVelocity;
    }

    /**
     * This method add the paddle the sprites and to the collidables of the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);

    }
    /**
     * This method returns the paddle's width.
     *
     * @return the width of the paddle.
     */
    public int getPadWidth() {
        return padWidth;
    }

    /**
     * This method returns the paddle's height.
     *
     * @return the height of the paddle.
     */
    public int getPadHeight() {
        return padHeight;
    }

    /**
     * This method returns the paddle's color.
     *
     * @return the color of the paddle.
     */
    public Color getPadColor() {
        return this.color;
    }
}

