package Hit;
// 324205764 Bar Nakdimon


/**
 * Hit.Counter is a class that keeps track of an integer count.
 */
public class Counter {
    private int count;
    /**
     * Constructs a new Hit.Counter with initial count set to 0.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     * Constructs a new Hit.Counter with the specified initial count.
     *
     * @param count The initial count value.
     */
    public Counter(int count) {
        this.count = count;
    }
    /**
     * Increases the current count by the specified number.
     *
     * @param number The number to increase the count by.
     */
    public void increase(int number) {
        this.count += number;
    }
    /**
     * Decreases the current count by the specified number.
     *
     * @param number The number to decrease the count by.
     */
    public void decrease(int number) {
        this.count -= number;
    }
    /**
     * Returns the current count.
     *
     * @return The current count.
     */
    public int getValue() {
        return this.count;
    }
}