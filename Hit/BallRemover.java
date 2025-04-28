package Hit;
// 324205764 Bar Nakdimon

import GameMain.Game;
import Sprite.Ball;
import Collision.Block;

/**
 * The Hit.BallRemover class is in charge of removing balls from the game and keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;
    /**
     * Constructs a new Hit.BallRemover with the specified game and counter.
     *
     * @param game The game from which balls should be removed.
     * @param remainingBalls The counter keeping track of remaining balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    /**
     * Handles the hit event when a ball hits a block.
     * Removes the ball from the game and updates the remaining balls count.
     *
     * @param beingHit The block that was hit (not used in this context).
     * @param hitter The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
