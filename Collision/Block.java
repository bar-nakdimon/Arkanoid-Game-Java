package Collision;
// 324205764 Bar Nakdimon

import biuoop.DrawSurface;
import Geometry.Point;
import Geometry.Rectangle;
import Hit.HitNotifier;
import Hit.HitListener;
import Sprite.Sprite;
import Sprite.Velocity;
import GameMain.Game;
import Sprite.Ball;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The Collision.Block class represents a block object in the game, which can be drawn on the screen,
 * interact with other objects, and be part of the game environment.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // The rectangle shape of the block
    private Rectangle rect;
    private java.awt.Color color;
    // The block's listener list
    private List<HitListener> hitListeners;
    /**
     * Constructs a new Collision.Block with the specified rectangle and color.
     *
     * @param rect The rectangle shape of the block.
     * @param color The color of the block.
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    /**
     * Returns the collision rectangle of the block.
     *
     * @return The collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    /**
     * Handles the hit event when an object collides with the block.
     *
     * @param hitter The ball from the collision.
     * @param collisionPoint The point of collision.
     * @param currentVelocity The current velocity of the colliding object.
     * @return The new velocity after the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // If the color is not the same as the ball - notify for the hit
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        // Check if the collision point is on a vertical side of the block
        boolean vertical  = this.rect.getLeftLine().isPointInLine(collisionPoint)
                || this.rect.getRightLine().isPointInLine(collisionPoint);
        // Check if the collision point is on a horizontal side of the block
        boolean horizontal = this.rect.getTopLine().isPointInLine(collisionPoint)
                || this.rect.getBottomLine().isPointInLine(collisionPoint);
        // If the collision point is on both vertical and horizontal sides
        if (vertical && horizontal) {
            // Reverse both dx and dy velocities
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // If the collision point is only on a vertical side
        if (vertical) {
            // Reverse the dx velocity
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        // If the collision point is only on a horizontal side
        if (horizontal) {
            // Reverse the dy velocity
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // If no specific side is hit, return the current velocity
        return currentVelocity;
    }
    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface The surface on which to draw the block.
     */
    public void drawOn(DrawSurface surface) {
        // Draw the filled rectangle representing the block
        surface.setColor(color);
        surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        // Draw the outline of the rectangle
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }
    /**
     * Notifies the block that time has passed.
     */
    public void timePassed() {

    }
    /**
     * Adds the block to the game by adding it to both the sprite and collidable to the game.
     *
     * @param g The game to which the block should be added.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Returns the color of the block.
     *
     * @return The color of the block.
     */
    public java.awt.Color getColor() {
        return color;
    }
    /**
     * Checks if the ball's color matches the block's color.
     *
     * @param ball The ball to compare color with.
     * @return True if the colors match, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }
    /**
     * Removes the block from the game.
     *
     * @param game The game from which the block should be removed.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
    /**
     * Adds a hit listener to the block.
     *
     * @param hl The hit listener to add.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * Removes a hit listener from the block.
     *
     * @param hl The hit listener to remove.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * Notifies all hit listeners about a hit event.
     *
     * @param hitter The ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
