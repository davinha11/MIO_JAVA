package com.davena.exercises.Third_lesson.basic_ClickCounter;


import java.util.Objects;

public class ClickCounter {
    int clicks;

    public ClickCounter() {
        clicks=0;
    }


    public void click() {
        clicks += 1;
    }

    /* clicks = Math.max (0, clicks -1) è equivalente e più succinto */
    public void undo() {
        clicks -= 1;
        if (clicks <=0)
            clicks = 0;
    }

    public void reset() {
        clicks = 0;
    }

    @Override
    public String toString() {
        return "ClickCounter{" +"clicks=" + clicks +'}';
    }


    public int getValue() {
        return clicks;
    }



    /*
    public static void main(String[] args) {
        ClickCounter cc1 = new ClickCounter();
        cc1.click();
        cc1.click();
        cc1.undo();
        System.out.println(cc1.getValue());

    }
    */

    public static void main(String[] args) {
        ClickCounter cc1 = new ClickCounter();
        ClickCounter cc2 = new ClickCounter();

        System.out.println(cc1.equals(cc2));
        /*ci dice falso perchè sono riferimenti diversi. Funziona solo con le stringhe perchè ridefiniscono equals */

        /*per far si che ci dia true deve essere ClickCounter cc2 = cc1 */

        /*per aggiustare equals facciomo generate equals, che deve sempre andare in coppia con un hash code

           @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClickCounter that = (ClickCounter) o;
        return clicks == that.clicks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clicks);
    }
      */

    /*
        molte volte posso costruire un costruttore che crea un clickk counter che prende un altro click counter
        in questo modo nel main si fa:

        ClickCounter cc2 = new ClickCounter(cc2);

        quindi crea una copia di cc1

        si fa la stessa cosa chiamando il metodo clone() che torno un nuovo riferimento alla copia dell'oggetto
        non si usa molte volte perchè bisogna usare il cast al tipo dell'oggetto.
        l'altro problema è che le copie sono "shallow copy" ovvero viene copiato solo il primo livello dell'oggetto.


        ES: con un array di punti
        ArrayList <Point> l1 = ArrayList <> ();
        l1.add(new Point(1, 2);
        qui aggiungo gli altri punti nell'array

        ArrayList <Point> l2 = (ArrayList) l1.clone();

        ma l2 copia i riferimenti, ma non gli oggetti
        Infatti se cambio un punto in l1 questo cambierà anche in l2, quindi non è una copia efficace

     */


        /* WRAPPER CLASSES : CONVERSIONS
        in un main :

        String s = "3";
        int a = Integer.parseInt(s);
        double d = Double.parseDouble(s);

        String s2= String.valueOf(7);



           Possiamo usare le versioni oggetto senza fregarcene un cazzo tanto ci pensa java
         */



    }

}
