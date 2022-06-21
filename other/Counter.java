package other;
/**
 * @author Shir Amit
 * ID:207640228
 * This class represents a counter.
 */
public class Counter {
    private int remainingBlocks;
    /**
     * Constructor.
     * @param remainingBlocks the number of remaining blocks.
     */
    public Counter(int remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }
    /**
     * This method increases the value of the counter.
     * @param number the number in which we increase the counter.
     */
   public void increase(int number) {
        this.remainingBlocks += number;
    }
    /**
     * This method decreases the value of the counter.
     * @param number the number in which we decrease the counter.
     */
     public void decrease(int number) {
        this.remainingBlocks -= number;
    }
    /**
     * This method returns the value of the counter.
     * @return the value
     */
     public int getValue() {
        return this.remainingBlocks;
    }
}