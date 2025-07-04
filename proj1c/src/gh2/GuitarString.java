package gh2;

import deque.ArrayDeque;
import deque.Deque;
import java.util.Random; // Import Random for generating more controlled random numbers

public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;
    private Random random; //  Use a Random object for generating random numbers

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque<>(capacity);
        random = new Random(); // Initialize the Random object
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // Replace the buffer with random numbers between -0.5 and 0.5
        for (int i = 0; i < buffer.size(); i++) { // Iterate over the existing elements
            buffer.removeFirst(); // Remove the current first element
            buffer.addLast(random.nextDouble() - 0.5); // Add a new random value to the end
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        if (buffer.isEmpty()) {
            // Handle the empty buffer case, for example:
            return;
            // Or throw a meaningful exception
            // throw new IllegalStateException("Cannot call tic() on an empty GuitarString.");
        }
        double front = buffer.removeFirst();
        double next = buffer.getFirst();
        double newSample = DECAY * 0.5 * (front + next);
        buffer.addLast(newSample);
    }
    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.getFirst(); // Return the first element without removing
    }
}