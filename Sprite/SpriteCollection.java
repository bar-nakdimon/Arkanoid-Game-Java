package Sprite;
// 324205764 Bar Nakdimon

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The Sprite.SpriteCollection class represents a collection of sprites in the game.
 * It manages adding sprites, updating their states, and drawing them on the screen.
 */
public class SpriteCollection {
    // List to store all sprites
    private List<Sprite> sprites;
    /**
     * Constructs a new Sprite.SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * Notifies all sprites in the collection that time has passed.
     * Calls the timePassed() method on each sprite.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites list before iterating over them
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }
    /**
     * Draws all sprites in the collection on the given DrawSurface.
     * Calls the drawOn(d) method on each sprite.
     *
     * @param d The surface on which to draw the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
    /**
     * Removes a sprite from the collection.
     *
     * @param s The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}