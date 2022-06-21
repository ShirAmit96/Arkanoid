package geometry;

/**
 * @author Shir Amit
 * id:207640228
 * This Class represents a point.
 */
public class Point {
    private double x;
    private double y;
    /**
     * constructor.
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method calculates the distance between this point and another point.
     * @param other the other point.
     * @return  the distance of this point to the other point
     */

    public double distance(Point other) {
        double distance;
        distance = Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
        return distance;

    }
    /**
     * This method returns true if this point equals to another point and false otherwise.
     * @param other the other point.
     * @return return true is the points are equal, false otherwise.
     */

    public boolean equals(Point other) {
        // this checks if x-y is closer to 0 than epsilon, if true, then x probably equals y
        double epsilon = Math.pow(10, -8);
        return ((this.getX() - other.getX()) < epsilon && (this.getY() - other.getY()) < epsilon
                && (this.getX() - other.getX()) > -epsilon && (this.getY() - other.getY()) > -epsilon);
    }

    /**
     * This method return the x value of the point.
     * @return the x value of the point.
     */
    public double getX() {
        return x;
    }

    /**
     *  This method return the x value of the point.
     * @return the x value of the point.
     */
    public double getY() {
        return y;
    }
}
