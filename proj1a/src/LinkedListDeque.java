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
            if (next != null) this.next = next;
            if (prev != null) this.prev = prev;
            assert next != null;
            next.next = this;
            assert prev != null;
            prev.prev = this;
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
        sentinel1 = new Node(null, sentinel2);
        sentinel2 = new Node(null);
        sentinel2.prev = sentinel1;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x);

        newNode.next = sentinel1.next;
        newNode.prev = sentinel1;

        sentinel1.next.prev = newNode;
        sentinel1.next = newNode;

        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x);

        newNode.next = sentinel2;
        newNode.prev = sentinel2.prev;

        sentinel2.prev.next = newNode;
        sentinel2.prev = newNode;

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
