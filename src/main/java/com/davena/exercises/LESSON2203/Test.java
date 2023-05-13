package com.davena.exercises.LESSON2203;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList <String> l= new ArrayList();
        /*
        * Posso anche srivere List l=new ArrayList();
        * ma non ho accesso a tutti i metodi di ArrayList, ma ha il vantaggio dell'astrazione,
        * quindi posso scrivere new [qualunque altra implementazione di List], quindi è più versatile in caso di cambiamenti.
        * la linkedList ha metodi in più, ad esempio addFirst.
        * Se non abbiamo un obiettivo specifico conviene usare List
        * */
        l.add("nicola");
        l.add("gigi");
        l.add("bbbb");
        l.remove(0);
        l.remove("gigi");
        System.out.println(l.contains("nicola"));
        System.out.println(l);
    }
}
