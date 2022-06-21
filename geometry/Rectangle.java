package geometry;
import sprites.Ball;
import java.util.List;
import java.util.ArrayList;
/**
 * @author Shir Amit
 * ID: 207640228
 * This class represents rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    static final int NUM_OF_LINES = 4;

    /**
     * Constructor.
     *
     * @param upperLeft the upper corner of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

    }

    /**
     * This method computes and returns the top right point of the rectangle.
     *
     * @return the top right point of the rectangle.
     */
    public Point getUpperRight() {
        double x = upperLeft.getX() + width;
        double y = upperLeft.getY();
        Point topRight = new Point(x, y);
        return topRight;
    }

    /**
     * This method computes and returns the bottom left point of the rectangle.
     *
     * @return the bottom left point of the rectangle.
     */
    public Point getBottomLeft() {
        double x = upperLeft.getX();
        double y = upperLeft.getY() + height;
        Point bottomLeft = new Point(x, y);
        return bottomLeft;
    }

    /**
     * This method computes and returns the bottom right point of the rectangle.
     *
     * @return the bottom right point of the rectangle.
     */
    public Point getBottomRight() {
        double x = getUpperRight().getX();
        double y = getBottomLeft().getY();
        Point bottomRight = new Point(x, y);
        return bottomRight;
    }

    /**
     * This method creates and returns the left line of the rectangle.
     *
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        Line leftLine = new Line(upperLeft, getBottomLeft());
        return leftLine;
    }

    /**
     * This method creates and returns the right line of the rectangle.
     *
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        Line rightLine = new Line(getUpperRight(), getBottomRight());
        return rightLine;
    }

    /**
     * This method creates and returns the top line of the rectangle.
     *
     * @return the top line of the rectangle.
     */
    public Line getTopLine() {
        Line topLine = new Line(upperLeft, getUpperRight());
        return topLine;
    }

    /**
     * This method creates and returns the bottom line of the rectangle.
     *
     * @return the bottom line of the rectangle.
     */
    public Line getBottomLine() {
        Line bottomLine = new Line(getBottomLeft(), getBottomRight());
        return bottomLine;
    }
    /**
     * This method returns the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * This method returns the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * This method returns the upper-left corner of the rectangle.
     *
     * @return the upper left point.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * This method creates and returns an array of the rectangle lines.
     *
     * @return array of the rectangle lines.
     */
    public Line[] rectangleLinesArray() {
        Line[] linesArray = new Line[NUM_OF_LINES];
        linesArray[0] = getTopLine();
        linesArray[1] = getRightLine();
        linesArray[2] = getBottomLine();
        linesArray[3] = getLeftLine();
        return linesArray;
    }

    /**
     * This method returns a (possibly empty) List of intersection points of
     * a specific line and each of the rectangle sides.
     * @param line -The line with which we want to test an intersection with the rectangle.
     * @return the intersection points list.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> listOfPoints = new ArrayList<Point>();
        //check intersection of the line with each of the rectangle sides:
        for (int i = 0; i < NUM_OF_LINES; ++i) {
            if (rectangleLinesArray()[i].isIntersecting(line)) {
                if (rectangleLinesArray()[i].intersectionWith(line) != null) {
                    listOfPoints.add(rectangleLinesArray()[i].intersectionWith(line));
                }

            }
        }
        if (listOfPoints.isEmpty()) {
            return null;
        } else {
            return listOfPoints;
        }
    }
    /**
     * This method checks if the ball is found inside the rectangle.
     * @param ball the ball
     * @return the boolean
     */
    public boolean isContainingBall(Ball ball) {
        return (ball.getCenter().getX() >= this.getUpperLeft().getX()
                && ball.getCenter().getX() <= this.getBottomRight().getX()
                && ball.getCenter().getY() >= this.getUpperLeft().getY()
                && ball.getCenter().getY() <= this.getBottomRight().getY());
    }
}
