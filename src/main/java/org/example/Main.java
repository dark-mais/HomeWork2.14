package org.example;

import org.example.sort.QuickSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Тестирование с быстрой сортировкой
        IntegerList quickSortList = new IntegerList(new QuickSort());

        // Тестирование сортировок
        Integer[] array = new Integer[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        Integer[] arrayCopy1 = Arrays.copyOf(array, array.length);
        Integer[] arrayCopy2 = Arrays.copyOf(array, array.length);

        long start = System.currentTimeMillis();
        Arrays.sort(arrayCopy1);
        System.out.println("Arrays.sort: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        quickSortList.sortable.sort(arrayCopy2, arrayCopy2.length);
        System.out.println("QuickSort: " + (System.currentTimeMillis() - start) + "ms");
    }
}