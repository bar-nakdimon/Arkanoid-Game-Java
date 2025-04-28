package Collision;
// 324205764 Bar Nakdimon

import Geometry.Point;
import Geometry.Rectangle;
import Sprite.Velocity;
import Sprite.Ball;
/**
 * The Collision.Collidable interface represents an object that can be collided with.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return The rectangle representing the collision shape.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notifies the object that a collision has occurred at the specified point
     * with the given velocity, and returns the new velocity expected after the hit.
     *
     * @param hitter The ball that hit the object.
     * @param collisionPoint The point at which the collision occurred.
     * @param currentVelocity The current velocity at the time of collision.
     * @return The new velocity after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
