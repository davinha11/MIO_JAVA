package com.davena.exercises.Fourth_lesson;

public interface MyShape {
    double getWidth();
    double getHeight();
    void paint();

    default void paintSpecial() {
        System.out.println("I'm a special guy");
    }
}
