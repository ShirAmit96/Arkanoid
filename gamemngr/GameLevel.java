package gamemngr;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import interfaces.LevelInformation;
import biuoop.DrawSurface;
import biuoop.GUI;
import collections.GameEnvironment;
import collections.SpriteCollection;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import other.Counter;
import other.Velocity;
import interfaces.Animation;
import java.util.ArrayList;
import java.awt.Color;
import biuoop.KeyboardSensor;
import sprites.TopSurface;
import sprites.NameIndicator;
import sprites.ScoreIndicator;
import sprites.Block;
import sprites.Ball;
import sprites.Paddle;
import java.util.List;
/**
 * @author Shir Amit
 * Id: 207640228
 * This class represents the Game Level.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter scoreCounter;
    private ScoreIndicator scoreIndicator;
    private List<Block> blocksList;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;
    private final int width = 800;
    private final int height = 600;
    private final int boundWidth = 20;
    private List<Ball> ballsList;
    private Paddle paddle;
    private final int radius = 6;
    private LevelInformation levelInfo;
    private TopSurface topSurface;
    private NameIndicator nameIndicator;

    /**
     * Constructor.
     *
     * @param levelInfo the level information
     * @param ks        the keyboard sensor
     * @param ar        the animation runner
     * @param score     the score
     * @param gui       the gui
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, Counter score, GUI gui) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls =  new Counter(0);
        this.scoreCounter = score;
        this.blocksList = new ArrayList<>();
        this.runner = ar;
        this.keyboard = ks;
        this.levelInfo = levelInfo;
        this.gui = gui;
        this.topSurface = new TopSurface();
        this.nameIndicator = new NameIndicator(levelInfo.levelName(), this.topSurface.getRec());
        this.scoreIndicator = new ScoreIndicator(this.scoreCounter, this.topSurface.getRec());
    }
    /**
     * This method adds a collidable object to the game's environment.
     * @param c a collidable object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * This method removes s collidable object from the game's environment.
     * @param c a collidable object.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }
    /**
     * This method adds a sprite to the sprites collection.
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * This method removes a given sprite from  the sprite's collection.
     * @param s a sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
    /**
     * This method initiates the balls and adds them to the game.
     */
    public void initBalls() {
        this.ballsList = new ArrayList<Ball>();
        for (int i = 0; i < levelInfo.numberOfBalls(); ++i) {
            double x = 0;
            double y = 0;
            Point center = new Point(x, y);
            Velocity v = levelInfo.initialBallVelocities().get(i);
            Color color = Color.WHITE;
            Ball ball = new Ball(center, radius, color, v, environment, paddle);
            ball.addToGame(this);
            ballsList.add(ball);
            remainingBalls.increase(1);
        }
    }
    /**
     * Create balls on top of paddle.
     */
    public void createBallsOnTopOfPaddle() {
        for (Ball b:ballsList) {
            double x = paddle.getCollisionRectangle().getUpperLeft().getX() + (this.paddle.getPadWidth() / 2);
            double y = paddle.getCollisionRectangle().getUpperLeft().getY() - radius;
            b.setCenter(x, y);
        }
    }
    /**
     * This method initiates the blocks.
     */
    public void initBlocks() {
        this.remainingBlocks.increase(levelInfo.numberOfBlocksToRemove());
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        for (Block b:levelInfo.blocks()) {
            ScoreTrackingListener stl = new ScoreTrackingListener(this.scoreCounter);
            b.addHitListener(stl);
            b.addHitListener(blockRemover);
            b.addToGame(this);
        }
    }
    /**
     * This method initiates the screen's bounds and adds them to the game.
     */
    public void initWalls() {
        List<Block> walls = new ArrayList<Block>();
        Block left = new Block(new Rectangle(new Point(0, 0), boundWidth, height), Color.DARK_GRAY);
        Block right = new Block(new Rectangle(new Point(width - boundWidth, 0), boundWidth, height),
                Color.DARK_GRAY);
        Block bottom = new Block(new Rectangle(new Point(0, height - 1), width, 1),
                Color.LIGHT_GRAY);
        Block top = new Block(new Rectangle(new Point(0, 0), width, boundWidth),
                Color.DARK_GRAY);
        walls.add(left);
        walls.add(right);
        walls.add(top);
        walls.add(bottom);
       for (Block b: walls) {
            b.addToGame(this);
        }
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        // define the bottom border as a 'death region':
        bottom.addHitListener(ballRemover);
    }
    /**
     * This method initiates the paddle and adds it to the game.
     */
    public void  initPaddle() {
        int padWidth = this.levelInfo.paddleWidth();
        int padHeight = 20;
        int padSpeed = this.levelInfo.paddleSpeed();
        this.paddle = new Paddle(this.gui, width, height, boundWidth, padWidth, padHeight, padSpeed);
        paddle.addToGame(this);
    }
    /**
     * This method initializes a new level.
     */
    public void initialize() {
        addSprite(this.levelInfo.getBackground());
        initWalls();
        initBlocks();
        initPaddle();
        initBalls();
        this.topSurface.addToGame(this);
        this.scoreIndicator.addToGame(this);
        this.nameIndicator.addToGame(this);
    }
   @Override
    public void doOneFrame(DrawSurface d) {
        PauseScreen pause = new PauseScreen();
        KeyPressStoppableAnimation kpsPause =
                new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY, pause);
            this.sprites.drawAllOn(d);
            this.scoreIndicator.drawOn(d);
            this.sprites.notifyAllTimePassed();
            //if the "p" key is pressed- pause the level:
        if (this.keyboard.isPressed("p")
                || this.keyboard.isPressed("P")
                || this.keyboard.isPressed("פ")) {
            new AnimationRunner(60, this.gui).run(kpsPause);
          }
    }
    @Override
    public boolean shouldStop() {
        if (this.remainingBlocks.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
        }
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
        return (!this.running);
    }
    /**
     * This method runs the game-starts the animation loop.
     */
    public void run() {
        this.createBallsOnTopOfPaddle();
        this.running = true;
        // countdown before turn starts:
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        //run the level with the animation runner:
        this.runner.run(this);

    }
    /**
     * This method returns the sprites collection of the game.
     * @return the sprites collection
     */
    public SpriteCollection getSprites() {
        return sprites;
    }
    /**
     * This method returns the number of remaining balls.
     * @return the number of remaining balls.
     */
    public int  getRemainingBalls() {
        return this.remainingBalls.getValue();
    }

    /**
     *  This method returns the number of remaining blocks.
     *
     * @return the number of remaining blocks/
     */
    public int getRemainingBlocks() {
        return this.remainingBlocks.getValue();
    }
}
