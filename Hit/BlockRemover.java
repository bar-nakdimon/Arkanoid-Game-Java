package Hit;
// 324205764 Bar Nakdimon

import GameMain.Game;
import Collision.Block;
import Sprite.Ball;

/**
 * The Hit.BlockRemover class is in charge of removing blocks from the game and keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;
    /**
     * Constructs a new Hit.BlockRemover with the specified game and counter.
     *
     * @param game The game from which blocks should be removed.
     * @param remainingBlocks The counter keeping track of remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    /**
     * Handles the hit event when a block is hit by a ball.
     * Removes the block from the game and updates the remaining blocks count.
     * Also removes this listener from the block being removed.
     *
     * @param beingHit The block that was hit.
     * @param hitter The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // Check if the ball's color matches the block's color
        if (!beingHit.ballColorMatch(hitter)) {
            // Set hitter's color to block's color
            hitter.setColor(beingHit.getColor());
            // Remover the listener from the block
            beingHit.removeHitListener(this);
            // Remove the block from the game
            beingHit.removeFromGame(game);
            // Decrease the count of remaining blocks
            remainingBlocks.decrease(1);
        }
    }
}