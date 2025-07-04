package deque;

import deque.Deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T> {
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node current;

        public LinkedListDequeIterator() {
            current = sentinel1.next; // Start from the first actual element
        }

        @Override
        public boolean hasNext() {
            return current != sentinel2; // Check if there are more elements before the second sentinel
        }

        @Override
        public T next() {
            if (!hasNext()) { // Throw exception if there are no more elements
                return null;
            }
            T item = current.item; // Get the item of the current node
            current = current.next; // Move to the next node
            return item; // Return the current item
        }
    }

    private class Node {

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
            prev.next = this;
            next.prev = this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (item.equals(o)) return true;
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
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x, sentinel2, sentinel2.prev);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> resultList = new ArrayList<>();
        Node currentNode = sentinel1.next;
        while (currentNode != sentinel2) {
            resultList.add(currentNode.item);
            currentNode = currentNode.next;
        }
        return resultList;
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
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        T x = sentinel1.next.item;
        sentinel1.next = sentinel1.next.next;
        return x;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if (index > size || index < 0) return null;
        Node p = sentinel1.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;

    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return helper(sentinel1.next, index);
    }

    private T helper(Node currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }
        return helper(currentNode.next, index - 1);
    }

    @Override
    public String toString(){
        String str = "[";
        for (Node cur = sentinel1.next; cur != sentinel2; cur = cur.next){
            str += cur.item.toString();
            if (cur != sentinel2.prev) str += ", ";
        }
        return str + "]";
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }

        LinkedListDeque<?> other = (LinkedListDeque<?>) o;
        if (this.size() != other.size()) {
            return false;
        }

        Iterator<T> thisIterator = this.iterator();
        Iterator<?> otherIterator = other.iterator();
        while (thisIterator.hasNext()) {
            T thisItem = thisIterator.next();
            Object otherItem = otherIterator.next();
            if (thisItem == null && otherItem != null) {
                return false;
            }
            if (thisItem != null && !thisItem.equals(otherItem)) {
                return false;
            }
            if (thisItem == null && otherItem == null) {
            }
        }

        return true;
    }

    @Override
    public T getFirst(){
        return sentinel1.next.item;
    }
}

