package com.davena.exercises.car;

public class  Car {
    boolean isOn;
    String licencePlate;

    public Car(boolean isOn, String licensPlate) {
        isOn = isOn;
        this.licencePlate = licensPlate;
    }

    public Car() {
        this.isOn = false;
        this.licencePlate = licencePlate;
    }

    /* perchè cambia nelle macchine con guida autonoma uso il super.____*/
    public void turnon() {
        isOn = true;
    }

    public void turnoff() {
        isOn = false;
    }


}
