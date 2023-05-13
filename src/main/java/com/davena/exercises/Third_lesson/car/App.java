package com.davena.exercises.Third_lesson.car;

public class App {
    public static void main(String[] args) {
        Car c= new Car (false, "AA786BB" );
        SDCar sdCar = new SDCar(false, "CC555DD", false);
        /**
         * se non dovessimo definire il toString in SDCar allora per stampare sdcar userebbe la definizione trovata in car,
         *quindi non stamperebbe l'attributo isSelfDriving
         */

        /**
         * Se io cambio il package ad SDCar non ho più diritti sulle variabili, quindi uso protected, che è come default,
         * ma se si tratta di sottoclassi posso accedere anche se si trovano in package diversi.
         * Si usa poco perché è importante mantenere la coesione all'interno dei programmi.
         */
        System.out.println(c);
        System.out.println(sdCar);
        Car[] garage=new Car[4];
        garage[0]=new Car(false, "AA111BB");
        garage[1]=new Car(true, "CC222DD");
        garage[2]=new Car(false, "EE333FF");
        garage[3]=new SDCar(true, "GG444HH", false);

        for (Car cc : garage) System.out.println(cc.toString());

        /**
         * SDCar z4 = garage[3];
         * si incazza perché garage[3] è un Car, quindi devo downcastare
         */
        SDCar z4 = (SDCar) garage[3];
        z4.turnSDoff();
        /**
         * se lo facessimo con garage[2] si incazzerebbe perché non è possibile passare un Car ad SDCar in questo modo,
         * quindi faccio:
         */
        if (garage[2] instanceof SDCar) {
            SDCar plaid = (SDCar) garage[2];
            plaid.turnSDoff();
        }
//queste 2 scritture sono equivalenti
        if (garage[2] instanceof SDCar sdCar1){
            sdCar1.turnSDoff();
        }

    }
}
