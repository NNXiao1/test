package gh2;

import deque.ArrayDeque;
import deque.Deque; // Assuming you have your Deque interface defined in the 'deque' package
import java.util.Random; // Import Random for generating white noise

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    private Deque<Double> buffer;

    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);

        if (capacity < 2) {
            return;
        }
        buffer = new ArrayDeque<Double>();

        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    public void pluck() {
        Random random = new Random();
        while (!buffer.isEmpty()) {
            buffer.removeFirst();
        }

        for (int i = 0; i < buffer.size(); i++) {
            double randomValue = random.nextDouble() - 0.5;
            buffer.addLast(randomValue);
        }
    }

    public void tic() {
        if (buffer.size() < 2) {
            return;
        }
        double firstSample = buffer.get(0);
        buffer.removeFirst();
        double secondSample = buffer.get(0);
        double newSample = DECAY * 0.5 * (firstSample + secondSample);
        buffer.addLast(newSample);
    }

    /**
     * Returns the current sample.
     *
     * @return The value of the item at the front of the buffer.
     */
    public double sample() {
        Double firstSample = buffer.get(0);

        if (firstSample == null) {
            return 0.0;
        } else {
            return firstSample;
        }
    }
}
