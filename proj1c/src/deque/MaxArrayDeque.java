package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> defaultComparator;

    /**
     * Creates a MaxArrayDeque with the given Comparator.
     *
     * @param c The Comparator to use for determining the maximum element.
     */
    public MaxArrayDeque(Comparator<T> c) {
        super(); // Call the ArrayDeque constructor
        this.defaultComparator = c;
    }

    /**
     * Returns the maximum element in the deque using the default Comparator.
     *
     * @return The maximum element, or null if the deque is empty.
     */
    public T max() {
        if (isEmpty()) {
            return null;
        }
        return findMax(defaultComparator);
    }

    /**
     * Returns the maximum element in the deque using the provided Comparator.
     *
     * @param c The Comparator to use for determining the maximum element.
     * @return The maximum element, or null if the deque is empty.
     */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        return findMax(c);
    }

    /**
     * Helper method to find the maximum element using a given Comparator.
     *
     * @param comparator The Comparator to use.
     * @return The maximum element, or null if the deque is empty.
     */
    private T findMax(Comparator<T> comparator) {
        if (isEmpty()) {
            return null;
        }

        Iterator<T> iterator = this.iterator();
        T maxElement = iterator.next();

        while (iterator.hasNext()) {
            T currentElement = iterator.next();
            if (comparator.compare(currentElement, maxElement) > 0) {
                maxElement = currentElement;
            }
        }
        return maxElement;
    }
}