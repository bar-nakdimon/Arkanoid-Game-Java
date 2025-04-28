package Sprite;
// 324205764 Bar Nakdimon

import Hit.Counter;
import GameMain.Game;
import biuoop.DrawSurface;

/**
 * The Sprite.ScoreIndicator class implements the Sprite.Sprite interface to display the current score on a DrawSurface.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    /**
     * Constructs a Sprite.ScoreIndicator with the given Hit.Counter.
     *
     * @param score The Hit.Counter to display the score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * Draws the score on the given DrawSurface.
     *
     * @param d The DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.drawText(350, 20, "Score: " + score.getValue(), 15);
    }
    /**
     * Notifies the score indicator that time has passed.
     */
    public void timePassed() { }
    /**
     * Adds this Sprite.ScoreIndicator to the given GameMain.Game by adding it as a Sprite.Sprite.
     *
     * @param g The GameMain.Game to add this Sprite.ScoreIndicator to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

}
