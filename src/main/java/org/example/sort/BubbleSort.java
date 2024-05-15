package org.example.sort;

import org.example.Sortable;

public class BubbleSort implements Sortable {

    @Override
    public void sort(Integer[] array, int size) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < size - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
}
