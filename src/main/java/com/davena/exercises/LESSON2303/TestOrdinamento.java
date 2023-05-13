package com.davena.exercises.LESSON2303;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestOrdinamento {
    public static void main(String[] args) {

        List<PPerson> l = new ArrayList<>();
        l.add(new PPerson("giulio", "verdi"));
        l.add(new PPerson("giulio", "rossi"));
        l.add(new PPerson("giulio", "bianchi"));

        //Collections.sort(l); mi da errore perché devo fornire un metodo per fare il sort
        //quindi in PPerson scrivo implements comparable<PPerson> e lui implementerà il metodo, adesso quindi lo posso scrivere
        Collections.sort(l);
        //Collections.binarySearch(l, new PPerson("giulio", "rossi"));

        /*
        Per aver maggiora flessibilità non si modifica PPerson, ma si usa un altro tipo di sort, che usa
        un tipo PPerson senza comparable, ed un comparator che mi consente di mettere in relazione gli elementi
        Comparator è un interfaccia che ha un metodo compare che fa la comparazione tra due elementi
         */
        Collections.sort(l, new Comparator<PPerson>() {
            @Override
            public int compare(PPerson o1, PPerson o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
