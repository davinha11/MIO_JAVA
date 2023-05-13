package com.davena.exercises.Fourth_lesson;

import java.awt.*;

public class App {

    public static void drawShapes(MyShape [] shapes) {
        for (MyShape s:shapes) {
            s.paint();
            if (s instanceof Computable cs) {//qui cè getArea perché cs è computable, ma prima s era solo myShape
                System.out.println(cs.getArea());
            }
        }
    }
    public static void main(String[] args) {
        MyShape [] shapes= new MyShape[3];

        shapes[0] = new Circle("123", new Point(0, 0), 10);
        shapes[1] =new Rectangle("456", new Point(0, 10), new Point(20, 0));
        shapes[2] = new Circle("789", new Point(0, 0), 10);
        Circle c1 = new Circle("22", new Point(1, 2), 4);
        drawShapes(shapes);
    }
}
