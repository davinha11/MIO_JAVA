package com.davena.exercises.Fourth_lesson;

import java.awt.*;

public class Circle extends AbstractShape implements Computable {
    String id;
    Point center;
    double radius;

    public Circle(String id, Point center, double radius) {
        super(id);
        this.center = center;
        this.radius = radius;

    }

    @Override
    public String toString() {
        return "Circle{" +
                "center=" + center +
                ", radius=" + radius +
                ", id=" + id +
                '}';
    }

    @Override
    public double getWidth() {

        return 2*radius;
    }

    @Override
    public double getHeight() {
        return 2*radius;
    }

    @Override
    public void paint() {
        System.out.println("Sono un cerchio");
    }

    @Override
    public double getArea() {
        return radius*radius*3.14;
    }

    @Override
    public double getPerimeter() {
        return 6.28*radius;
    }
}
