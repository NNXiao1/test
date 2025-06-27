import java.util.Arrays;
import java.util.Iterator; // Import Iterator
import java.util.Objects; // Import Objects for better equals comparison

/**
 * A src.AList is a list of items. Like SLList, it also hides the terrible
 * truth of the nakedness within, but uses an array as it's base.
 */
public class AList<Item> implements Iterable<Item> { // Change to Iterable<Item>

    private Item[] items;
    private int size;

    /** Creates an empty src.AList. */
    public AList() {
        items = (Item[]) new Object[8];
        size = 0;
    }

    /** Returns a src.AList consisting of the given values. */
    public static <Item> AList<Item> of(Item... values) {
        AList<Item> list = new AList<>();
        for (Item value : values) {
            list.addLast(value);
        }
        return list;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Adds item to the back of the list. */
    public void addLast(Item item) {
        if (items.length == size) {
            resize();
        }
        items[size] = item;
        size += 1;
    }

    /** Resize the array to accommodate more items. */
    private void resize() {
        Item[] newItems = (Item[]) new Object[items.length * 2];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    /** Returns the item at the specified index. */
    public Item get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return items[index];
    }

    @Override
    public String toString() {
        String result = "";
        int p = 0;
        boolean first = true;
        while (p != size) {
            if (first) {
                result += items[p].toString();
                first = false;
            } else {
                result += " " + items[p].toString();
            }
            p += 1;
        }
        return result;
    }

    /** Returns whether this and the given list or object are equal. */
    @Override
    public boolean equals(Object o) {
        // Check for self-comparison
        if (this == o) {
            return true;
        }
        // Check for null and type compatibility
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        // Cast the object to AList
        AList<?> other = (AList<?>) o; // Use wildcard for flexibility

        // Compare size
        if (size != other.size) {
            return false;
        }

        // Compare elements using Objects.equals for null safety
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(items[i], other.items[i])) {
                return false;
            }
        }
        return true;
    }

    // Implementing the Iterator interface
    @Override
    public Iterator<Item> iterator() {
        return new AListIterator();
    }

    private class AListIterator implements Iterator<Item> {
        private int current = 0; // Cursor to keep track of elements

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return items[current++];
        }
    }
}
