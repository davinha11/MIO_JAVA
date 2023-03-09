package com.davena.exercises.array;

import java.awt.Point;
import java.util.Arrays;

public class array {
    public static final int MAX_SIZE = 10;
    public static void main(String[] args) {


        int[] w = {1, 2, 3, 4, 9, 5, 6, 7};  //dichiarazione + creazione dell'array


        Point[] p;
        String[] strings =  new String[6];   //strings è un array di 6 stringhe
        strings [1]= new String ("Ciao");


        String[][] table= {{"a", "b", "c"}, {"d", "e", "f"}};       //è un array bidimensionale in cui a, b, c sono contigue tra loro, stesso per d, e, f, ma non sono contigue tra loro

        Arrays.fill(strings, "negro");
        strings[1]="ciao";
        strings[4]="bella";

        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));
        System.out.println("ciao si trova nella posizione " + Arrays.binarySearch(strings, "ciao"));

        int[] v= Arrays.copyOf(w, 3);
    }
}
