package GameMain;
// 324205764 Bar Nakdimon

import Collision.Block;
import Hit.Counter;
import Hit.HitListener;
import Sprite.Ball;

/**
 * The GameMain.ScoreTrackingListener class implements the Hit.HitListener interface to track and update the game score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructs a GameMain.ScoreTrackingListener with the given Hit.Counter for score tracking.
     *
     * @param scoreCounter The Hit.Counter object to track the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Handles the hit event between the beingHit Collision.Block and the hitter Sprite.Sprite.Ball.
     * Increases the score if the colors of the Collision.Block and Sprite.Sprite.Ball do not match - block removed.
     *
     * @param beingHit The Collision.Block object being hit.
     * @param hitter   The Sprite.Sprite.Ball object hitting the Collision.Block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter)) {
            currentScore.increase(5);
        }
    }
}