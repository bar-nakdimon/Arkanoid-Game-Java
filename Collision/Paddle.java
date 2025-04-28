package Collision;
// 324205764 Bar Nakdimon

import biuoop.DrawSurface;
import Geometry.Point;
import Geometry.Rectangle;
import Sprite.Ball;
import Sprite.Velocity;
import Sprite.Sprite;
import GameMain.Game;
import java.awt.Color;

/**
 * The Collision.Paddle class represents the player's paddle in the game,
 * which can move left and right and interact with balls.
 */
public class Paddle implements Sprite, Collidable {
    // The right and left boundaries limit for the paddle
    static final int RIGHT_LIMIT = 775;
    static final int LEFT_LIMIT = 25;
    // The value to add or reduce to the paddle x when moving
    static final int MOVE = 8;
    // The keyboard sensor to detect player input
    private final biuoop.KeyboardSensor keyboard;
    // The rectangle representing the paddle's shape
    private Rectangle rect;
    /**
     * Constructs a new Collision.Paddle with the specified keyboard sensor and rectangle.
     *
     * @param keyboard The keyboard sensor for detecting input.
     * @param rect The rectangle representing the paddle's shape.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect) {
        this.keyboard = keyboard;
        this.rect = rect;

    }
    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        // Calculate the new x-coordinate after moving left
        double newX = this.rect.getUpperLeft().getX() - MOVE;
        // If the new x-coordinate is beyond the left limit
        if (newX < LEFT_LIMIT) {
            // Set the x-coordinate to the left limit - for circular fashion
            newX = RIGHT_LIMIT - this.rect.getWidth();
        }
        // Create a new point with the new coordinates
        Point next = new Point(newX, this.rect.getUpperLeft().getY());
        // Update the rectangle's position
        this.rect = new Rectangle(next, this.rect.getWidth(), this.rect.getHeight());
    }
    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        // Calculate the new x-coordinate after moving right
        double newX = this.rect.getUpperLeft().getX() + MOVE;
        // If the new x-coordinate is beyond the right limit
        if (newX + this.rect.getWidth() > RIGHT_LIMIT) {
            // Set the x-coordinate to the right limit - for circular fashion
            newX = LEFT_LIMIT;
        }
        // Create a new point with the new coordinates
        Point next = new Point(newX, this.rect.getUpperLeft().getY());
        // Update the rectangle's position
        this.rect = new Rectangle(next, this.rect.getWidth(), this.rect.getHeight());
    }
    /**
     * Handles the passing of time, checking for player input to move the paddle.
     */
    public void timePassed() {
        // If the left key is pressed - move left
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        }
        // If the right key is pressed - move right
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d The surface on which to draw the paddle.
     */
    public void drawOn(DrawSurface d) {
        // Draw the filled rectangle representing the paddle
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        // Draw the outline of the rectangle

        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }
    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return The collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    /**
     * Handles the hit event when an object collides with the paddle.
     *
     * @param hitter The ball from the collision.
     * @param collisionPoint The point of collision.
     * @param currentVelocity The current velocity of the colliding object.
     * @return The new velocity after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // If the collision is above the paddle
        if (collisionPoint.getY() < this.rect.getUpperLeft().getY()) {
            // Return the current velocity
            return currentVelocity;
        }
        // Determine which region of the paddle was hit
        switch (getRegion(collisionPoint.getX())) {
            case 1:
                // Return new velocity for region 1 - angle of 300
                return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            case 2:
                // Return new velocity for region 2 - angle of 330
                return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            case 3:
                // Return new velocity for region 3 - reverse dy velocity
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            case 4:
                // Return new velocity for region 4 - angle of 30
                return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            case 5:
                // Return new velocity for region 5 - angle of 60
                return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            default:
                // Return the current velocity if no region is matched
                return currentVelocity;
        }
    }
    /**
     * Determines which region of the paddle was hit based on the x-coordinate of the collision point.
     *
     * @param x The x-coordinate of the collision point.
     * @return The region number (1 to 5) or 0 if no region is matched.
     */
    private int getRegion(double x) {
        // Divide the paddle into 5 equal regions
        double zones = this.rect.getWidth() / 5;
        // Get the starting x-coordinate of the paddle
        double start = this.rect.getUpperLeft().getX();
        // Creating 5 zones
        double first = zones, second = zones * 2, third = zones * 3, forth = zones * 4, fifth = zones * 5;
        // If x in the first zone
        if (x >= start && x <= start + first) {
            return 1;
        } else if (x >= start + first && x <= start + second) { // If x in the second zone
            return 2;
        } else if (x >= start + second && x <= start + third) { // If x in the third zone
            return 3; // 3
        } else if (x >= start + third && x <= start + forth) { // If x in the forth zone
            return 4;
        } else if (x >= start + forth && x <= start + fifth) { // If x in the fifth zone
            return 5;
        } else { // Return 0 if no region is matched
            return 0;
        }
    }
    /**
     * Adds this paddle to the game.
     *
     * @param g The game to which the paddle should be added.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}