package Geometry;
// 324205764 Bar Nakdimon

import java.util.List;

/**
 * The geometry.Line class represents a line segment in 2D space defined by two points.
 */
public class Line {
    // EPSILON for equal threshold check
    static final double EPSILON = 0.001;
    private final Point start;
    private final Point end;
    /**
     * Constructs a geometry.Line with the given start and end points.
     *
     * @param start the starting point of the line
     * @param end the ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Constructs a geometry.Line with the given coordinates for the start and end points.
     *
     * @param x1 the x-coordinate of the starting point
     * @param y1 the y-coordinate of the starting point
     * @param x2 the x-coordinate of the ending point
     * @param y2 the y-coordinate of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        this.start = p1;
        this.end = p2;
    }
    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }
    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        return (new Point(((this.start.getX() + this.end.getX()) / 2), ((this.start.getY() + this.end.getY()) / 2)));
    }
    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }
    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }
    /**
     * Checks if the absolute difference between two doubles is within a threshold value.
     *
     * @param first  the first double value.
     * @param second the second double value.
     * @return true if the absolute difference between first and second is less than epsilon, false otherwise.
     */
    private boolean thresholdEqual(double first, double second) {
        return Math.abs(first - second) < EPSILON;
    }
    /**
     * @return string of the line points to print
     */
    public String print() {
        return "geometry.Line: " + this.start.print() + " - " + this.end.print();
    }
    /**
     * Determines the intersection point of this line and another line if one of them is vertical.
     *
     * @param other the other line
     * @return the intersection point if it exists, null otherwise
     */
    private Point verticalIntersect(Line other) {
        // Check if either of the lines is vertical
        boolean vertical1 = thresholdEqual(this.start.getX(), this.end.getX());
        boolean vertical2 = thresholdEqual(other.start.getX(), other.end.getX());
        // If at least one line is vertical
        if (vertical1 || vertical2) {
            // If this line is vertical and the other is not
            if (vertical1 && !vertical2) {
                // Calculate intersection point by y = mx + b
                double slope2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
                double b2 = other.start.getY() - slope2 * other.start.getX();
                double yIntersect = slope2 * this.start.getX() + b2;
                // Create the intersection point
                Point p = new Point(this.start.getX(), yIntersect);
                // Check if the intersection point lies on the other line
                if (other.isPointInLine(p) && this.isPointInLine(p)) {
                    return p;
                } else {
                    return null;
                }
                // If the other line is vertical and this line is not
            } else if (!vertical1) {
                // Calculate intersection point by y = mx + b
                double slope1 = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
                double b2 = this.start.getY() - slope1 * this.start.getX();
                double yIntersect = slope1 * other.start.getX() + b2;
                // Create the intersection point
                Point p = new Point(other.start.getX(), yIntersect);
                // Check if the intersection point lies on this line
                if (this.isPointInLine(p) && other.isPointInLine(p)) {
                    return p;
                } else {
                    return null;
                }
            } else { // Both lines are vertical - checks on other method
                return null;
            }
        }
        // Neither line is vertical - checks on other method
        return null;
    }
    /**
     * Determines the intersection point of two vertical lines.
     *
     * @param other the other line
     * @return the intersection point if it exists, null otherwise
     */
    private Point parallelIntersect(Line other) {
        // Check if the points lies on the other line
        boolean check1 = other.isPointInLine(this.start);
        boolean check2 = other.isPointInLine(this.end);
        boolean check3 = this.isPointInLine(other.start);
        boolean check4 = this.isPointInLine(other.end);
        // If both start points are the same and neither end point lies on the other line
        if (this.start.equals(other.start) && !check2 && !check4) {
            // Return the start point - intersection
            return this.start;
            // If this end point equals other start point and neither other start point lies on this line
        } else if (this.end.equals(other.start) && !check1 && !check4) {
            // Return the end point - intersection
            return this.end;
            // If this start point equals other end point and neither other end point lies on this line
        } else if (this.start.equals(other.end) && !check2 && !check3) {
            // Return the start point - intersection
            return this.start;
            // If both end points are the same and neither start point lies on the other line
        } else if (this.end.equals(other.end) && !check1 && !check3) {
            // Return the end point - intersection
            return this.end;
        } else { // If none of the above conditions are met - the lines are overlapping
            return null;
        }
    }
    /**
     * Determines if a given point lies on the line segment.
     *
     * @param p the point to check
     * @return true if the point lies on the line segment, false otherwise
     */
    public boolean isPointInLine(Point p) {
        Point lStart = this.start, lEnd = this.end;
        // If the point is in range of the start and end points
        if ((p.getX() >= Math.min(lStart.getX(), lEnd.getX()) - EPSILON
                && p.getX() <= Math.max(lStart.getX(), lEnd.getX()) + EPSILON)
                && (p.getY() >= Math.min(lStart.getY(), lEnd.getY()) - EPSILON
                && p.getY() <= Math.max(lStart.getY(), lEnd.getY()) + EPSILON)) {
            // If the line is vertical
            if (thresholdEqual(lStart.getX(), lEnd.getX())) {
                return thresholdEqual(p.getX(), lStart.getX());
            }
            // If the line is horizontal
            if (thresholdEqual(lStart.getY(), lEnd.getY())) {
                return thresholdEqual(p.getY(), lStart.getY());
            }
            // Calculate the slope of the line
            double slope = (lEnd.getY() - lStart.getY()) / (lEnd.getX() - lStart.getX());
            // Calculate the b of the line using y = mx + b
            double b = lStart.getY() - slope * lStart.getX();
            // Return if the point satisfies the line equation y = slope * x + intercept
            return thresholdEqual(p.getY(), (slope * p.getX() + b));
        } else {
            return false;
        }
    }
    /**
     * Determines if this line intersects with another line.
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // geometry.Line are equal - indeed intersecting
        if (this.equals(other)) {
            return true;
        }
        // Vertical check
        boolean vertical1 = thresholdEqual(this.start.getX(), this.end.getX());
        boolean vertical2 = thresholdEqual(other.start.getX(), other.end.getX());
        // If at least one line is vertical
        if (vertical1 || vertical2) {
            // If both line are vertical - overlap or no intersection point
            if (vertical1 && vertical2) {
                // If there is overlap - the start/end point lies on the other line
                return this.isPointInLine(other.start) || this.isPointInLine(other.end);
            } else { // Only one line is vertical - call the method to return if intersection point found
                return this.verticalIntersect(other) != null;
            }
        }
        // Calculating slops for the lines
        double slope1 = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        double slope2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
        // Find b of the lines (y = mx + b)
        double b1 = this.start.getY() - slope1 * this.start.getX();
        double b2 = other.start.getY() - slope2 * other.start.getX();
        // Check if slopes are not equal (lines are not parallel)
        if (slope1 != slope2) {
            // Find coordinate of intersection point using y = mx + b
            double xIntersect = (b2 - b1) / (slope1 - slope2);
            double yIntersect = xIntersect * slope1 + b1;
            Point p = new Point(xIntersect, yIntersect);
            // Return if intersection point lies on both line segments
            return this.isPointInLine(p) && other.isPointInLine(p);
        } else {
            // The lines are parallel - check if start/end point is on the other line
            // If true - the lines are overlapping, else - the lines do not intersect
            return this.isPointInLine(other.start) || this.isPointInLine(other.end);
        }
    }
    /**
     * Determines if this line intersects with two other lines.
     *
     * @param other1 the first other line
     * @param other2 the second other line
     * @return true if this line intersects with both other lines, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }
    /**
     * Returns the intersection point if this line intersects with another line.
     *
     * @param other the other line
     * @return the intersection point if it exists, null otherwise
     */
    public Point intersectionWith(Line other) {
        // If lines do not intersect or equal, return null - 0 intersection points or more than 1
        if (!this.isIntersecting(other) || this.equals(other)) {
            return null;
        }
        // Vertical check
        boolean vertical1 = thresholdEqual(this.start.getX(), this.end.getX());
        boolean vertical2 = thresholdEqual(other.start.getX(), other.end.getX());
        // If at least one line is vertical
        if (vertical1 || vertical2) {
            // If both line are vertical - overlap or no intersection point
            if (vertical1 && vertical2) {
                // Returns: overlap - null, intersection - the point
                return this.parallelIntersect(other);
            } else { // Only one line is vertical - call the method to return intersection point
                return this.verticalIntersect(other);
            }
        } else {
            // Calculating slopes for the lines
            double slope1 = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            double slope2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());

            // Find b of the lines (y = mx + b)
            double b1 = this.start.getY() - slope1 * this.start.getX();
            double b2 = other.start.getY() - slope2 * other.start.getX();
            // The lines are not parallel
            if (slope1 != slope2) {
                // Find coordinate of intersection point using y = mx + b
                double xIntersect = (b2 - b1) / (slope1 - slope2);
                double yIntersect = slope1 * xIntersect + b1;
                Point p = new Point(xIntersect, yIntersect);
                // Check if intersection point lies on both line segments
                if (this.isPointInLine(p) && other.isPointInLine(p)) {
                    return p;
                } else {
                    return null;
                }
            } else { // The lines are parallel - Returns the intersection point if found, else null
                return this.parallelIntersect(other);
            }
        }
    }
    /**
     * Determines if this line is equal to another line.
     *
     * @param other the other line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }
    /**
     * Finds the closest intersection point to the start of the line with the given rectangle.
     *
     * @param rect The rectangle to check for intersections with this line.
     * @return The closest intersection point to the start of the line, or null if there are no intersections.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // Get the list of intersection points between this line and the rectangle
        List<Point> list = rect.intersectionPoints(this);
        // If the list is empty, there are no intersections, so return null
        if (list.isEmpty()) {
            return null;
        }
        // If there is only one intersection point, return that point
        if (list.size() == 1) {
            return list.get(0);
        }
        // If there are two intersection points, return the one closest to the start of the line
        // Compare the distances from the start of the line to the two intersection points
        return (this.start.distance(list.get(0)) < this.start.distance(list.get(1))) ? list.get(0) : list.get(1);
    }
}