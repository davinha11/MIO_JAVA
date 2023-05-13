package com.davena.exercises.Fourth_lesson;

import java.awt.*;

public class Rectangle extends AbstractShape {
    Point upperLeft;
    Point lowerRight;

    public Rectangle(String id, Point upperLeft, Point lowerRight) {
        super(id);
        this.upperLeft = upperLeft;
        this.lowerRight = lowerRight;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "upperLeft=" + upperLeft +
                ", lowerRight=" + lowerRight +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public double getWidth() {
        return lowerRight.x- upperLeft.x;
    }

    @Override
    public double getHeight() {
        return upperLeft.y - lowerRight.y ;
    }


    @Override
    public void paint() {
        System.out.println("Io sono un rettangolo");

    }
}
