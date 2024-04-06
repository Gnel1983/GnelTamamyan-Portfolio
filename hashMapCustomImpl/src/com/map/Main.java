package com.map;


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMapCustom<Integer, Integer> map = new HashMapCustom<>(8);
        map.put(10, 20);
        map.put(11, 30);
        map.put(12, 40);
        map.put(13, 50);
        map.put(14, 50);
        map.put(15, 50);
        map.put(16, 50);
        map.put(17, 50);
        System.out.println(map);
        map.put(17, 500);
        System.out.println(map);
        System.out.println(map.remove(12));
        System.out.println(map.remove(17));
        System.out.println(map.get(11));

        System.out.println(map.size());
        System.out.println(map);


    }
}
