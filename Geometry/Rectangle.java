package Geometry;
// 324205764 Bar Nakdimon

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rectangle defined by its position (x, y),
 * width, and height.
 */
public class Rectangle {
    // EPSILON for equal threshold check
    static final double EPSILON = 0.001;
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Creates a new rectangle with the specified location and width/height.
     *
     * @param upperLeft The upper left point of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * Gets the width of the rectangle.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the rectangle.
     *
     * @param width the new width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the rectangle.
     *
     * @param height the new height
     */
    public void setHeight(double height) {
        this.height = height;
    }
    /**
     * Gets the Upper Left point of the rectangle.
     *
     * @return the point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
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
     * Gets the left line of the rectangle.
     *
     * @return The left line of the rectangle.
     */
    public Line getLeftLine() {
        return new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
    }
    /**
     * Gets the right line of the rectangle.
     *
     * @return The right line of the rectangle.
     */
    public Line getRightLine() {
        return new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
    }
    /**
     * Gets the top line of the rectangle.
     *
     * @return The top line of the rectangle.
     */
    public Line getTopLine() {
        return new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
    }
    /**
     * Gets the bottom line of the rectangle.
     *
     * @return The bottom line of the rectangle.
     */
    public Line getBottomLine() {
        return new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
    }
    /**
     * Returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line The line to check for intersections.
     * @return A list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // Create a list to store intersection points
        List<Point> list = new ArrayList<>();
        // Check intersection with the left line
        Point intersect = line.intersectionWith(this.getLeftLine());
        if (intersect != null) {
            // Add the intersection point to the list if it exists
            list.add(intersect);
        }
        // Check intersection with the right line
        intersect = line.intersectionWith(this.getRightLine());
        if (intersect != null) {
            // Add the intersection point to the list if it exists
            list.add(intersect);
        }
        // Check intersection with the top line
        intersect = line.intersectionWith(this.getTopLine());
        if (intersect != null) {
            // Add the intersection point to the list if it exists
            list.add(intersect);
        }
        // Check intersection with the bottom line
        intersect = line.intersectionWith(this.getBottomLine());
        if (intersect != null) {
            // Add the intersection point to the list if it exists
            list.add(intersect);
        }
        // Return the list of intersection points
        return list;
    }
}