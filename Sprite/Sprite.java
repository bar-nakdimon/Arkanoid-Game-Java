package Sprite;
// 324205764 Bar Nakdimon
import GameMain.Game;
import biuoop.DrawSurface;

/**
 * The Sprite.Sprite interface represents an object that can be drawn on the screen and that can update over time.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d The surface on which to draw the sprite.
     */
    void drawOn(DrawSurface d);
    /**
     * Notifies the sprite that time has passed, allowing it to update its state.
     */
    void timePassed();
    /**
     * Adds this sprite to the game.
     *
     * @param g The game to which the sprite should be added.
     */
    void addToGame(Game g);
}
