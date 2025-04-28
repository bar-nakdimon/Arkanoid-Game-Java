package GameMain;
// 324205764 Bar Nakdimon

import Collision.Block;
import Collision.Collidable;
import Collision.Paddle;
import Hit.BallRemover;
import Hit.BlockRemover;
import Hit.Counter;
import Sprite.Sprite;
import biuoop.GUI;
import biuoop.DrawSurface;
import Geometry.Point;
import Geometry.Rectangle;
import Sprite.Ball;
import Sprite.SpriteCollection;
import Sprite.ScoreIndicator;
import Sprite.Velocity;

import java.awt.Color;

/**
 * The GameMain.Game class represents the main game logic and control.
 */
public class Game {
    // Width and height of the screen
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // Sizes of the game blocks
    static final int BLOCK_LINES = 6;
    static final int BLOCK_IN_RAW = 10;
    static final int BLUE_START = 25;
    static final int BLOCK_WIDTH = 60;
    static final int BLOCK_HEIGHT = 25;
    static final int PADDLE_WIDTH = 80;
    static final int PADDLE_HEIGHT = 20;
    // Radius of the balls
    static final int BALL_RADIUS = 6;
    // Collection of sprites in the game
    private SpriteCollection sprites;
    // Environment containing the collidables
    private GameEnvironment environment;
    // Hit.Counter for the remaining Blocks
    private Counter remainingBlocks;
    // Hit.Counter for the remaining Balls
    private Counter remainingBalls;
    // Hit.Counter for the game's score
    private Counter score;

    /**
     * Constructs a new GameMain.Game instance.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = new Counter(0);
    }
    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s The sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * Removes a collidable object from the game environment.
     *
     * @param c The collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * Removes a sprite from the sprite collection.
     *
     * @param s The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * Initializes the game by creating blocks, balls, and the paddle, and adding them to the game.
     */
    public void initialize() {
        // Initialize listeners and indicators
        BlockRemover blockRemoveListener = new BlockRemover(this, remainingBlocks);
        BallRemover ballRemoveListener = new BallRemover(this, remainingBalls);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);
        ScoreIndicator indicator = new ScoreIndicator(this.score);
        indicator.addToGame(this);
        //
        // Create and add death region block
        Block deathRegion = new Block(new Rectangle(new Point(0, HEIGHT - (double) BLUE_START / 2),
                WIDTH,  BLUE_START), Color.BLUE);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemoveListener);
        // Create and add screen border blocks
        Block grayFrame1 = new Block(new Rectangle(new Point(0, BLUE_START), WIDTH, BLUE_START), Color.DARK_GRAY);
        Block grayFrame2 = new Block(new Rectangle(new Point(WIDTH - BLUE_START, BLUE_START),
                BLUE_START, HEIGHT), Color.DARK_GRAY);
        Block grayFrame3 = new Block(new Rectangle(new Point(0, BLUE_START),
                BLUE_START, HEIGHT), Color.DARK_GRAY);
        grayFrame1.addToGame(this);
        grayFrame2.addToGame(this);
        grayFrame3.addToGame(this);
        // Create and add blue background block
        Block blueBackground = new Block(new Rectangle(new Point(BLUE_START, BLUE_START),
                WIDTH - 2 * BLUE_START, HEIGHT - 2 * BLUE_START), Color.BLUE);
        // Variable for the start of the blocks to be drawn
        double blueLimitX = blueBackground.getCollisionRectangle().getUpperLeft().getX()
                + blueBackground.getCollisionRectangle().getWidth();
        // Array of colors for the blocks in the game
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.cyan, Color.PINK, Color.GREEN};
        // Creating the blocks in the game. Each line of block will have different color
        for (int j = 0; j < BLOCK_LINES; j++) {
            for (int i = 0; i < BLOCK_IN_RAW - j; i++) {
                Block block = new Block(new Rectangle(new Point(blueLimitX - (i + 1) * BLOCK_WIDTH,
                        120 + (j * BLOCK_HEIGHT)), BLOCK_WIDTH, BLOCK_HEIGHT), colors[j]);
                block.addToGame(this);
                // Add the listeners to every block
                block.addHitListener(scoreTracker);
                block.addHitListener(blockRemoveListener);
                // Increase the number of blocks in the game
                this.remainingBlocks.increase(1);
            }
        }
        // Creating 3 ball to be in the game
        Ball ball1 = new Ball(new Point(300, 350), BALL_RADIUS, Color.WHITE, this.environment);
        Ball ball2 = new Ball(new Point(300, 350), BALL_RADIUS, Color.WHITE, this.environment);
        Ball ball3 = new Ball(new Point(300, 350), BALL_RADIUS, Color.WHITE, this.environment);
        // Set the velocity to the balls
        ball1.setVelocity(Velocity.fromAngleAndSpeed(50, 3));
        ball2.setVelocity(Velocity.fromAngleAndSpeed(100, 3));
        ball3.setVelocity(Velocity.fromAngleAndSpeed(300, 3));
        // Add the balls to the game
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
        // Increase the number of balls in the game
        this.remainingBalls.increase(3);
    }
    /**
     * Runs the game by starting the animation loop.
     */
    public void run() {
        // Create GUI and keyboard sensor
        GUI gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        // Create the paddle and add it to the game
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(320, 555), PADDLE_WIDTH, PADDLE_HEIGHT));
        paddle.addToGame(this);
        // Creating blue frame only to be drawn for the screen background in blue
        Block blueBackground = new Block(new Rectangle(new Point(BLUE_START, BLUE_START),
                WIDTH - 2 * BLUE_START, HEIGHT), Color.BLUE);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        // Main game loop
        while (true) {
            // Get drawing surface
            DrawSurface d = gui.getDrawSurface();
            // GameMain.Game over condition when all blocks are removed
            if (this.remainingBlocks.getValue() == 0) {
                // Increase the score when all the block removed
                this.score.increase(100);
                // Draws the current state of the game
                drawGame(d, blueBackground, gui);
                gui.close();
                return;
            }
            // GameMain.Game over condition when all balls are lost
            if (this.remainingBalls.getValue() == 0) {
                gui.close();
                return;
            }
            // Timing
            long startTime = System.currentTimeMillis();
            // Draws the current state of the game
            drawGame(d, blueBackground, gui);
            // Notify all sprites that time has passed
            this.sprites.notifyAllTimePassed();
            // Timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * Draws the current state of the game onto the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     * @param blueBackground the Collision.Block representing the blue background
     * @param gui the GUI to display the drawing
     */
    private void drawGame(DrawSurface d, Block blueBackground, GUI gui) {
        // Draw the blue blueBackground
        blueBackground.drawOn(d);
        // Draw all sprites on the surface
        this.sprites.drawAllOn(d);
        // Show the surface
        gui.show(d);
    }
}
