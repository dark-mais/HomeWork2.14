package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StringList list = new ArrayStringList(10);

        list.add("Hello");
        list.add("World");
        list.add(1, "Java");
        System.out.println(Arrays.toString(list.toArray())); // [Hello, Java, World]

        list.set(1, "Python");
        System.out.println(Arrays.toString(list.toArray())); // [Hello, Python, World]

        list.remove("Python");
        System.out.println(Arrays.toString(list.toArray())); // [Hello, World]

        System.out.println(list.contains("Hello")); // true
        System.out.println(list.indexOf("World")); // 1
        System.out.println(list.lastIndexOf("World")); // 1

        System.out.println(list.get(0)); // Hello

        StringList otherList = new ArrayStringList(10);
        otherList.add("Hello");
        otherList.add("World");

        System.out.println(list.equals(otherList)); // true

        System.out.println(list.size()); // 2
        System.out.println(list.isEmpty()); // false

        list.clear();
        System.out.println(list.size()); // 0
    }
}