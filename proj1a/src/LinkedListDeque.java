package proj1a.src;

import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        /*
         * The access modifiers inside a private nested class are irrelevant:
         * both the inner class and the outer class can access these instance
         * variables and methods.
         */
        public T item;
        public Node next;
        public Node prev;

        public Node(T item, Node next) { // TODO: consider adding another constructor
            this.item = item;
            this.next = next;
            this.prev = null; // TODO
        }

        public Node(T item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }

        public Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node that = (Node) o;
            return item.equals(that.item);
        }

        @Override
        public String toString() {
            return item + "";
        }

    }
    private Node sentinel1;
    private Node sentinel2;
    private int size;

    public LinkedListDeque() {
        sentinel2 = new Node(null);
        sentinel1 = new Node(null, sentinel2);
        sentinel2.prev = sentinel1;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x, sentinel1.next, sentinel1);
        sentinel1.next.prev = newNode; // Update the old first node's prev
        sentinel1.next = newNode;     // Update sentinel1's next to the new node
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x, sentinel2, sentinel2.prev);
        sentinel2.prev.next = newNode; // Update the old last node's next
        sentinel2.prev = newNode;      // Update sentinel2's prev to the new node
        size++;
    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
