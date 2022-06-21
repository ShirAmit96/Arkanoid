package interfaces;

/**
 * @author Shir Amit
 * ID: 207640228
 * This interface represents a Hit notifier.
 */
public interface HitNotifier {
    /**
     * This method adds a listener to hit events.
     * @param hl a given hit listener.
     */
    void addHitListener(HitListener hl);
    /**
     * This method removes a given hit listener from the list of listeners to hit events.
     * @param hl a given hit listener.
     */
    void removeHitListener(HitListener hl);
}
