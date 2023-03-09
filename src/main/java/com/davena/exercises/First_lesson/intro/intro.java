package com.davena.exercises;

public class intro {
    public static void main(String[] args) {
        int[] v= {0, 9, 2, 7, 4, 1, 6, 7};


        /**
         * n varia all'interno di v
         * */
        for (int n : v ){
            System.out.println(n);
        }

        int i=0;
        while ( i < v.length) {
            System.out.println(v[i]);
            i++;
        }
    }



}