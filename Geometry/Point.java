package Geometry;

// 324205764 Bar Nakdimon
/**
 * The geometry.Point class represents a point in a two-dimensional space.
 */
public class Point {
    // EPSILON for equal threshold check
    static final double EPSILON = 0.001;
    private final double x;
    private final double y;
    /**
     * Constructs a new point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Calculates the distance from this point to another point.
     *
     * @param other the other point to which the distance is calculated
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.getX()) * (this.x - other.getX())
                + (this.y - other.getY()) * (this.y - other.getY()));
    }
    /**
     * Checks if this geometry.Point is equal to another geometry.Point within a certain threshold.
     * Two points are considered equal if their x and y coordinates are equal.
     *
     * @param other the geometry.Point object to compare with.
     * @return true if this geometry.Point is approximately equal to the other geometry.Point, false otherwise.
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.getX()) < EPSILON && Math.abs(this.y - other.getY()) < EPSILON;
    }
    /**
     * @return string of the point to print
     */
    public String print() {
        return "geometry.Point: (" + this.x + "," + this.y + ")";
    }
    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return x;
    }
    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return y;
    }
}