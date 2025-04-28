package Hit;
// 324205764 Bar Nakdimon


/**
 * The Hit.HitNotifier interface represents an object that can notify listeners about hit events.
 */
public interface HitNotifier {
    /**
     * Adds a hit listener to the list of listeners.
     *
     * @param hl The hit listener to add.
     */
    void addHitListener(HitListener hl);
    /**
     * Removes a hit listener from the list of listeners.
     *
     * @param hl The hit listener to remove.
     */
    void removeHitListener(HitListener hl);
}
