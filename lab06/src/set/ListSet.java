package set;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of ints. A simple implementation of a set using a list.
 */
public class ListSet implements SimpleSet {

    List<Integer> elems;

    public ListSet() {
        elems = new ArrayList<Integer>();
    }

    /** Adds k to the set. */
    @Override
    public void add(int k) {
        elems.add(k);
    }

    /** Removes k from the set. */
    public void remove(int k) {
        Integer toRemove = k;
        int removeIndex = 0;
        if (this.size() == 0) return;
        for (int i = 0; i < this.size(); i++){
            if (i == toRemove) {
                removeIndex = i;
                break;
            }
        }
        // TODO - use the above variable with an appropriate List method.
        // The reason is beyond the scope of this lab, but involves
        // method resolution.
        elems.remove(removeIndex);
    }

    /** Return true if k is in this set, false otherwise. */
    @Override
    public boolean contains(int k) {
        // TODO: Implement this method.
            if (elems.contains(k)) return true;
        return false;
    }

    /** Return true if this set is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
      return this.size() == 0;
    }

    /** Returns the number of items in the set. */
    @Override
    public int size() {
        // TODO: Implement this method.
        return elems.size();
    }

    /** Returns an array containing all of the elements in this collection. */
    @Override
    public int[] toIntArray() {
        // TODO - use a for loop!
        if (this.size() == 0) return null;
        int[] ints = new int[this.size()];
        for (int i = 0; i < this.size(); i++){
            ints[i] = elems.get(i);
        }
        return ints;
    }
}
