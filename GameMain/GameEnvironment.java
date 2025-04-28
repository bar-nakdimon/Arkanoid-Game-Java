package GameMain;
// 324205764 Bar Nakdimon

import Collision.Collidable;
import Collision.CollisionInfo;
import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameMain.GameEnvironment class represents the environment in which the game objects exist.
 * It keeps track of all collidable objects and provides methods to add new collidables and find the closest collision.
 */
public class GameEnvironment {
    // List to store all collidable objects in the game environment
    private List<Collidable> collidables;
    /**
     * Constructs a new GameMain.GameEnvironment with an empty list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }
    /**
     * Adds the given collidable to the environment.
     *
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * Finds the closest collision that would occur along the given trajectory.
     * Assumes an object moving from line.start() to line.end().
     *
     * @param trajectory The line representing the object's trajectory.
     * @return The information about the closest collision that is going to occur, or null if no collision occurs.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Variable to store the shortest distance to a collision
        double closestDistance = -1;
        // Variable to store the closest collision information
        CollisionInfo closest = null;
        // Iterate through all collidables to find the closest collision point
        for (Collidable c : collidables) {
            // Find intersection point between the trajectory to the collidable object
            Point intersect = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (intersect != null) {
                // If this is the first collision or if this collision is closer than the previous closest
                if (closestDistance == -1 || trajectory.start().distance(intersect) < closestDistance) {
                    // Update the closest distance
                    closestDistance = trajectory.start().distance(intersect);
                    // Update the closest collision information
                    closest = new CollisionInfo(intersect, c);
                }
            }
        }
        // Return the closest collision information, or null if no collision occurs
        return closest;
    }
    /**
     * Removes the specified collidable from the game environment.
     *
     * @param c The collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}
