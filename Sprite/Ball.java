package Sprite;
// 324205764 Bar Nakdimon

import Collision.CollisionInfo;
import biuoop.DrawSurface;
import Geometry.Line;
import Geometry.Point;
import GameMain.GameEnvironment;
import GameMain.Game;

import java.awt.Color;


/**
 * The Sprite.Sprite.Ball class represents a ball with a center point, radius, color, velocity and game environment.
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private  java.awt.Color color;
    private Velocity v;
    private GameEnvironment game;
    /**
     * Constructs a new Sprite.Sprite.Ball with the specified center point, radius, color, and boundary limits.
     *
     * @param center The center point of the ball.
     * @param r The radius of the ball.
     * @param color The color of the ball.
     * @param game The GameMain.Game Environment of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.game = game;
    }
    /**
     * Returns the x-coordinate of the center of the ball.
     *
     * @return The x-coordinate of the center of the ball.
     */
    public int getX() {
        return (int) center.getX();
    }
    /**
     * Returns the y-coordinate of the center of the ball.
     *
     * @return The y-coordinate of the center of the ball.
     */
    public int getY() {
        return (int) center.getY();
    }
    /**
     * Returns the radius of the ball.
     *
     * @return The radius of the ball.
     */
    public int getSize() {
        return r;
    }
    /**
     * Returns the color of the ball.
     *
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }
    /**
     * Sets the color of the ball to the specified color.
     *
     * @param color The new color of the ball.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }
    /**
     * Draws the ball and its center on the given DrawSurface.
     *
     * @param surface The surface on which to draw the ball.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), r);
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), r);
    }
    /**
     * Sets the velocity of the ball.
     *
     * @param v The new velocity.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }
    /**
     * Sets the velocity of the ball using dx and dy values.
     *
     * @param dx The change in x-axis.
     * @param dy The change in y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * Gets the current velocity of the ball.
     *
     * @return The current velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }
    /**
     * Moves the ball one step, checking for possible collisions in the way.
     */
    public void moveOneStep() {
        // Calculate the next point based on current velocity
        Point nextPoint = this.v.applyToPoint(this.center);
        // Create a line from current to next point
        Line trajectory = new Line(this.center, nextPoint);
        // Get collision information from the games objects and the trajectory
        CollisionInfo info = this.game.getClosestCollision(trajectory);
        // If there is a collision
        if (info != null) {
            // Update velocity based on collision
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.v));
            // Calculate the next point after collision
            Point checkNext = this.v.applyToPoint(this.center);
            // Create a new trajectory
            trajectory = new Line(this.center, checkNext);
            // Check for collision again
            info = this.game.getClosestCollision(trajectory);
            // If there is another collision
            if (info != null) {
                // Update velocity based on the second collision
                this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.v));
            }
            // Move the ball to the new position after handling collisions
            this.center = this.v.applyToPoint(this.center);
        } else { // Move the ball to the next point if no collision occurs
            this.center = nextPoint;
        }
    }
    /**
     * Notifies the ball that time has passed and so it should move one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * Adds the ball to the game.
     *
     * @param g The game to which the ball should be added.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
    /**
     * Removes the ball from the game.
     *
     * @param game The game from which the ball should be removed.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}