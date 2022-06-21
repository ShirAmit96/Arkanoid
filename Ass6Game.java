import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import biuoop.GUI;
import gamemngr.AnimationRunner;
import gamemngr.GameFlow;
import interfaces.LevelInformation;
import java.util.List;
import java.util.ArrayList;
/**
 * @author Shir Amit
 * ID: 207640228
 * This class represents the Ass6Game.
 */
public class Ass6Game {
    /**
     * This method starts game's running.
     * @param args - command line arguments.
     */
    public static void main(String[] args) {
        List<LevelInformation> levelsList = new ArrayList<>();
        String[] input = args;
        for (String s:input) {
            if (s.equals("1")) {
                levelsList.add(new Level1());
            } else if (s.equals("2")) {
                levelsList.add(new Level2());
            } else if (s.equals("3")) {
                levelsList.add(new Level3());
            } else if (s.equals("4")) {
                levelsList.add(new Level4());
            }
        }
        if (levelsList.size() == 0) {
            levelsList.add(new Level1());
            levelsList.add(new Level2());
            levelsList.add(new Level3());
            levelsList.add(new Level4());
        }
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(60, gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor(), gui);
        gameFlow.runLevels(levelsList);
        gui.close();
        return;

    }
}
