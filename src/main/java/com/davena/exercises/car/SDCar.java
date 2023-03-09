package com.davena.exercises.car;

public class SDCar extends Car{
    boolean IsSelfDriving;

    public SDCar(boolean IsOn, String licencePlate, boolean isSelfDriving) {
        super (IsOn, licencePlate);
        this.IsSelfDriving = isSelfDriving;
    }

    public void turnon() {
        turnSDon();
        super.turnon();
    }

    public void turnoff() {
        turnSDoff();
        super.turnoff();
    }

    public void turnSDon() {
        IsSelfDriving=true;
    }

    public void turnSDoff() {
        IsSelfDriving= false;
    }

    @Override
    public String toString() {
        return "SDCar{" +
                "IsSelfDriving=" + IsSelfDriving +
                ", isOn=" + isOn +
                ", licencePlate='" + licencePlate + '\'' +
                '}';
    }

    public static void main(String[] args) {
        SDCar sdc = new SDCar(false, "AA", false);
        System.out.println(sdc);
    }
}
