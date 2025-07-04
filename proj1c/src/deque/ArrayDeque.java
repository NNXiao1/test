package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T> {

    private Object[] list;
    private int size;

    public ArrayDeque() {
        list = new Object[0];
        size = 0;
    }

    public ArrayDeque(int x) {
        list = new Object[x];
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        if (list.length == size) {
            this.resize(size + 10);
        }
        shiftright(list);
        list[0] = x;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (list.length == size) {
            this.resize(size + 10);
        }
        list[size] = x;
        size++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ArrayDeque<?> other = (ArrayDeque<?>) o;
        if (this.size() != other.size()) {
            return false;
        }

        Iterator<T> thisIterator = this.iterator();
        Iterator<?> otherIterator = other.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
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

        return !thisIterator.hasNext() && !otherIterator.hasNext();
    }

    private boolean contains(T item) {
        for (Object o : list) {
            if (o == item) return true;
        }
        return false;
    }

    @Override
    public List<T> toList() {
        List<T> resultList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            resultList.add((T) list[i]);
        }
        return resultList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        Object x = list[0];
        shiftleft(list);
        size--;
        return (T) x;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return (T) list[index];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    private void resize(int capacity) {
        Object[] newData = new Object[capacity];
        System.arraycopy(list, 0, newData, 0, size);
        list = newData;
    }

    public static void shiftright(Object[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
    }

    public static void shiftleft(Object[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int currentIndex;

        public ArrayDequeIterator() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate");
            }
            T item = (T) list[currentIndex];
            currentIndex++;
            return item;
        }
    }

    @Override
    public String toString(){
        String str = "[";
        for (int i = 0; i < size; i++){
            str += list[i].toString();
            if (i != size - 1) str += ", ";
        }
        return str + "]";
    }

    @Override
    public T getFirst(){
        return (T) list[0];
    }
}
