package other;

import geometry.Point;

/**
 * @author Shir Amit
 * ID:207640228
 * This class represents velocity.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */

public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     * @param dx the number of steps on x axis.
     * @param dy the the number of steps on y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method converts angle and speed to dx and dy.
     * @param angle the angle of the movement vector.
     * @param speed the speed of movement.
     * @return the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double convertToRad = Math.toRadians(angle - 90.0);
        double dx = Math.cos(convertToRad) * speed;
        double dy = Math.sin(convertToRad) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * This method takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     * @param p the current location point.
     * @return a new point with position (x+dx, y+dy).
     */

    public Point applyToPoint(Point p) {
        p = new Point(p.getX() + dx, p.getY() + dy);
        return p;
    }

    /**
     * This method returns the dx value of the velocity class.
     * @return the dx value.
     */
    public double getDx() {
        return dx;
    }

    /**
     * This method returns the dy value of the velocity class.
     * @return the dy value.
     */
    public double getDy() {
        return dy;
    }
}
