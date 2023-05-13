package com.davena.exercises.LESSON2203;

import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        List l= List.of("nicola", "ciccio", "agata", "agata");
        Set<String>setoflist =new HashSet<>(l);
        System.out.println(setoflist);
        /*
        Con linkedHashSet mi mantiene in memoria l'ordine in cui inserisco i file
         */

        /*
        Se per noi l'ordine di inserimento è importante posso fare:
         */
        List l2=new ArrayList(new LinkedHashSet<>(l));
        System.out.println(l2);

        /*
        un altra variante è la tree set, che non supporta duplicati e implementa un meccanismo di ordinamento interno
         */
        List l3=new ArrayList(new TreeSet<>(l));

        Set<String> s =new HashSet<>();
        s.add("nicola");
        s.add("ciccio");
        s.add("abaco");

        s.add("abaco");
        /*
        Se aggiungo un elemento che esiste già lui me lo toglie in automatico
        questo infatti è l'uso principale del set. L'hashSet è il metodo più veloce
        */

        System.out.println(s);

        /*
        Non supportano il mantenimento dell'ordine in cui io inserisco i file perché usa l'hash quindi viene riordinato
        in base all'hash della parola
         */
    }
}
