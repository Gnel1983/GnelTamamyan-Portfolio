package com.company;

import java.util.*;


public class Main {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> integers = new DoublyLinkedList<>();
        integers.add(0, 10);
        integers.add(11);
        integers.add(12);
        integers.add(13);
        integers.add(14);
        integers.add(15);
        integers.add(16);
        integers.add(17);


        integers.add(8, 20);
        for (int i : integers) {
            System.out.println(i);
        }
        System.out.println("--------");
        System.out.println(integers.remove(8));
        System.out.println("------------");

        Iterator<Integer> iterator = integers.iterator();
        iterator.forEachRemaining(System.out::println);
        System.out.println("---------");
        integers.remove(5);
        Iterator<Integer> iterator1 = integers.iterator();
        iterator1.forEachRemaining(System.out::println);
        integers.remove(5);
        System.out.println("-----------");
        Iterator<Integer> iterator2 = integers.iterator();
        iterator2.forEachRemaining(System.out::println);
        System.out.println("-----------");
        System.out.println(integers.size());

    }

}
