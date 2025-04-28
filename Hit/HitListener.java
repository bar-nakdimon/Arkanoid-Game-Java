package Hit;
// 324205764 Bar Nakdimon

import Collision.Block;
import Sprite.Ball;

/**
 * The Hit.HitListener interface represents an object that listens for hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit by the hitter Sprite.Sprite.Ball.
     *
     * @param beingHit The Collision.Block object that is being hit.
     * @param hitter   The Sprite.Sprite.Ball object that is hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

