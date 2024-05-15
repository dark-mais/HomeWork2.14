package org.example;

import org.example.exception.ElementNotFoundException;
import org.example.exception.InvalidIndexException;
import org.example.exception.NullElementException;

import java.util.Arrays;

public class ArrayStringList implements StringList {
    private String[] elements;
    private int size;

    public ArrayStringList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than zero.");
        }
        elements = new String[initialCapacity];
        size = 0;
    }

    @Override
    public String add(String item) {
        validateNotNull(item);
        ensureCapacity();
        elements[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateNotNull(item);
        validateIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateNotNull(item);
        validateIndex(index);
        String oldItem = elements[index];
        elements[index] = item;
        return oldItem;
    }

    @Override
    public String remove(String item) {
        validateNotNull(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("Element not found.");
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String removedItem = elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return removedItem;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        validateNotNull(item);
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        validateNotNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return elements[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullElementException("Compared list cannot be null.");
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!elements[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    private void validateNotNull(String item) {
        if (item == null) {
            throw new NullElementException("Null elements are not allowed.");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException("Index out of bounds.");
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException("Index out of bounds.");
        }
    }
}
