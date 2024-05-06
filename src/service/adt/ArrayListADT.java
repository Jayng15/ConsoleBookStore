package service.adt;

import java.util.Arrays;

import service.adt.interfaces.List;

public class ArrayListADT<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    // Constructor
    public ArrayListADT() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayListADT(int initialCapacity) {
        this.array = (T[]) new Object[initialCapacity];
        this.size = 0;
    }

    // Private method to ensure capacity
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = array.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    // Implements List interface methods
    @Override
    public void add(T element) {
        add(size, element);
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return array[index];
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        array[index] = element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                T removedElement = array[i];
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                size--;
                return removedElement;
            }
        }
        return null; // Element not found
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
    public void clear() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public T[] toArray(T[] ts) {
        if (ts.length < size) {
            return Arrays.copyOf(array, size, (Class<? extends T[]>) ts.getClass());
        } else {
            System.arraycopy(array, 0, ts, 0, size);
            if (ts.length > size) {
                ts[size] = null;
            }
            return ts;
        }
    }
}

