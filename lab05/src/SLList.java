/** A data structure to represent a Linked List of Integers.
 * Each SLList represents one node in the overall Linked List.
 */

public class SLList<T> {

    /** The object stored by this node. */
    public T item;
    /** The next node in this SLList. */
    public SLList<T> next;

    public int length(){
        int i = 0;
        SLList<T> current = this;
        while (current != null){
            i ++;
            current = current.next;
        }
        return i;
    }

    /** Constructs an SLList storing ITEM and next node NEXT. */
    public SLList(T item, SLList<T> next) {
        this.item = item;
        this.next = next;
    }

    /** Constructs an SLList storing ITEM and no next node. */
    public SLList(T item) {
        this(item, null);
    }

    /**
     * Returns [position]th item in this list. Throws IllegalArgumentException
     * if index out of bounds.
     *
     * @param position, the position of element.
     * @return The element at [position]
     */
    public T get(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Position cannot be negative.");
        }

        SLList<T> current = this;
        int currentIndex = 0;

        while (current != null && currentIndex < position) {
            current = current.next;
            currentIndex++;
        }

        if (current == null) {
            throw new IllegalArgumentException("Position is out of bounds.");
        }

        return current.item;
    }
    /**
     * Returns the string representation of the list. For the list (1, 2, 3),
     * returns "1 2 3".
     *
     * @return The String representation of the list.
     */
    public String toString() {
        SLList<T> current = this;
        String string = "";
        while (current != null){
            string += String.valueOf(current.item) + " ";
            current = current.next;
        }
        return string.substring(0, string.length() - 1);
    }

    /**
     * Returns whether this and the given list or object are equal.
     *
     * NOTE: A full implementation of equals requires checking if the
     * object passed in is of the correct type, as the parameter is of
     * type Object. This also requires we convert the Object to an
     * SLList<T>, if that is legal. The operation we use to do this is called
     * casting, and it is done by specifying the desired type in
     * parenthesis. This has already been implemented for you.
     *
     * @param obj, another list (object)
     * @return Whether the two lists are equal.
     */

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SLList)) {
            return false;
        }
        SLList<T> otherLst = (SLList<T>) obj;
        if (otherLst.length() != this.length()) return false;
        for (int i = 0; i < otherLst.length(); i++){
            if(!otherLst.get(i).equals(this.get(i))) return false;
        }

        return true;
    }

    /**
     * Adds the given value at the end of the list.
     *
     * @param value, the int to be added.
     */
    public void add(T value) {
        SLList<T> current = this;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new SLList<>(value);
    }

    public void item(int i, T value){
        SLList<T> p = this;
        int r = 0;
        while (r != i){
            p = p.next;
            r++;
        }
        p.item = value;
    }
    public SLList<T> copy(){
        SLListFactory<T> factory = new SLListFactory<>();
        SLList<T> copy = factory.of(); // Initialize the copy list
        SLList<T> current = this;
        SLList<T> tail = copy; // Keep track of the tail of the copy list

        int i = 0;
        while (i < current.length()){
            // Create a new node with the data from the original list
            SLList<T> newNode = factory.of(current.get(i));

            // Append the new node to the copy list
            if (copy == null) { // Assuming SLList has an isEmpty() method
                copy = newNode; // If copy is empty, the new node becomes the head
                tail = copy; // And also the tail
            } else {
                tail.next = newNode; // Append the new node to the current tail
                tail = newNode; // Update the tail to the newly added node
            }
            i++; // Increment the counter
        }
        return copy;
    }

}