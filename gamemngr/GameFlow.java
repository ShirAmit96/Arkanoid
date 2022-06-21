package gamemngr;
import animation.GameOver;
import animation.KeyPressStoppableAnimation;
import animation.YouWin;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import other.Counter;
import biuoop.GUI;
import java.util.List;
/**
 * @author Shir Amit
 * Id: 207640228
 * This class represents the Game flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private GUI gui;
    /**
     * Constructor.
     * @param ar  the animation runner.
     * @param ks  the keyboard sensor.
     * @param gui the gui.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.score = new Counter(0);
        this.gui = gui;
    }
    /**
     * This method runs a given list of levels.
     * @param levels a list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score, this.gui);
            level.initialize();
            while (level.getRemainingBalls() > 0 && level.getRemainingBlocks() > 0) {
                level.run();
                } if (level.getRemainingBalls() == 0) {
                GameOver gameOver = new GameOver(this.score);
                KeyPressStoppableAnimation kps =
                        new KeyPressStoppableAnimation(this.keyboardSensor, this.keyboardSensor.SPACE_KEY, gameOver);
                while (!kps.shouldStop()) {
                    this.animationRunner.run(kps);
                }
                break;
            }
            if ((levels.indexOf(levelInfo) == levels.size() - 1) && level.getRemainingBlocks() == 0) {
                    YouWin youWin = new YouWin(this.score); KeyPressStoppableAnimation kps =
                        new KeyPressStoppableAnimation(this.keyboardSensor, this.keyboardSensor.SPACE_KEY, youWin);
                    while (!kps.shouldStop()) {
                    this.animationRunner.run(kps);
                }
                    break;
                    }
                }
            }
        }



