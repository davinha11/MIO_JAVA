package com.davena.exercises.LESSON2303;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> l= new ArrayList<>();
        l.add("nicola");
        l.add("etta");
        System.out.println(l);

        //L'aggiunta in testa si fa
        l.add(0, "ggiul");

        l.contains("silvana");
        System.out.println(l);


        List<PPerson> pPeople=new ArrayList<>();
        pPeople.add(new PPerson("nicola", "bb"));

        pPeople.add(new PPerson("gest", "bb"));
        PPerson p= pPeople.get(0);
        System.out.println(pPeople.contains(new PPerson("gest", "bb")));


        List<String> list = new ArrayList<>(10);
        list.add("luusa");
        list.add("abaco");
        Collections.sort(list);
        System.out.println(list);
        System.out.println(list.contains("giovanni"));
        /*
        Per farlo megliio e più veloce
         */
        Collections.binarySearch(list, "giovanni");

        list.addAll(l); //aggiungo tutti gli elementi di l in list
        list.addAll(List.of("ciccio", "pasticcio")); // è il metodo più veloce per aggiungere elementi
        list.clear();//pulisce la lista. è il modo più ottimizzata, sennò posso anche scrivere:
        //list = new ArrayList<>();
        list.indexOf("giovanni"); //ritorna l'indice della prima occorrenza
        System.out.println(l.size());//stampa il num di elementi presenti
        String[] v =new String[10];
        System.out.println(v.length);//questo mi ritorna quanto è lunga la stringa, ma non quanti eleenti ha
    }
}
