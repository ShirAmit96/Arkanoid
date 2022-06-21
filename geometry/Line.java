package geometry;

/**
 * @author Shir Amit
 * Id: 207640228
 * This class represents a line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructor.
     * @param start the beginning point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * constructor.
     *
     * @param x1 the x value of start point.
     * @param y1 the y value of start point.
     * @param x2 the x value of end point.
     * @param y2 the y value of end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * This method calculates the distance between start and end point on x axis.
     *
     * @return dx. double
     */
    public double dx() {
        double dx;
        dx = start.getX() - end.getX();
        return dx;
    }

    /**
     * This method calculates the distance between start and end point on y axis.
     *
     * @return dy. double
     */
    public double dy() {
        double dy;
        dy = start.getY() - end.getY();
        return dy;
    }

    /**
     * This method calculates and return the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        double length;
        length = Math.sqrt((dx() * dx()) + (dy() * dy()));
        return length;
    }

    /**
     * This method returns the middle point of the line.
     *
     * @return middle point.
     */
    public Point middle() {
        double midX;
        double midY;
        Point middle;
        midX = (start.getX() + end.getX()) / 2;
        midY = (start.getY() + end.getY()) / 2;
        middle = new Point(midX, midY);
        return middle;
    }

    /**
     * This method returns the start point of the line.
     *
     * @return start point.
     */
    public Point start() {
        return start;
    }

    /**
     * This method returns the end point of the line.
     *
     * @return end point.
     */
    public Point end() {
        return end;
    }

    /**
     * This method calculates the incline of the line and returns it.
     * If the line has no incline (parallel to y axis) return 1, else return the incline.
     *
     * @return the incline of the line.
     */
    public double incline() {
        double incline;
        if (dx() == 0) {
            incline = 1;
        } else {
            incline = (dy() / dx());
        }
        return incline;
    }

    /**
     * This method calculates the n value of the line equation.
     *
     * @return n. double
     */
    public double n() {
        double n;
        n = start.getY() - (incline() * start.getX());
        return n;
    }

    /**
     * This method returns the max x value between start and end point.
     *
     * @return max x value.
     */
    public double xMax() {
        double xMax;
        xMax = Math.max(start.getX(), end.getX());
        return xMax;
    }

    /**
     * This method returns the max y value between start and end point.
     *
     * @return max y value.
     */
    public double yMax() {
        double yMax;
        yMax = Math.max(start.getY(), end.getY());
        return yMax;
    }


    /**
     * This method returns the min x value between start and end point.
     *
     * @return min x value.
     */
    public double xMin() {
        double xMin;
        xMin = Math.min(start.getX(), end.getX());
        return xMin;
    }

    /**
     * This method returns the min y value between start and end point.
     *
     * @return min y value.
     */
    public double yMin() {
        double yMin;
        yMin = Math.min(start.getY(), end.getY());
        return yMin;

    }
    /**
     * This method checks if two doubles are almost equal.
     * @param d1 is a double
     * @param d2 is another double
     * @return true if the doubles are almost equal. otherwise,return false.
     */
    public boolean isEqualD(double d1, double d2) {
        // this checks if d1-d2 is closer to 0 than epsilon, if true, then d1 probably equals d2
        double epsilon = Math.pow(10, -8);
        return ((d1 - d2) < epsilon && (d1 - d2) > -epsilon);
    }

    /**
     * This method checks if this line and another line are both points.
     *
     * @param other is another line.
     * @return true if both lines are points,else return false.
     */
    public boolean isTwoPoints(Line other) {
        if (this.length() == 0 && other.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if this line is a point and the other line is a line.
     *
     * @param other is another line.
     * @return true if this line is a point and the other line is a line,else return false.
     */
    public boolean isPointAndLine(Line other) {
        if (this.length() == 0 && other.length() != 0) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if this line is a line and the other line is a point.
     *
     * @param other is another line.
     * @return true if this line is a line  and the other line is a point,else return false.
     */
    public boolean isLineAndPoint(Line other) {
        if (this.length() != 0 && other.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if this line and another line are parallel to y axis.
     *
     * @param other is another line.
     * @return true if both lines are parallel to y axis,else return false.
     */
    public boolean isBothDxEquals0(Line other) {
        if (this.dx() == 0 && other.dx() == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if this line and another line have the same line equation.
     *
     * @param other is another line.
     * @return true if the lines have the same line equation, else return false.
     */
    public boolean isOnSameLine(Line other) {
        return (isEqualD(this.incline(), other.incline()) && isEqualD(this.n(), other.n()));
    }
    /**
     * This method checks if this line is parallel to y axis and the other line is not.
     *
     * @param other is another line.
     * @return true if this line is parallel to y axis and the other line is not,else- return false.
     */
    public boolean isThisDxEqual0(Line other) {
        if (this.dx() == 0 && other.dx() != 0) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if this line is not parallel to y axis and the other line indeed parallel to y axis.
     *
     * @param other is another line.
     * @return true if this line isn't parallel to y axis and the other line indeed parallel to y axis
     * else- return false.
     */
    public boolean isOtherDxEqual0(Line other) {
        if (this.dx() != 0 && other.dx() == 0) {
            return true;
        }
        return false;
    }


    /**
     * This method finds the closest intersection point of this line and a rectangle to the start of line point.
     * @param rect a rectangle
     * @return the closest intersection point to the start of the line.
     * If this line does not intersect with the rectangle, return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this) == null) {
            return null;
        } else {
            Point closestPoint = rect.intersectionPoints(this).get(0);
            for (int i = 1; i < rect.intersectionPoints(this).size(); ++i) {
                if (this.start.distance(rect.intersectionPoints(this).get(i)) <  this.start.distance(closestPoint)) {
                    closestPoint = rect.intersectionPoints(this).get(i);
                }
            }
            return closestPoint;
        }
    }
    /**
     * This method checks if this line is intersecting with another line.
     *
     * @param other is another line.
     * @return true if the lines are intersecting, else- return false.
     */
    public boolean isIntersecting(Line other) {
        double inclinesSub;
        double nSub;
        double xInter;
        double yInter;
        //two points:
        if (isTwoPoints(other)) {
            if (this.start.equals(other.start)) {
                return true;
            }
            return false;
            //point and line- option 1:
        } else if (isPointAndLine(other)) {
            // check if the other point is not parallel to y axis:
            if (other.dx() != 0) {
                //check if the point is in the range of the line:
                if ((this.start.getX() <= other.xMax()) && (this.start.getX() >= other.xMin())
                        && (this.start.getY() <= other.yMax())
                        && (this.start.getY() >= other.yMin())) {
                    //check if the point is located on the line:
                    if (isEqualD(other.n(), this.start.getY() - (other.incline() * this.start.getX()))) {
                        return true;
                    }
                    return false;
                } else {
                    return false;
                }

            } else {
                //the line is parallel to Y axis,check if the point is located on the line:
                if (isEqualD(this.start.getX(), other.start.getX()) && (this.start.getY() <= other.yMax())
                        && (this.start.getY() >= other.yMin())) {
                    return true;
                }
                return false;
            }
            //point and line option 2:
        } else if (isLineAndPoint(other)) {
            //check if "this"  point is not parallel to y axis:
            if (this.dx() != 0) {
                //check if the point is in the range of the line:
                if ((other.start.getX() <= this.xMax()) && (other.start.getX() >= this.xMin())
                        && (other.start.getY() <= this.yMax()) && (other.start.getY() >= this.yMin())) {
                    if (isEqualD(this.n(), (other.start.getY() - (this.incline() * other.start.getX())))) {
                        return true;
                    }
                    return false;
                } else {
                    return false;
                }
            } else {
                //the line is parallel to Y axis,check if the point is located on the line:
                if ((isEqualD(this.start.getX(), other.start.getX())) && (other.start.getY() <= this.yMax())
                        && (other.start.getY() >= this.yMin())) {
                    return true;
                }
                return false;
            }
            //two lines:
        } else {
            //both lines parallel to Y axis:
            if (isBothDxEquals0(other)) {
                //if x value is different-->the lines are parallel to each other-return false:
                if (!isEqualD(this.start.getX(), other.start.getX())) {
                    return false;
                } else {
                    //the lines are on the same line:
                    if (isEqualD(Math.max(this.yMin(), other.yMin()), Math.min(this.yMax(), other.yMax()))
                            && isEqualD(Math.max(this.xMin(), other.xMin()), Math.min(this.xMax(), other.xMax()))) {
                        return true;
                    }
                    return false;
                }
            } else if (isThisDxEqual0(other)) {
                //one line is parallel to Y axis- option 1:
                yInter = (other.incline() * this.start.getX()) + other.n();
                xInter = this.start.getX();
                if (yInter <= other.yMax() && yInter >= other.yMin()
                        && yInter <= this.yMax() && yInter >= this.yMin() && xInter <= other.xMax()
                        && xInter >= other.xMin() && xInter <= this.xMax() && xInter >= this.xMin()) {
                    return true;
                }
                return false;
            } else if (isOtherDxEqual0(other)) {
                //one line is parallel to Y axis- option 2:
                yInter = (this.incline() * other.start.getX()) + this.n();
                xInter = other.start.getX();
                if (yInter <= other.yMax() && yInter >= other.yMin()
                        && yInter <= this.yMax() && yInter >= this.yMin() && xInter <= other.xMax()
                        && xInter >= other.xMin() && xInter <= this.xMax() && xInter >= this.xMin()) {
                    return true;
                }
                return false;
            } else {
                //none of the lines is parallel Y axis:
                //if the lines have the same incline and same n:
                if (isOnSameLine(other)) {
                    //if the lines have only one shared point-return true,else- return false:
                    if (isEqualD(Math.max(this.yMin(), other.yMin()), Math.min(this.yMax(), other.yMax()))
                            && isEqualD(Math.max(this.xMin(), other.xMin()), Math.min(this.xMax(), other.xMax()))) {
                        return true;
                    }
                    return false;
                } else if (isEqualD(this.incline(), other.incline()) && !isEqualD(this.n(), other.n())) {
                    //the lines have the same incline and different n-the lines are parallel:
                    return false;
                } else if (!isEqualD(this.incline(), other.incline()) && isEqualD(this.n(), other.n())) {
                    //the lines have same n and different incline:
                    //check if intersection point is in range:
                    if (0 <= Math.min(this.xMax(), other.xMax())
                            && 0 >= Math.max(this.xMin(), other.xMin())
                            && this.n() <= Math.min(this.yMax(), other.yMax())
                            && this.n() >= Math.max(this.yMin(), other.yMin())) {
                        return true;
                    }
                    return false;
                } else {
                    //the lines have different incline and different n:
                    //subtract the "other" incline from "this" incline
                    inclinesSub = this.incline() - other.incline();
                    //subtract the "this" 'n' from "other" 'n'
                    nSub = other.n() - this.n();
                    //divide 'n' by 'm'
                    xInter = nSub / inclinesSub;
                    yInter = (xInter * this.incline()) + this.n();
                    if (xInter <= Math.min(this.xMax(), other.xMax())
                            && xInter >= Math.max(this.xMin(), other.xMin())
                            && yInter <= Math.min(this.yMax(), other.yMax())
                            && yInter >= Math.max(this.yMin(), other.yMin())) {

                        return true;
                    }
                    return false;
                }
            }
        }
    }
    /**
     * This method returns the intersection point if the lines intersect,and null otherwise.
     * @param other is another line.
     * @return intersection point if exist,else- return null.
     */
    public Point intersectionWith(Line other) {
        double inclinesSub;
        double nSub;
        double xInter;
        double yInter;
        if (!isIntersecting(other)) {
            return null;
        } else {

            if (isTwoPoints(other) || isPointAndLine(other)) {
                xInter = this.start.getX();
                yInter = this.start.getY();

            } else if (isLineAndPoint(other)) {
                xInter = other.start.getX();
                yInter = other.start.getY();
            } else if (isBothDxEquals0(other)) {
                xInter = this.start.getX();
                yInter = Math.min(this.yMax(), other.yMax());
            } else if (isOnSameLine(other)) {
                xInter = Math.min(this.xMax(), other.xMax());
                yInter = Math.min(this.yMax(), other.yMax());
            } else if (isThisDxEqual0(other)) {
                xInter = this.start.getX();
                yInter = (other.incline() * xInter) + other.n();
            } else if (isOtherDxEqual0(other)) {
                xInter = other.start.getX();
                yInter = (this.incline() * xInter) + this.n();
            } else {
                inclinesSub = this.incline() - other.incline();
                //subtract the "this" 'n' from "other" 'n'
                nSub = other.n() - this.n();
                //divide 'n' by 'm'
                xInter = nSub / inclinesSub;
                yInter = (xInter * this.incline()) + this.n();
            }
            Point interPoint = new Point(xInter, yInter);
            return interPoint;
        }
    }
    /**
     * This method checks if this line is equal to another line.
     *
     * @param other is another line.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return false;
    }

}






