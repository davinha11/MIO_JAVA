package com.davena.exercises.LESSON2303;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "nicola");
        map.put(2, "giulia");
        map.put(3, "marzia");
        map.put(4, "agata");
        //Se inseriamo due coppie con le stesse chiavi la prima verrà sovrascritta
        System.out.println(map.keySet());
        System.out.println(map.values());

        Set<Map.Entry<Integer, String>> l = map.entrySet();
        //ritorna il set delle entry. Entry è una classe statica presente dentro map
        for (Map.Entry<Integer, String> entry : l) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }//oppure equivalmente si può scrivere
        System.out.println(map);

        System.out.println(map.get(4));
        // è una memoria associatuva, infatti associa un intero ad una stringa

    }
}
