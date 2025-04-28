package Sprite;
// 324205764 Bar Nakdimon

import Geometry.Point;

/**
 * The Sprite.Velocity class specifies the change in position on the `x` and `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a Sprite.Velocity object with the specified change in x and y positions.
     *
     * @param dx the change in the x-axis
     * @param dy the change in the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a Sprite.Velocity object from an angle and speed.
     *
     * @param angle the angle in degrees
     * @param speed the speed
     * @return a new Sprite.Velocity object based on the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Convert angle to radians
        double radians = Math.toRadians(angle);
        // Calculate dx and dy
        double dx = speed * Math.sin(radians);
        double dy = -speed * Math.cos(radians);
        // Return new Sprite.Velocity object
        return new Velocity(dx, dy);
    }
    /**
     * Gets the angle of the velocity in degrees.
     *
     * @return the angle of the velocity in degrees
     */
    public double getAngleVelocity() {
        // Calculate the angle in radians and convert to degrees
        double radians = Math.atan2(dy, dx);
        return Math.toDegrees(radians);
    }

    /**
     * Takes a point with position (x, y) and returns a new point with position (x+dx, y+dy).
     *
     * @param p the point to which the velocity is applied
     * @return a new geometry.Point object with the updated position
     */
    public Point applyToPoint(Point p) {
        // Return new geometry.Point with updated coordinates
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Gets the change in the x-axis.
     *
     * @return the change in the x-axis
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * Sets the change in the x-axis.
     *
     * @param dx the new change in the x-axis
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * Gets the change in the y-axis.
     *
     * @return the change in the y-axis
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * Sets the change in the y-axis.
     *
     * @param dy the new change in the y-axis
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    /**
     * Gets the speed based on dx and dy.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }
}
