package org.example;

import org.example.sort.BubbleSort;
import org.example.sort.QuickSort;

import java.util.Arrays;

public class IntegerList implements StringList {

    private Integer[] elements;
    private int size;
    private Sortable sortable;

    public IntegerList(Sortable sortable) {
        this.sortable = sortable;
        elements = new Integer[10];
        size = 0;
    }

    @Override
    public String add(String item) {
        ensureCapacity();
        elements[size] = Integer.parseInt(item);
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = Integer.parseInt(item);
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        String oldValue = elements[index].toString();
        elements[index] = Integer.parseInt(item);
        return oldValue;
    }

    @Override
    public String remove(String item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new IllegalArgumentException("Element not found");
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        String removedElement = elements[index].toString();
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public boolean contains(String item) {
        int value = Integer.parseInt(item);
        sort();
        return binarySearch(elements, value, size) >= 0;
    }

    @Override
    public int indexOf(String item) {
        int value = Integer.parseInt(item);
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        int value = Integer.parseInt(item);
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index].toString();
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException("Other list is null");
        }
        if (this.size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(otherList.get(i))) {
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
        elements = new Integer[10];
        size = 0;
    }

    @Override
    public String[] toArray() {
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = elements[i].toString();
        }
        return array;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    private void sort() {
        sortable.sort(elements, size);
    }

    private int binarySearch(Integer[] array, int value, int size) {
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid].equals(value)) {
                return mid;
            } else if (array[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
